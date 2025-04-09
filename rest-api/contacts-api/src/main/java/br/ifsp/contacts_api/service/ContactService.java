package br.ifsp.contacts_api.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifsp.contacts_api.dto.ContactRequestDTO;
import br.ifsp.contacts_api.dto.ContactResponseDTO;
import br.ifsp.contacts_api.exception.ResourceNotFoundException;
import br.ifsp.contacts_api.model.Address;
import br.ifsp.contacts_api.model.Contact;
import br.ifsp.contacts_api.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Page<ContactResponseDTO> getAllContacts(Pageable pageable) {
		return contactRepository.findAll(pageable)
                .map(this::convertToResponseDTO);
	}

	public ContactResponseDTO getContactById(Long id) {
		Contact contact =  contactRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));
		return convertToResponseDTO(contact);
	}

	public Page<ContactResponseDTO> searchContactsByName(String name, Pageable pageable) {
		return contactRepository.findByNomeContainingIgnoreCase(name, pageable)
				.map(this::convertToResponseDTO);
	}

	public ContactResponseDTO save(ContactRequestDTO contactDTO) {
		Contact contact = convertToEntity(contactDTO);
		Contact savedContact = contactRepository.save(contact);
		return convertToResponseDTO(savedContact);
	}

	
	public ContactResponseDTO updateContact(Long id, ContactRequestDTO updatedContact) {
	    Contact existingContact = contactRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));

	    existingContact.setNome(updatedContact.getNome());
	    existingContact.setEmail(updatedContact.getEmail());
	    existingContact.setTelefone(updatedContact.getTelefone());

	    if (updatedContact.getAddresses() != null) {
	        List<Address> addressEntities = updatedContact.getAddresses().stream()
	                .map(dto -> {
	                    Address address = modelMapper.map(dto, Address.class);
	                    address.setContact(existingContact); 
	                    return address;
	                })
	                .collect(Collectors.toList());

	        existingContact.setAddresses(addressEntities);
	    }

	    Contact updated = contactRepository.save(existingContact);
	    return convertToResponseDTO(updated);
	}


	public ContactResponseDTO updateContactPartial(Long id, Map<String, String> updates) {
		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));

		updates.forEach((key, value) -> {
			switch (key) {
			case "nome":
				contact.setNome(value);
				break;
			case "telefone":
				contact.setTelefone(value);
				break;
			case "email":
				contact.setEmail(value);
				break;
			default:
			    throw new IllegalArgumentException("Campo não suportado: " + key);

			}
		});

		Contact updated = contactRepository.save(contact);
        return convertToResponseDTO(updated);
	}

	public void deleteContact(Long id) {
		if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contato não encontrado: " + id);
        }
		contactRepository.deleteById(id);
	}

	public ContactResponseDTO convertToResponseDTO(Contact contact) {
		// Converte Entidade -> DTO
		return modelMapper.map(contact, ContactResponseDTO.class);
	}

	public Contact convertToEntity(ContactRequestDTO contactDTO) {
	    Contact contact = modelMapper.map(contactDTO, Contact.class);

	    // converte AddressDTO → Address e seta o contact em cada um
	    if (contactDTO.getAddresses() != null) {
	        List<Address> addressEntities = contactDTO.getAddresses().stream()
	            .map(dto -> {
	                Address address = modelMapper.map(dto, Address.class);
	                address.setContact(contact); // liga o address ao contact
	                return address;
	            })
	            .collect(Collectors.toList());

	        contact.setAddresses(addressEntities); // seta os endereços convertidos
	    }

	    return contact;
	}



}

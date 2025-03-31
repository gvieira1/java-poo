package br.ifsp.contacts_api.service;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifsp.contacts_api.dto.ContactDTO;
import br.ifsp.contacts_api.exception.ResourceNotFoundException;
import br.ifsp.contacts_api.model.Contact;
import br.ifsp.contacts_api.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Page<ContactDTO> getAllContacts(Pageable pageable) {
		return contactRepository.findAll(pageable)
                .map(this::convertToDTO);
	}

	public ContactDTO getContactById(Long id) {
		Contact contact =  contactRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));
		return convertToDTO(contact);
	}

	public Page<ContactDTO> searchContactsByName(String name, Pageable pageable) {
		return contactRepository.findByNomeContainingIgnoreCase(name, pageable)
				.map(this::convertToDTO);
	}

	public ContactDTO save(ContactDTO contactDTO) {
		Contact contact = convertToEntity(contactDTO);
		Contact savedContact = contactRepository.save(contact);
		return convertToDTO(savedContact);
	}

	public ContactDTO updateContact(Long id, ContactDTO updatedContact) {
		Contact existingContact = contactRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));

		existingContact.setNome(updatedContact.getNome());
		existingContact.setEmail(updatedContact.getEmail());
		existingContact.setTelefone(updatedContact.getTelefone());
		existingContact.setAddresses(updatedContact.getAddresses());

		Contact updated = contactRepository.save(existingContact);
        return convertToDTO(updated);
	}

	public ContactDTO updateContactPartial(Long id, Map<String, String> updates) {
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
			}
		});

		Contact updated = contactRepository.save(contact);
        return convertToDTO(updated);
	}

	public void deleteContact(Long id) {
		if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contato não encontrado: " + id);
        }
		contactRepository.deleteById(id);
	}

	public ContactDTO convertToDTO(Contact contact) {
		// Converte Entidade -> DTO
		return modelMapper.map(contact, ContactDTO.class);
	}

	public Contact convertToEntity(ContactDTO contactDTO) {
		// Converte DTO -> Entidade
		return modelMapper.map(contactDTO, Contact.class);
	}

}

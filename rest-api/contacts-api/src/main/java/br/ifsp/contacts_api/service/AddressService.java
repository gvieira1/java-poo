package br.ifsp.contacts_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsp.contacts_api.dto.AddressDTO;
import br.ifsp.contacts_api.exception.ResourceNotFoundException;
import br.ifsp.contacts_api.model.Address;
import br.ifsp.contacts_api.model.Contact;
import br.ifsp.contacts_api.repository.AddressRepository;
import br.ifsp.contacts_api.repository.ContactRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public AddressDTO save(Long contactId, AddressDTO addressDTO) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + contactId));
        
        Address address = convertToEntity(addressDTO);
        address.setContact(contact);   
        Address savedAddress = addressRepository.save(address);     
		return convertToDTO(savedAddress);
    }
	
	 public List<AddressDTO> getAddressesByContact(Long contactId) {
	        Contact contact = contactRepository.findById(contactId)
	                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + contactId));
	        
	        return contact.getAddresses()
	        		.stream()
	        		.map(this::convertToDTO)
	        		.collect(Collectors.toList());
	    }
	
	private AddressDTO convertToDTO(Address address) {
	    return modelMapper.map(address, AddressDTO.class);
	}

	private Address convertToEntity(AddressDTO addressDTO) {
	    return modelMapper.map(addressDTO, Address.class);
	}

}

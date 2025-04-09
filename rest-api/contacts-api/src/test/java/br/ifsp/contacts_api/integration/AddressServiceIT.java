package br.ifsp.contacts_api.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import br.ifsp.contacts_api.dto.AddressDTO;
import br.ifsp.contacts_api.exception.ResourceNotFoundException;
import br.ifsp.contacts_api.model.Address;
import br.ifsp.contacts_api.model.Contact;
import br.ifsp.contacts_api.repository.ContactRepository;
import br.ifsp.contacts_api.service.AddressService;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
@EntityScan(basePackages = "br.ifsp.contacts_api.model")
@Transactional
class AddressServiceIT {

	@Autowired
	private AddressService addressService;

	@Autowired
	private ContactRepository contactRepository;

	private Contact savedContact;

	@BeforeEach
	void setUp() {
	    Contact contact = new Contact();
	    contact.setNome("Teste");
	    contact.setEmail("teste@email.com");
	    contact.setTelefone("123456789");

	    Address address = new Address();
	    address.setRua("Rua B");
	    address.setCidade("São Paulo");
	    address.setEstado("SP");
	    address.setCep("11111-111");
	    address.setContact(contact); 
	    
	    contact.setAddresses(List.of(address));
	    savedContact = contactRepository.save(contact);
	}

	@Test
	void shouldSaveAddressForExistingContact() {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setRua("Rua A");
		addressDTO.setCidade("São Paulo");
		addressDTO.setEstado("SP");
		addressDTO.setCep("11111-111");

		AddressDTO savedAddress = addressService.save(savedContact.getId(), addressDTO);

		assertNotNull(savedAddress);
		assertEquals("Rua A", savedAddress.getRua());
	}

	@Test
	void shouldReturnAddressesByContact() {

		Pageable pageable = PageRequest.of(0, 10);
		Page<AddressDTO> result = addressService.getAddressesByContact(savedContact.getId(), pageable);

		assertFalse(result.isEmpty());
		assertEquals("Rua B", result.getContent().get(0).getRua());
	}

	@Test
	void shouldThrowException_whenGettingAddressesForNonexistentContact() {
		Pageable pageable = PageRequest.of(0, 10);

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			addressService.getAddressesByContact(999L, pageable);
		});

		assertEquals("Contato não encontrado com ID: 999", exception.getMessage());
	}
}

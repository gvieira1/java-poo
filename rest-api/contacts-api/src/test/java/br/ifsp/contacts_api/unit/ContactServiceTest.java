package br.ifsp.contacts_api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.ifsp.contacts_api.dto.AddressDTO;
import br.ifsp.contacts_api.dto.ContactRequestDTO;
import br.ifsp.contacts_api.dto.ContactResponseDTO;
import br.ifsp.contacts_api.exception.ResourceNotFoundException;
import br.ifsp.contacts_api.model.Address;
import br.ifsp.contacts_api.model.Contact;
import br.ifsp.contacts_api.repository.ContactRepository;
import br.ifsp.contacts_api.service.ContactService;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

	@Mock
	ContactRepository contactRepository;

	@Mock
	ModelMapper modelMapper;

	@InjectMocks
	ContactService contactService;

	private Contact contact;
	private ContactRequestDTO dto;
	private ContactResponseDTO resultDTO;
	private Contact existingContact;
	private ContactRequestDTO updatedDTO;
	private Contact updatedContact;
	private ContactResponseDTO expectedDTO;

	@BeforeEach
	void setup() {
		contact = new Contact();
		dto = new ContactRequestDTO();
		resultDTO = new ContactResponseDTO();

		existingContact = new Contact();
		existingContact.setNome("Nome Antigo");
		existingContact.setEmail("antigo@email.com");
		existingContact.setTelefone("8888-8888");
		existingContact.setAddresses(new ArrayList<>());

		// DTO com os dados atualizados
		updatedDTO = new ContactRequestDTO();
		updatedDTO.setNome("Novo Nome");
		updatedDTO.setEmail("novo@email.com");
		updatedDTO.setTelefone("9999-9999");
		updatedDTO.setAddresses(List.of(new AddressDTO()));

		// Entidade após atualização
		updatedContact = new Contact();
		updatedContact.setNome(updatedDTO.getNome());
		updatedContact.setEmail(updatedDTO.getEmail());
		updatedContact.setTelefone(updatedDTO.getTelefone());
		updatedContact.setAddresses(List.of(new Address()));

		// DTO esperado no retorno
		expectedDTO = new ContactResponseDTO();
		expectedDTO.setNome(updatedDTO.getNome());
		expectedDTO.setEmail(updatedDTO.getEmail());
		expectedDTO.setTelefone(updatedDTO.getTelefone());
		List<AddressDTO> addressDTOs = List.of(new AddressDTO("Rua A", "São Paulo", "SP", "11111-000"));
		expectedDTO.setAddresses(addressDTOs);
	}

	@Test
	void shouldReturnContacts_whenContactsExist() {

		Pageable pageable = PageRequest.of(0, 5);
		Page<Contact> page = new PageImpl<>(List.of(contact));

		when(contactRepository.findAll(pageable)).thenReturn(page);
		when(contactService.convertToResponseDTO(contact)).thenReturn(resultDTO);

		Page<ContactResponseDTO> result = contactService.getAllContacts(pageable);
		assertEquals(resultDTO, result.getContent().get(0));
	}

	@Test
	void shouldReturnContact_whenIdIsValid() {
		Long contactID = 1L;

		when(contactRepository.findById(contactID)).thenReturn(Optional.of(contact));
		when(contactService.convertToResponseDTO(contact)).thenReturn(resultDTO);

		ContactResponseDTO result = contactService.getContactById(contactID);
		assertEquals(result, resultDTO);
		verify(contactRepository).findById(contactID);

	}

	@Test
	void shouldThrowException_whenContactNotFound() {
		Long contactId = 2L;

		when(contactRepository.findById(contactId)).thenReturn(Optional.empty());

		ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
			contactService.getContactById(contactId);
		});

		verify(contactRepository).findById(contactId);
		assertEquals("Contato não encontrado: 2", ex.getMessage());
	}

	@Test
	void shouldReturnPagedContacts_whenNameMatches() {
		String name = "João";
		Pageable pageable = PageRequest.of(0, 5);

		when(contactRepository.findByNomeContainingIgnoreCase(name, pageable))
				.thenReturn(new PageImpl<>(List.of(contact)));
		when(contactService.convertToResponseDTO(contact)).thenReturn(resultDTO);

		Page<ContactResponseDTO> result = contactService.searchContactsByName(name, pageable);
		assertEquals(resultDTO, result.getContent().get(0));
	}

	@Test
	void shouldSaveContact_whenDataIsValid() {
		Contact savedContact = new Contact();

		when(modelMapper.map(dto, Contact.class)).thenReturn(contact);
		when(contactRepository.save(contact)).thenReturn(savedContact);
		when(modelMapper.map(savedContact, ContactResponseDTO.class)).thenReturn(resultDTO);

		ContactResponseDTO result = contactService.save(dto);

		assertEquals(resultDTO, result);
		verify(contactRepository).save(contact);
	}
	

	@Test
	void shouldUpdateContact_whenIdIsValid() {
	    AddressDTO addressDTO = new AddressDTO();
	    addressDTO.setRua("Rua A");
	    addressDTO.setCidade("Cidade X");
	    addressDTO.setEstado("SP");
	    addressDTO.setCep("12345-678");

	    updatedDTO.setAddresses(List.of(addressDTO));

	    Address address = new Address();
	    address.setRua("Rua A");
	    address.setCidade("Cidade X");
	    address.setEstado("SP");
	    address.setCep("12345-678");

	    when(contactRepository.findById(1L)).thenReturn(Optional.of(existingContact));
	    when(contactRepository.save(any(Contact.class))).thenReturn(updatedContact);

	    when(modelMapper.map(any(AddressDTO.class), eq(Address.class))).thenAnswer(invocation -> {
	        AddressDTO dto = invocation.getArgument(0);
	        Address addr = new Address();
	        addr.setRua(dto.getRua());
	        addr.setCidade(dto.getCidade());
	        addr.setEstado(dto.getEstado());
	        addr.setCep(dto.getCep());
	        return addr;
	    });

	    when(modelMapper.map(updatedContact, ContactResponseDTO.class)).thenReturn(expectedDTO);

	    ContactResponseDTO result = contactService.updateContact(1L, updatedDTO);

	    assertEquals(expectedDTO.getNome(), result.getNome());
	    assertEquals(expectedDTO.getEmail(), result.getEmail());
	    assertEquals(expectedDTO.getTelefone(), result.getTelefone());

	    verify(contactRepository).save(any(Contact.class));
	}


	@Test
	void shouldThrowException_whenUpdatingNonExistentContact() {
		when(contactRepository.findById(1L)).thenReturn(Optional.empty());

		ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
			contactService.updateContact(1L, updatedDTO);
		});

		verify(contactRepository).findById(1L);
		assertEquals("Contato não encontrado: 1", ex.getMessage());
	}

	@Test
	void shouldPartiallyUpdateContact_whenOnlySomeFieldsProvided() {
		Map<String, String> updates = new HashMap<>();
		updates.put("email", "atualizado@email.com");

		existingContact.setEmail(updates.get("email")); // simula atualização do campo

		Contact savedPartialContact = existingContact;

		ContactResponseDTO expectedPartialDTO = new ContactResponseDTO();
		expectedPartialDTO.setNome(existingContact.getNome()); // nome não mudou
		expectedPartialDTO.setEmail(existingContact.getEmail()); // email atualizado
		expectedPartialDTO.setTelefone(existingContact.getTelefone());

		// Conversão de Address → AddressDTO
		List<AddressDTO> addressDTOs = existingContact.getAddresses().stream()
		    .map(address -> modelMapper.map(address, AddressDTO.class))
		    .collect(Collectors.toList());
		expectedPartialDTO.setAddresses(addressDTOs);

		when(contactRepository.findById(1L)).thenReturn(Optional.of(existingContact));
		when(contactRepository.save(any(Contact.class))).thenReturn(savedPartialContact);
		when(contactService.convertToResponseDTO(savedPartialContact)).thenReturn(expectedPartialDTO);

		ContactResponseDTO result = contactService.updateContactPartial(1L, updates);

		assertEquals(expectedPartialDTO.getEmail(), result.getEmail());
		assertEquals(expectedPartialDTO.getNome(), result.getNome());
		assertEquals(expectedPartialDTO.getTelefone(), result.getTelefone());
	}

	@Test
	void shouldThrowException_whenPartiallyUpdatingNonExistentContact() {
		Long nonExistentId = 99L;

		when(contactRepository.findById(nonExistentId)).thenReturn(Optional.empty());

		Map<String, String> updates = new HashMap<>();
		updates.put("email", "novo@email.com");

		ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
			contactService.updateContactPartial(nonExistentId, updates);
		});

		verify(contactRepository).findById(nonExistentId);
		assertEquals("Contato não encontrado: 99", ex.getMessage());
	}

	@Test
	void shouldThrowException_whenUpdatingWithInvalidField() {
		Long id = 1L;
		when(contactRepository.findById(id)).thenReturn(Optional.of(existingContact));

		Map<String, String> wrongUpdates = new HashMap<>();
		wrongUpdates.put("idade", "30");

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
			contactService.updateContactPartial(id, wrongUpdates);
		});

		verify(contactRepository).findById(id);
		assertEquals("Campo não suportado: idade", ex.getMessage());
	}

	@Test
	void shouldDeleteContact_whenIdExists() {
		when(contactRepository.existsById(1L)).thenReturn(true);
		contactService.deleteContact(1L);
		verify(contactRepository).deleteById(1L);

	}

	@Test
	void shouldThrowException_whenDeletingNonExistentContact() {
		when(contactRepository.existsById(1L)).thenReturn(false);

		ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
			contactService.deleteContact(1L);
		});

		verify(contactRepository, never()).deleteById(any());
		assertEquals("Contato não encontrado: 1", ex.getMessage());
	}

}

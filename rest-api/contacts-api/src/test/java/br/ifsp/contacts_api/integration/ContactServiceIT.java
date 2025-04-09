package br.ifsp.contacts_api.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import br.ifsp.contacts_api.dto.AddressDTO;
import br.ifsp.contacts_api.dto.ContactRequestDTO;
import br.ifsp.contacts_api.dto.ContactResponseDTO;
import br.ifsp.contacts_api.exception.ResourceNotFoundException;
import br.ifsp.contacts_api.service.ContactService;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
@Transactional
class ContactServiceIT {

    @Autowired
    private ContactService contactService;

    private ContactRequestDTO contactDTO;
    private ContactResponseDTO saved;

    @BeforeEach
    void setUp() {
    	
    	AddressDTO addressDTO = new AddressDTO();
        addressDTO.setRua("Rua B");
        addressDTO.setCidade("São Paulo");
        addressDTO.setEstado("SP");
        addressDTO.setCep("11111-111");

        List<AddressDTO> addressDTOList = new ArrayList<>();
        addressDTOList.add(addressDTO);

        contactDTO = new ContactRequestDTO();
        contactDTO.setNome("João Silva");
        contactDTO.setEmail("joao@email.com");
        contactDTO.setTelefone("123456789");
        contactDTO.setAddresses(addressDTOList);

        saved = contactService.save(contactDTO);
    }

    @Test
    void shouldSaveAndRetrieveContact() {
     
        assertNotNull(saved.getId());
        ContactResponseDTO found = contactService.getContactById(saved.getId());

        assertEquals("João Silva", found.getNome());
        assertEquals("joao@email.com", found.getEmail());
        assertEquals("123456789", found.getTelefone());
    }

    @Test
    void shouldReturnAllContactsPaged() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ContactResponseDTO> result = contactService.getAllContacts(pageable);
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldSearchContactsByName() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<ContactResponseDTO> result = contactService.searchContactsByName("João", pageable);

        assertFalse(result.isEmpty());
        assertEquals("João Silva", result.getContent().get(0).getNome());
    }

    @Test
    void shouldUpdateContactCompletely() {
    	
        ContactRequestDTO updated = new ContactRequestDTO();
        updated.setNome("João Atualizado");
        updated.setEmail("novo@email.com");
        updated.setTelefone("987654321");

        ContactResponseDTO result = contactService.updateContact(saved.getId(), updated);

        assertEquals("João Atualizado", result.getNome());
        assertEquals("novo@email.com", result.getEmail());
        assertEquals("987654321", result.getTelefone());
    }

    @Test
    void shouldUpdateContactPartially() {

        Map<String, String> updates = new HashMap<>();
        updates.put("telefone", "999999999");

        ContactResponseDTO result = contactService.updateContactPartial(saved.getId(), updates);

        assertEquals("999999999", result.getTelefone());
        assertEquals("João Silva", result.getNome()); 
    }

    @Test
    void shouldThrowExceptionWhenUpdatingPartialWithInvalidField() {	

        Map<String, String> updates = new HashMap<>();
        updates.put("campoInvalido", "teste");

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContactPartial(saved.getId(), updates);
        });
    }

    @Test
    void shouldDeleteContact() {
    
        contactService.deleteContact(saved.getId());

        assertThrows(ResourceNotFoundException.class, () -> {
            contactService.getContactById(saved.getId());
        });
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonexistentContact() {
        assertThrows(ResourceNotFoundException.class, () -> {
            contactService.deleteContact(9999L);
        });
    }

    @Test
    void shouldThrowExceptionWhenFetchingNonexistentContact() {
        assertThrows(ResourceNotFoundException.class, () -> {
            contactService.getContactById(1234L);
        });
    }
}

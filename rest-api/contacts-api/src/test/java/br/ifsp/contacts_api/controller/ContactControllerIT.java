package br.ifsp.contacts_api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ifsp.contacts_api.dto.AddressDTO;
import br.ifsp.contacts_api.dto.ContactRequestDTO;
import br.ifsp.contacts_api.service.ContactService;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
@Transactional
class ContactControllerIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ContactService contactService;
	
	private Long contactId;
	
	private List<AddressDTO> addresses = new ArrayList<>();
	
	@BeforeEach
	void setup() {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setRua("Rua A");
		addressDTO.setCidade("Campinas");
		addressDTO.setEstado("SP");
		addressDTO.setCep("13000-000");

		
		addresses.add(addressDTO);
		ContactRequestDTO contactDTO = new ContactRequestDTO();
		contactDTO.setNome("Maria Teste");
		contactDTO.setEmail("maria@email.com");
		contactDTO.setTelefone("123456789");
		contactDTO.setAddresses(addresses); 

		contactId = contactService.save(contactDTO).getId();		
	}
	
	@Test
	void shouldReturnAllContactsPaginated() throws Exception {
		mockMvc.perform(get("/api/contacts").param("page", "0").param("size", "10"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.content").isArray())
		.andExpect(jsonPath("$.content[0].nome").value("Maria Teste"));
	}
	
	@Test
	void shouldReturnContactByIdWhenExists() throws Exception {
		mockMvc.perform(get("/api/contacts/{id}", contactId))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.nome").value("Maria Teste"));
	}
	
	@Test
	void shouldReturn404WhenContactDoesNotExist() throws Exception {	
		ContactRequestDTO request = new ContactRequestDTO();
		mockMvc.perform(get("/api/contacts/{id}", 11L)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isNotFound());		
	}
	
	@Test
	void shouldReturnContactsMatchingSearchTerm() throws Exception{
		mockMvc.perform(get("/api/contacts/search").param("name", "Maria")
			.param("page", "0").param("size", "10"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content").isArray())
			.andExpect(jsonPath("$.content[0].nome").value("Maria Teste"));
	}
	
	@Test
	void shouldReturnEmptyListWhenNoMatchesFound() throws Exception {
		mockMvc.perform(get("/api/contacts/search").param("name", "João")
				.param("page", "0").param("size", "10"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isEmpty());
	}
	
	@Test
	void shouldCreateNewContactSuccessfully() throws JsonProcessingException, Exception {
		AddressDTO address = new AddressDTO();
		address.setRua("Rua B");
		address.setCidade("Campos");
		address.setEstado("SP");
		address.setCep("13222-000");

		List<AddressDTO> addresses = new ArrayList<>();
		addresses.add(address);

		ContactRequestDTO contact = new ContactRequestDTO();
		contact.setNome("Pedro Vlogs");
		contact.setEmail("pedro@email.com");
		contact.setTelefone("123451111");
		contact.setAddresses(addresses); 
		
		mockMvc.perform(post("/api/contacts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(contact)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.nome").value("Pedro Vlogs"))
				.andExpect(jsonPath("$.email").value("pedro@email.com"))
				.andExpect(jsonPath("$.telefone").value("123451111"));
	}
	
	@Test
	void shouldReturn400WhenCreatingContactWithInvalidData() throws JsonProcessingException, Exception {
		ContactRequestDTO contact = new ContactRequestDTO();
		contact.setNome("Pedro Vlogs");
		contact.setEmail("pedro@email.com");
		contact.setTelefone("123451111");
		
		mockMvc.perform(post("/api/contacts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(contact)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	void shouldUpdateExistingContact() throws JsonProcessingException, Exception {
		ContactRequestDTO contact = new ContactRequestDTO();
		contact.setNome("Oscar Ruiz");
		contact.setEmail("oscar@email.com");
		contact.setTelefone("123453232");
		contact.setAddresses(addresses);
		
		mockMvc.perform(put("/api/contacts/{id}", contactId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(contact)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value("Oscar Ruiz"))
				.andExpect(jsonPath("$.email").value("oscar@email.com"));
	}
	
	@Test
	void shouldReturn400WhenUpdatingWithInvalidData() throws JsonProcessingException, Exception {
		ContactRequestDTO contact = new ContactRequestDTO();
		contact.setNome("Oscar Ruiz");
		contact.setEmail("oscar@email.com");
		contact.setTelefone("123453232");
		
		mockMvc.perform(put("/api/contacts/{id}", contactId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(contact)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	void shouldReturn404WhenUpdatingNonexistentContact() throws JsonProcessingException, Exception {
		ContactRequestDTO contact = new ContactRequestDTO();
		contact.setNome("Oscar Ruiz");
		contact.setEmail("oscar@email.com");
		contact.setTelefone("123453232");
		contact.setAddresses(addresses);
		
		mockMvc.perform(put("/api/contacts/{id}", 99L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(contact)))
				.andExpect(status().isNotFound());
	}

	@Test
	void shouldPartiallyUpdateContact() throws JsonProcessingException, Exception {
				
		Map<String, String> updates = new HashMap<>();
		updates.put("nome", "Carlos Atualizado");
		updates.put("email", "carlosnovo@email.com");

		mockMvc.perform(patch("/api/contacts/{id}", contactId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updates)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value("Carlos Atualizado"))
				.andExpect(jsonPath("$.email").value("carlosnovo@email.com"));
	}
	
	@Test
	void shouldReturn404WhenPartiallyUpdatingNonexistentContact() throws JsonProcessingException, Exception {
				
		Map<String, String> updates = new HashMap<>();
		updates.put("nome", "Carlos Atualizado");
		updates.put("email", "carlosnovo@email.com");

		mockMvc.perform(patch("/api/contacts/{id}", 22L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updates)))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void shouldDeleteContactSuccessfully() throws Exception {
		 mockMvc.perform(delete("/api/contacts/{id}", contactId))
         	.andExpect(status().isNoContent());
				
	}
	
	@Test
	void shouldReturn404WhenDeletingNonexistentContact() throws Exception {
	    mockMvc.perform(delete("/api/contacts/{id}", 9999L))
	           .andExpect(status().isNotFound());
	}

	
}

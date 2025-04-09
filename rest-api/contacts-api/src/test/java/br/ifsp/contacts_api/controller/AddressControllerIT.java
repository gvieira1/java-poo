package br.ifsp.contacts_api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

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
class AddressControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ContactService contactService;

	private Long contactId;
	private AddressDTO addressDTO;

	@BeforeEach
	void setUp() {
		
		addressDTO = new AddressDTO();
		addressDTO.setRua("Rua A");
		addressDTO.setCidade("Campinas");
		addressDTO.setEstado("SP");
		addressDTO.setCep("13000-000");

		List<AddressDTO> addresses = new ArrayList<>();
		addresses.add(addressDTO);

		ContactRequestDTO contactDTO = new ContactRequestDTO();
		contactDTO.setNome("Maria Teste");
		contactDTO.setEmail("maria@email.com");
		contactDTO.setTelefone("123456789");
		contactDTO.setAddresses(addresses); 

		contactId = contactService.save(contactDTO).getId();
	}

	@Test
	void shouldCreateAddressForExistingContact() throws Exception {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setRua("Rua X");
		addressDTO.setCidade("Campinas");
		addressDTO.setEstado("SP");
		addressDTO.setCep("13000-000");

		mockMvc.perform(post("/api/addresses/contacts/{contactId}", contactId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(addressDTO)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.rua").value("Rua X"))
				.andExpect(jsonPath("$.cidade").value("Campinas"))
				.andExpect(jsonPath("$.estado").value("SP"))
				.andExpect(jsonPath("$.cep").value("13000-000"));
	}

	@Test
	void shouldReturnAddressesByContact() throws Exception {

		mockMvc.perform(post("/api/addresses/contacts/{contactId}", contactId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(addressDTO)))
				.andExpect(status().isCreated());

		mockMvc.perform(get("/api/addresses/contacts/{contactId}", contactId).param("page", "0").param("size", "10"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray())
				.andExpect(jsonPath("$.content[0].rua").value("Rua A"));
	}

	@Test
	void shouldReturn404WhenCreatingAddressForNonexistentContact() throws Exception {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setRua("Rua Z");
		addressDTO.setCidade("Curitiba");
		addressDTO.setEstado("PR");
		addressDTO.setCep("80000-000");

		mockMvc.perform(post("/api/addresses/contacts/{contactId}", 9999L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(addressDTO)))
				.andExpect(status().isNotFound());
	}

	@Test
	void shouldReturn400WhenCreatingAddressWithInvalidData() throws Exception {
		AddressDTO addressDTO = new AddressDTO(); 

		mockMvc.perform(post("/api/addresses/contacts/{contactId}", contactId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(addressDTO)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.cep").exists())
				.andExpect(jsonPath("$.rua").exists());
	}
}

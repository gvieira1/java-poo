package br.ifsp.contacts_api.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.contacts_api.dto.ContactRequestDTO;
import br.ifsp.contacts_api.dto.ContactResponseDTO;
import br.ifsp.contacts_api.repository.ContactRepository;
import br.ifsp.contacts_api.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/contacts")
@Validated
public class ContactController {

    
    @Autowired
	public ContactRepository contactRepository;

    @Autowired
    private ContactService contactService;

    
	@Operation(summary = "Get all contacts")
    @GetMapping
	public ResponseEntity<Page<ContactResponseDTO>> getAllContacts(Pageable pageable) {
		return ResponseEntity.ok(contactService.getAllContacts(pageable));
	}

    
	@Operation(summary = "Get a contact by its ID")
	@GetMapping("/{id}")
    public ContactResponseDTO getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

	@Operation(summary = "Search for a contact based on the provided parameter")
	@GetMapping("/search")
    public ResponseEntity<Page<ContactResponseDTO>> searchContactsByName(@RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(contactService.searchContactsByName(name, pageable));
    }
   

    @Operation(summary = "Create a new contact")
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContactResponseDTO> createContact(@Valid @RequestBody ContactRequestDTO contactDTO) {
    	ContactResponseDTO savedContact = contactService.save(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    
    @Operation(summary = "Update a contact by its ID")
    @PutMapping("/{id}")
    public ContactResponseDTO updateContact(@PathVariable Long id, @Valid @RequestBody ContactRequestDTO updatedContact) {
        return contactService.updateContact(id, updatedContact);
    }


    @Operation(summary = "Partially update a contact")
    @PatchMapping("/{id}")
    public ContactResponseDTO updateContactPartial(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        return contactService.updateContactPartial(id, updates);
    }


    @Operation(summary = "Delete a contact by its ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
    	contactService.deleteContact(id);
    }
}

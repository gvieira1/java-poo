package br.ifsp.contacts_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.contacts_api.dto.AddressDTO;
import br.ifsp.contacts_api.service.AddressService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    
    @Autowired
    private AddressService addressService;

    // Endpoint para adicionar um novo endereço a um contato existente. 
    @PostMapping("/contacts/{contactId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AddressDTO> createAddress(@PathVariable Long contactId, @RequestBody @Valid AddressDTO addressDTO) {
    	AddressDTO savedAddress = addressService.save(contactId, addressDTO);
    	return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    // Endpoint para listar todos os endereços de um contato específico.
    @GetMapping("/contacts/{contactId}")
    public List<AddressDTO> getAddressesByContact(@PathVariable Long contactId) {
        return addressService.getAddressesByContact(contactId);
    }
}
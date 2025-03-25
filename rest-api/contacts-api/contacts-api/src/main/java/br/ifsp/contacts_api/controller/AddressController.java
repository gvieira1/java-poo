package br.ifsp.contacts_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.contacts_api.model.Address;
import br.ifsp.contacts_api.model.Contact;
import br.ifsp.contacts_api.repository.AddressRepository;
import br.ifsp.contacts_api.repository.ContactRepository;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    // Endpoint para adicionar um novo endereço a um contato existente.
    @PostMapping("/{contactId}/addresses")
    public ResponseEntity<Address> addAddress(@PathVariable Long contactId, @RequestBody Address address) {
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        if (!contactOptional.isPresent()) {
            return ResponseEntity.notFound().build(); //código 404 npt found
        }
        // Define o contato para o endereço e salva
        Contact contact = contactOptional.get();
        address.setContact(contact);
        Address savedAddress = addressRepository.save(address);
        return ResponseEntity.ok(savedAddress);
    }

    // Endpoint para listar todos os endereços de um contato específico.
    @GetMapping("/{contactId}/addresses")
    public ResponseEntity<List<Address>> getAddressesByContact(@PathVariable Long contactId) {
        List<Address> addresses = addressRepository.findByContactId(contactId);
        return ResponseEntity.ok(addresses);
    }
}
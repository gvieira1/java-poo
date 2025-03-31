package br.ifsp.contacts_api.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.ifsp.contacts_api.dto.ContactDTO;
import br.ifsp.contacts_api.repository.ContactRepository;
import br.ifsp.contacts_api.service.ContactService;
import jakarta.validation.Valid;
 
/**
 * Classe responsável por mapear as rotas/endpoints relacionados
 * aos contatos. Cada método abaixo corresponde a uma operação
 * em nosso sistema.
 *
 * @RestController: Indica que esta classe é um controlador
 *                  responsável por responder requisições REST.
 * @RequestMapping("/api/contacts"): Indica o caminho base.
 */
@RestController
@RequestMapping("/api/contacts")
@Validated
public class ContactController {

    /**
     * @Autowired permite que o Spring "injete" automaticamente
     * uma instância de ContactRepository aqui,
     * sem que precisemos criar manualmente.
     */
    @Autowired
	public ContactRepository contactRepository;
    
    @Autowired
    private ContactService contactService;

    /**
	 * Método para obter todos os contatos.
	 *
	 * @GetMapping indica que este método vai responder a chamadas HTTP GET.
	 * Exemplo de acesso: GET /api/contacts
	 */
	@GetMapping
	public List<ContactDTO> getAllContacts() {
		return contactService.getAllContacts();
	}

    /**
     * Método para obter um contato específico pelo seu ID.
     *
     * @PathVariable "amarra" a variável {id} da URL
     * ao parâmetro do método.
     * Exemplo de acesso: GET /api/contacts/1
     */
    @GetMapping("/{id}")
    public ContactDTO getContactById(@PathVariable Long id) {
        // findById retorna um Optional, então usamos orElseThrow
        return contactService.getContactById(id);
    }

    @GetMapping("/search")
    public List<ContactDTO> searchContactsByName(@RequestParam String name) {
        // Chama o método no repositório para buscar os contatos pelo nome
        return contactService.searchContactsByName(name);
    }

    /**
     * Método para criar um novo contato.
     *
     * @PostMapping indica que este método responde a chamadas HTTP POST.
     * @RequestBody indica que o objeto Contact será preenchido
     * com os dados JSON enviados no corpo da requisição.
     */
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactDTO contactDTO) { //adicionar validação com @Valid
        ContactDTO savedContact = contactService.save(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    /**
     * Método para atualizar um contato existente.
     *
     * @PutMapping indica que este método responde a chamadas HTTP PUT.
     * Exemplo de acesso: PUT /api/contacts/1
     */  
    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable Long id, @Valid @RequestBody ContactDTO updatedContact) {
        return contactService.updateContact(id, updatedContact);
    }


	/*
	 * @PatchMapping("/{id}") public ResponseEntity<Contact>
	 * updatePartialContact(@PathVariable Long id, @RequestBody Contact
	 * updatedContact) { // Buscar o contato existente Optional<Contact>
	 * existingContact = contactRepository.findById(id);
	 * 
	 * //uso de Optional para verificação de id válido if
	 * (existingContact.isEmpty()) { return ResponseEntity.notFound().build(); }
	 * 
	 * // Atualizar os campos se existirem Contact contact = existingContact.get();
	 * if (updatedContact.getNome() != null) {
	 * contact.setNome(updatedContact.getNome()); } if (updatedContact.getTelefone()
	 * != null) { contact.setTelefone(updatedContact.getTelefone()); } if
	 * (updatedContact.getEmail() != null) {
	 * contact.setEmail(updatedContact.getEmail()); }
	 * 
	 * // Salvar alterações Contact updated = contactRepository.save(contact);
	 * return ResponseEntity.ok(updated);
	 * 
	 * }
	 */
    @PatchMapping("/{id}")
    public ContactDTO updateContactPartial(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        return contactService.updateContactPartial(id, updates);
    }


    /**
     * Método para excluir um contato pelo ID.
     *
     * @DeleteMapping indica que este método responde a chamadas HTTP DELETE.
     * Exemplo de acesso: DELETE /api/contacts/1
     */
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
    	contactService.deleteContact(id);
    }
}

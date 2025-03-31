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

import br.ifsp.contacts_api.dto.ContactDTO;
import br.ifsp.contacts_api.repository.ContactRepository;
import br.ifsp.contacts_api.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
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
	@Operation(summary = "Get all contacts")
    @GetMapping
	public ResponseEntity<Page<ContactDTO>> getAllContacts(Pageable pageable) {
		return ResponseEntity.ok(contactService.getAllContacts(pageable));
	}

    /**
     * Método para obter um contato específico pelo seu ID.
     *
     * @PathVariable "amarra" a variável {id} da URL
     * ao parâmetro do método.
     * Exemplo de acesso: GET /api/contacts/1
     */
	@Operation(summary = "Get a contact by its ID")
	@GetMapping("/{id}")
    public ContactDTO getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

	@Operation(summary = "Search for a contact based on the provided parameter")
	@GetMapping("/search")
    public ResponseEntity<Page<ContactDTO>> searchContactsByName(@RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(contactService.searchContactsByName(name, pageable));
    }

    /**
     * Método para criar um novo contato.
     *
     * @PostMapping indica que este método responde a chamadas HTTP POST.
     * @RequestBody indica que o objeto Contact será preenchido
     * com os dados JSON enviados no corpo da requisição.
     */

    @Operation(summary = "Create a new contact")
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
    @Operation(summary = "Update a contact by its ID")
    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable Long id, @Valid @RequestBody ContactDTO updatedContact) {
        return contactService.updateContact(id, updatedContact);
    }


    @Operation(summary = "Partially update a contact")
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
    @Operation(summary = "Delete a contact by its ID")
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
    	contactService.deleteContact(id);
    }
}

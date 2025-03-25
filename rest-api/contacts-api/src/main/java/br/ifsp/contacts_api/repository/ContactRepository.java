package br.ifsp.contacts_api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsp.contacts_api.model.Contact;

/**
 * Esta interface extende JpaRepository, que nos fornece métodos prontos
 * para acessar e manipular dados no banco de dados. Basta especificar
 * a classe de entidade (Contact) e o tipo da chave primária (Long).
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

	//usa convenção de métodos de pesquisa do JPA para retornar itens que contem o item pesquisado ignorando maiúsculas e retornando
	//lista vazia em caso de não obter resultado
	List<Contact> findByNomeContainingIgnoreCase(String nome);
}

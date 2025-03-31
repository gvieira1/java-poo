package br.ifsp.contacts_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifsp.contacts_api.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // Método para buscar todos os endereços de um contato específico, usando o ID do contato.
    List<Address> findByContactId(Long contactId);
}
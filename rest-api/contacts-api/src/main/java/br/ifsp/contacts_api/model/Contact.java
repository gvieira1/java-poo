package br.ifsp.contacts_api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Classe que representa o modelo de dados para um Contato.
 *
 * @Entity indica que este objeto será mapeado para uma tabela
 * no banco de dados (em cenários de persistência com JPA).
 */
@Entity
public class Contact {

    /**
     * @Id indica que este campo é a chave primária (primary key) da entidade.
     * @GeneratedValue permite que o banco de dados (ou o provedor JPA)
     * gere automaticamente um valor único para cada novo registro.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio") // Garante que o campo não seja vazio
    private String nome;

    @Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 caracteres") // Define o tamanho mínimo e máximo
    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "\\d+", message = "O telefone deve conter apenas números")
    private String telefone;

    @Email(message = "O e-mail deve ter um formato válido") // Garante que seja um e-mail válido
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @NotEmpty(message = "O contato deve ter pelo menos um endereço")
    private List<Address> addresses = new ArrayList<>();

    // Construtor vazio exigido pelo JPA
    public Contact() {}

    // Construtor para facilitar a criação de objetos
    public Contact(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

 
    public void setAddresses(List<Address> list) {
        if (list != null) {
            if (this.addresses == null) {
                this.addresses = new ArrayList<>();
            }

            this.addresses.clear();
            this.addresses.addAll(list);
        }
    }

	

}

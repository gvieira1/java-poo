package br.ifsp.contacts_api.dto;

import java.util.ArrayList;
import java.util.List;

import br.ifsp.contacts_api.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ContactDTO {

	@NotBlank(message = "O nome não pode estar vazio")
	private String nome;

	@Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 caracteres")
	@NotBlank(message = "O telefone é obrigatório")
	@Pattern(regexp = "\\d+", message = "O telefone deve conter apenas números")
	private String telefone;

	@Email(message = "O e-mail deve ter um formato válido")
	@NotBlank(message = "O e-mail é obrigatório")
	private String email;

	@NotEmpty(message = "O contato deve ter pelo menos um endereço")
	private List<Address> addresses = new ArrayList<>();

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

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}

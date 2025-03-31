package br.ifsp.contacts_api.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.ifsp.contacts_api.model.Contact;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddressDTO {

	    @NotBlank(message = "O campo rua não pode estar vazio")
	    private String rua;
	    
	    @NotBlank(message = "O campo cidade não pode estar vazio")
	    private String cidade;
	    
	    @NotBlank(message = "O campo estado não pode estar vazio")
	    @Size(min = 2, max = 2, message = "O campo estado deve ter exatamente 2 caracteres (sigla)")
	    @Pattern(regexp = "[A-Z]{2}", message = "O estado deve ser representado por duas letras maiúsculas")
	    private String estado;
	    
	    @NotBlank(message = "O CEP não pode estar vazio")
	    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 99999-999")
	    private String cep;

	    @JsonBackReference
	    private Contact contact;

		public String getRua() {
			return rua;
		}

		public void setRua(String rua) {
			this.rua = rua;
		}

		public String getCidade() {
			return cidade;
		}

		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public String getCep() {
			return cep;
		}

		public void setCep(String cep) {
			this.cep = cep;
		}

		public Contact getContact() {
			return contact;
		}

		public void setContact(Contact contact) {
			this.contact = contact;
		}
	    
	    
}

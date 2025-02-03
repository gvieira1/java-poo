package search_set;

import java.util.Objects;

public class Contato {
	
	private String nome;
	private int numero;
	
	public Contato(String nome, int numero) {
		super();
		this.nome = nome;
		this.numero = numero;
	}
	
	public Contato() {
	}

	@Override
	public String toString() {
		return "Contato [nome=" + nome + ", numero=" + numero + "]";
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNome());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		return Objects.equals(nome, other.getNome());
	}
	
	
	
	
	
}

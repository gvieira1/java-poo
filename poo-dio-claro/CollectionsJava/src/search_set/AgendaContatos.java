package search_set;

import java.util.HashSet;
import java.util.Set;

public class AgendaContatos {
	
	private Set<Contato> contato;

	public AgendaContatos() {
		this.contato = new HashSet<>();
	}
	
	public void adicionarContato(String nome, int numero) {
		contato.add(new Contato(nome, numero));
	}
	
	public void exibirContatos() {
		System.out.println(contato);
	}
	
	
	public Set<Contato> pesquisarPorNome(String nome) {
		Set<Contato> contatosPorNome = new HashSet<>();
		if (!contato.isEmpty()) {
			for (Contato c : contato) {
				if (c.getNome().startsWith(nome)) {
					contatosPorNome.add(c);
				}
			}
			return contatosPorNome;
		} else {
			throw new RuntimeException("O conjunto está vazio!");
		}
	}
	
	public Contato atualizarNumeroContato(String nome, int novoNumero) {
		Contato contatoAtualizado = null;
	    if (!contato.isEmpty()){
	      for (Contato c : contato) {
	        if (c.getNome().equalsIgnoreCase(nome)) {
	          c.setNumero(novoNumero);
	          contatoAtualizado = c;
	          break;
	        }
	      }
	      return contatoAtualizado;
		} else {
			throw new RuntimeException("O conjunto está vazio!");
		}
	}
	
}

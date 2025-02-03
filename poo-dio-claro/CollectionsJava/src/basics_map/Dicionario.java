package basics_map;

import java.util.HashMap;
import java.util.Map;

public class Dicionario {

	Map<String, String> dicionario;

	public Dicionario() {
		this.dicionario = new HashMap<>();
	}
	
	public Map<String, String> getDicionario() {
		return dicionario;
	}
	
	public void adicionarPalavra(String palavra, String definicao) {
		dicionario.put(palavra, definicao);
	}
	
	public void removerPalavra(String palavra) {
		if(!dicionario.isEmpty()) {
			dicionario.remove(palavra);
		} else {
			throw new RuntimeException("O conjunto está vazio!");
		}
	}
	

	public void exibirPalavras() {
		if (!dicionario.isEmpty()) {
			System.out.println(dicionario);
		} else {
			throw new RuntimeException("O conjunto está vazio!");
		}
	}
	
	public String pesquisarPorPalavra(String palavra) {
		String definicao = "";
		if (!dicionario.isEmpty()) {
			definicao = dicionario.get(palavra);
		} else {
			throw new RuntimeException("O conjunto está vazio!");
		}
		return definicao;
	}
	
	public static void main(String[] args) {
		Dicionario dicionario = new Dicionario();
		dicionario.adicionarPalavra("córrego", "pequeno rio com fluxo de água bastante tênue");
		dicionario.adicionarPalavra("lagoa", "depressão de pequena profundidade, contendo água doce ou salgada.");
		dicionario.adicionarPalavra("rio", "curso de água natural, mais ou menos torrencial, que corre de uma parte mais elevada para uma mais baixa e que deságua em outro rio, no mar ou num lago.");
	
		System.out.println("Palavras adicionadas: \n");
		dicionario.exibirPalavras();
		
		dicionario.removerPalavra("rio");
		
		System.out.println("Após remoção: \n");
		dicionario.exibirPalavras();
		
		System.out.println("Palavra pesquisada: \n");
		String pesquisa = dicionario.pesquisarPorPalavra("lagoa");
		System.out.println(pesquisa);
	}
}

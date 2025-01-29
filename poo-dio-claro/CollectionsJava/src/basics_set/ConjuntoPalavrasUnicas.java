package basics_set;

import java.util.HashSet;


public class ConjuntoPalavrasUnicas {
	
	private HashSet<String> palavrasUnicas;

	public ConjuntoPalavrasUnicas() {
		this.palavrasUnicas = new HashSet<>();
	}

	@Override
	public String toString() {
		return "ConjuntoPalavrasUnicas [palavrasUnicas=" + palavrasUnicas + "]";
	}
	
	public int size() {
	       return palavrasUnicas.size();
	}
	 
	public void adicionarPalavra(String palavra) {
		palavrasUnicas.add(palavra);
	}
	
	public void removerPalavra(String palavra) {
		if(!palavrasUnicas.isEmpty()) {
			String palavraParaRemover = "";
			for (String s: palavrasUnicas) {
				if (s == palavra) {
					palavraParaRemover = s;
				}
			}
			palavrasUnicas.remove(palavraParaRemover);
		} else {
			System.out.println("O conjunto está vazio!");
		}
	}
	
	public boolean verificarPalavra(String palavra) {
		if (palavrasUnicas.contains(palavra)) {
			return true;
		}
		System.out.println("Esta palavra não está no conjunto!");
		return false;
	}
	
	public void exibirPalavrasUnicas() {
		System.out.println(palavrasUnicas);
	}
	
}



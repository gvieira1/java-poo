package search_list;

import java.util.ArrayList;
import java.util.List;

public class SomaNumeros {
	private List<Integer> numeros;

	public SomaNumeros() {
		this.numeros = new ArrayList<>();
	}
	
	public void adicionarNumero(int numero) {
		numeros.add(numero);
	}
	
	public int calcularSoma() {
		if (!numeros.isEmpty()) {
			int soma = 0;
			for (int n : numeros) {
				soma += n;
			}
			return soma;
		} else {
			return 0;
		}
	}
	
	public int encontrarMaiorNumero() {
		if (!numeros.isEmpty()) {
			int maior = numeros.getFirst();
			for (int n : numeros) {
				if (n > maior) {
					maior = n;
				}
			}
			return maior;
		} else {
			return 0;
		}
	}
	
	public int encontrarMenorNumero() {
		if (!numeros.isEmpty()) {
			int menor = numeros.getFirst();
			for (int n : numeros) {
				if (n < menor) {
					menor = n;
				}
			}
			return menor;
		} else {
			return 0;
		}
	}
	
	public void exibirNumeros() {
		System.out.println(numeros);
	}
	
}

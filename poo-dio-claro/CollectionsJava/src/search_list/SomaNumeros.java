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
	
	public static void main(String[] args) {
		SomaNumeros soma = new SomaNumeros();
		soma.adicionarNumero(2);
		soma.adicionarNumero(7);
		soma.adicionarNumero(3);
		soma.adicionarNumero(19);
		
		int resultado = soma.encontrarMenorNumero();
		System.out.println(resultado);
	}
}

/*
 * Crie uma classe chamada "SomaNumeros" que possui uma lista de números
 * inteiros como atributo. Implemente os seguintes métodos:
 * 
 * adicionarNumero(int numero): Adiciona um número à lista de números.
 * calcularSoma(): Calcula a soma de todos os números na lista e retorna o
 * resultado. encontrarMaiorNumero(): Encontra o maior número na lista e retorna
 * o valor. encontrarMenorNumero(): Encontra o menor número na lista e retorna o
 * valor. exibirNumeros(): Retorna uma lista contendo todos os números presentes
 * na lista.
 */

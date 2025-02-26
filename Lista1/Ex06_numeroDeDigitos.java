//Determine	o	número	de	dígitos	de	um	número	informado.

import java.util.Scanner;

public class Ex06_numeroDeDigitos {
	public static void main(String[] args) {
		Integer digito = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira um número e descubra quantos dígitos ele possui: ");
		digito = scanner.nextInt();
		String contagem = digito.toString();
		System.out.println("O número fornecido tem " + contagem.length() + " dígitos");
		scanner.close();
		}
}

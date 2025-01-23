package contador;

import java.util.Scanner;
import exception.ParametrosInvalidosException;

public class Contador {
	
	public static void main(String[] args) {
		
		Scanner terminal = new Scanner(System.in);
		
		System.out.println("Digite o primeiro parâmetro");
		int parametroUm = terminal.nextInt();
		System.out.println("Digite o segundo parâmetro");
		int parametroDois = terminal.nextInt();
		
		try {
			contar(parametroUm, parametroDois);
		}catch (ParametrosInvalidosException e) {
			System.out.println("Mensagem: " + e.getMessage());
		}
		
		terminal.close();
		
	}
	static void contar(int parametroUm, int parametroDois ) throws ParametrosInvalidosException {
		
		if (parametroUm > parametroDois) {
			throw new ParametrosInvalidosException();
		}
		
		for (int i = parametroUm; i <= parametroDois; i++) {
			System.out.println("Imprimindo o número " + i);
		}
	}
}
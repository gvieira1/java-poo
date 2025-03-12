import java.util.Scanner;

//Leia três números reais fornecidos pelo usuário. 
//Descubra qual deles é o menor de todos, imprimindo seu valor.

public class Ex09_menorDeTodos {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Insira três valores reais: ");
		Double valor1 = teclado.nextDouble();
		Double valor2 = teclado.nextDouble();
		Double valor3 = teclado.nextDouble();
		
		
		if (valor1 < valor2 && valor1 < valor3) {
			System.out.println("Menor valor é " + valor1);
		} else if (valor2 < valor3) {
			System.out.println("Menor valor é " + valor2);
		} else {
			System.out.println("Menor valor é " + valor3);
		}
		
		teclado.close();
	}
}

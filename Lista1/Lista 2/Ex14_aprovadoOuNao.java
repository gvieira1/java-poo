import java.util.Scanner;

/*Escreva um programa que leia 3 notas (valores reais), calculando e exibindo sua média aritmética.
* Imprima também"Aprovado" se a média for maior que 7, "Reprovado" se for menor que 3 
* e "Exame" se estiver entre 3 e 7.*/

public class Ex14_aprovadoOuNao {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Insira três valores reais: ");
		Double valor1 = teclado.nextDouble();
		Double valor2 = teclado.nextDouble();
		Double valor3 = teclado.nextDouble();
		
		Double media = (valor1 + valor2 + valor3) / 3;
		
		if (media > 7) {
			System.out.println("Aprovado");
		} else if (media < 3) {
			System.out.println("Reprovado");
		} else {
			System.out.println("Exame");
		}
		
		teclado.close();
	}
}

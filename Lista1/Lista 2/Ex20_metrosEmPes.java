import java.util.Scanner;

/*Escreva um programa que leia um valor real correspondente a uma medida em metros, convertendo
o valor dado em pés (1 metro = 3.315 pés), exibindo os valores dado e convertido. 
Caso o usuário forneça um valor negativo, deve ser exibida
uma mensagem e a operação de conversão não deve ser efetuada.*/

public class Ex20_metrosEmPes {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Insira uma medida real em metros: ");
		Double valor = teclado.nextDouble();
		
		if (valor < 0) 
			System.out.println("Insira um valor maior que zero!");
		
		Double pes = valor * 3.28;
		
		System.out.println(valor + " metros equivalem a " + pes + " pes");
		teclado.close();
	}
}

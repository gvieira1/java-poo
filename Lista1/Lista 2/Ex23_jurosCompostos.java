/* Escreva um programa que receba as seguintes informações: um valor real indicando capital inicial PV, um valor
 real que corresponde a taxa de juros da aplicação J e um número inteiro de períodos da aplicação N. 
 O programa deve retornar o capital futuro FV dado pela relação abaixo: FV = PV * ( 1 + J )N */

import java.util.Scanner;

public class Ex23_jurosCompostos {
	public static void main(String[] args) {
		
		float capitalInicial = 0.0f;
		float taxaDeJuros = 0.0f;
		int tempoAplicacao = 0;
		float capitalFuturo = 0.0f;
		
		Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o valor do capital inicial PV: ");
        capitalInicial = scanner.nextFloat();
        System.out.print("Digite o valor da taxa de juros J: ");
        taxaDeJuros = scanner.nextFloat();
        System.out.print("Digite o período de aplicação N: ");
        tempoAplicacao = scanner.nextInt();
		
        capitalFuturo = (int) (capitalInicial *  Math.pow((1 + taxaDeJuros),tempoAplicacao));
		
		System.out.println("\nO capital futuro será de: " + capitalFuturo + " reais");
		
		scanner.close();
	}
}

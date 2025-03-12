import java.util.Scanner;

/*Considerando um objeto móvel em movimento uniformemente variado, escreva um programa que receba as 
seguintes informações: um valor real indicando posição inicial do móvel P0, um valor real que 
corresponde a velocidade do móvel V, um outro valor real A correspondente a aceleração do móvel e 
um número inteiro correspondente ao tempo decorrido T. O programa deve calcular a posição final PF
do móvel, dado pela relação abaixo: PF = P0 + V * T + (A * T2) / 2*/

public class Ex22_posicaoFinalObjeto {
	public static void main(String[] args) {
		float posicaoInicial = 0.0f;
		float velocidadeDoMovel = 0.0f;
		float aceleracaoDoMovel = 0.0f;
		int tempoDecorrido = 0;
		float posicaoFinal = 0.0f;
		
		Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o valor da posição inicial P0: ");
        posicaoInicial = scanner.nextFloat();
        System.out.print("Digite o valor da velocidade do móvel V: ");
        velocidadeDoMovel = scanner.nextFloat();
        System.out.print("Digite o valor da aceleração A: ");
        aceleracaoDoMovel = scanner.nextFloat();
        System.out.print("Digite o valor do tempo decorrido T: ");
        tempoDecorrido = scanner.nextInt();
		
		posicaoFinal = posicaoInicial + velocidadeDoMovel * tempoDecorrido + 
				(aceleracaoDoMovel * (tempoDecorrido * tempoDecorrido))/ 2;
		
		System.out.println("\nA posição final do objeto é: " + posicaoFinal + " metros");
		
		scanner.close();
	}
}

/*Considere	os	programas	a	seguir,	que	leem	um	código	repetidamente	e	imprimem	o	
código	lido	até	que	o	código	lido	seja	igual	a	-1.	O	código	-1	não	deve	ser	impresso.		
a. Qual	das	duas	soluções	é	a	correta?		
b. Como	a	solução	incorreta	poderia	ser	corrigida?

Programa A: 

import java.util.Scanner;

public class Codigo { 
	public static void main(String[] args) { 
		Scanner teclado = new Scanner(System.in); 
		int codigo; 
		System.out.println("Informe o código: "); 
		codigo = teclado.nextInt(); 
		while (codigo != -1) { 
			System.out.println("Código: " + codigo); 
			System.out.println("Informe o código: "); 
			codigo = teclado.nextInt(); 
		} 
	} 
}

O programa A não define corretamente o loop, o que faz que ele rode infinitamente com o primeiro 
código fornecido pelo usuário.

O programa B tem a lógica de loop já bem estabelecida, só faltando acrescentar uma verificação de código 
diferente de -1 para que ele não imprima este valor caso informado

*/

import java.util.Scanner;

public class Ex07_codigoDiferenteDeMenosUm {

	public static void main(String[] args) { 
		Scanner teclado = new Scanner(System.in); 
		int codigo; 
		do { 
		System.out.print("Informe o código: "); 
		codigo = teclado.nextInt(); 
		if(codigo != -1)
			System.out.println("Código: " + codigo); 
		} while (codigo != -1); 
		
		teclado.close();
		} 

}

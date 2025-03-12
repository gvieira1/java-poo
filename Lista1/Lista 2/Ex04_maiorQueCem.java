import java.util.Scanner;

/*Leia um número qualquer fornecido pelo usuário. Determine se o número é maior do que 100,
imprimindo uma mensagem indicando que o "valor é maior que 100" 
ou uma mensagem indicando que o "valor é menor ou igual a 100".
*/

public class Ex04_maiorQueCem {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Insira um número inteiro: ");
		int valor = teclado.nextInt();
		
		if (valor > 100)
			System.out.println("O valor é maior do que 100!");
		else 
			System.out.println("O valor é menor ou igual a 100!");
		
		teclado.close();
	}
}

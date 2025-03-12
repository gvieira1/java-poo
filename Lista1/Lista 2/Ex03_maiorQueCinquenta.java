import java.util.Scanner;

/*Leia um número qualquer fornecido pelo usuário. Determine se o número é maior do que 50, 
 * imprimindo uma mensagem indicando tal fato*/

public class Ex03_maiorQueCinquenta {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Insira um número inteiro: ");
		int valor = teclado.nextInt();
		
		if (valor >= 50)
			System.out.println("O número é igual ou maior do que 50!");
		else 
			System.out.println("O número é menor do que 50. =(");
		
		teclado.close();
	}

}

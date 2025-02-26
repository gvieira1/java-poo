import java.util.Scanner;

public class Ex11_raizManual {
	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		System.out.println("Insira o valor para o cálculo da raiz quadrada: ");
		int valor = teclado.nextInt();
		
		if (valor < 0) 
			System.out.println("Número inválido! Digite um valor positivo.");
		
		System.out.print("Digite o erro máximo permitido: ");
        double erro = teclado.nextDouble();

		double x = valor / 2.0;
        double raiz;

        //método de Newton-Raphson
        do {
            raiz = x;
            x = (x + (valor / x)) / 2.0;
        } while (Math.abs(x - raiz) > erro); 

        System.out.printf("\nA raiz quadrada aproximada de %d é %.5f%n", valor, x);
		teclado.close();
	}
}

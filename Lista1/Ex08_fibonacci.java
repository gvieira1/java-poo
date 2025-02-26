/*Calcule	a	série	de	Fibonacci	para	um	número	inteiro	não	negativo	informado	pelo	usuário.	
A	série	de	Fibonacci	inicia	com	os	números	F0	=	0	e	F1	=	1,	e	cada	número	posterior	
equivale	à	soma	dos	dois	números	anteriores	(Fn	=	Fn-1	+	Fn-2).	Por	exemplo,	caso	o	usuário	
informe	o	número	9,	o	resultado	seria:	0,	1,	1,	2,	3,	5,	8,	13,	21,	34.	*/

import java.util.Scanner;

public class Ex08_fibonacci {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Digite um número inteiro não negativo: ");
	        int n = scanner.nextInt();
	        if (n < 0) {
	            System.out.println("Número inválido! Digite um valor não negativo.");
	        } else {
	            System.out.println("Série de Fibonacci até " + n + " termos:");
	            fibonacci(n);
	        }
	        scanner.close();
	    }

	    public static void fibonacci(int n) {
	        int primeiro = 0, segundo = 1;

	        for (int i = 0; i < n+1; i++) {
	            System.out.print(primeiro + " ");

	            int proximo = primeiro + segundo;
	            primeiro = segundo;
	            segundo = proximo;
	        }
	        System.out.println(); 
	    }
	}



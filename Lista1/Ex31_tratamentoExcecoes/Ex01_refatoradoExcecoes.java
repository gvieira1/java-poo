package Ex31_tratamentoExcecoes;

import java.util.Scanner;

public class Ex01_refatoradoExcecoes {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Digite o valor de a: ");
            double a = scanner.nextDouble();
            System.out.print("Digite o valor de b: ");
            double b = scanner.nextDouble();
            System.out.print("Digite o valor de c: ");
            double c = scanner.nextDouble();

            if (a == 0) {
                System.out.println("O valor de 'a' não pode ser zero em uma equação do segundo grau.");
            } else {
                double delta = b * b - 4 * a * c;

                if (delta > 0) {
                    double raiz1 = (-b + Math.sqrt(delta)) / (2 * a);
                    double raiz2 = (-b - Math.sqrt(delta)) / (2 * a);
                    System.out.println("As raízes são reais e distintas:");
                    System.out.println("Raiz 1 = " + raiz1);
                    System.out.println("Raiz 2 = " + raiz2);
                } else if (delta == 0) {
                    double raiz = -b / (2 * a);
                    System.out.println("A raiz é real e única:");
                    System.out.println("Raiz = " + raiz);
                } else {
                    System.out.println("Não existem raízes reais.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: Por favor, insira um valor numérico válido.");
        } finally {
            scanner.close();
        }
    }
}

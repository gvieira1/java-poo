/*Determine as raízes de uma equação de 2º grau: ax2 + bx + c = 0 (recordar que o
discriminante Δ = b2 – 4ac, e que a raiz r = (–b ± √Δ)/2a).*/

import java.util.Scanner;

public class Ex01_equacaoSegundoGrau {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o valor de a: ");
        double a = scanner.nextDouble();
        System.out.print("Digite o valor de b: ");
        double b = scanner.nextDouble();
        System.out.print("Digite o valor de c: ");
        double c = scanner.nextDouble();

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
        scanner.close();
    }
}

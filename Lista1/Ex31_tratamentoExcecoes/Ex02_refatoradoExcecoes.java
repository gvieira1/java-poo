package Ex31_tratamentoExcecoes;

import java.util.Scanner;

public class Ex02_refatoradoExcecoes {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Digite as coordenadas do ponto P1 (x1, y1, z1): ");
            double x1 = scanner.nextDouble();
            double y1 = scanner.nextDouble();
            double z1 = scanner.nextDouble();

            System.out.print("Digite as coordenadas do ponto P2 (x2, y2, z2): ");
            double x2 = scanner.nextDouble();
            double y2 = scanner.nextDouble();
            double z2 = scanner.nextDouble();

            double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));

            System.out.println("A distância entre os pontos P1 e P2 é: " + distancia);
        } catch (Exception e) {
            System.out.println("Erro: Por favor, insira um valor numérico válido.");
        } finally {
            scanner.close();
        }
    }
}

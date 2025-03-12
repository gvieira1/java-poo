package Ex31_tratamentoExcecoes;

import java.util.Scanner;

public class Ex03_refatoradoExcecoes {
	public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        double x1, y1, x2, y2, distancia;

        try {
            System.out.println("Entre com as coordenadas x1, y1, x2 e y2 dos pontos nesta ordem:");
            x1 = teclado.nextDouble();
            y1 = teclado.nextDouble();
            x2 = teclado.nextDouble();
            y2 = teclado.nextDouble();

            distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            System.out.println("A distância é: " + distancia);
        } catch (Exception e) {
            System.out.println("Erro: Por favor, insira um valor numérico válido.");
        } finally {
            teclado.close();
        }
    }
}

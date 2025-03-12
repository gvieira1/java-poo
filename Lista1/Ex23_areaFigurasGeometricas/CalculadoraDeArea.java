package Ex23_areaFigurasGeometricas;
import java.util.Scanner;

public class CalculadoraDeArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FiguraGeometrica figura = null;

        System.out.println("Escolha a figura geométrica:");
        System.out.println("1 - Quadrado");
        System.out.println("2 - Retângulo");
        System.out.println("3 - Triângulo");
        System.out.println("4 - Círculo");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                System.out.print("Digite o lado do quadrado: ");
                double lado = scanner.nextDouble();
                figura = new Quadrado(lado);
                break;
            case 2:
                System.out.print("Digite a largura do retângulo: ");
                double largura = scanner.nextDouble();
                System.out.print("Digite a altura do retângulo: ");
                double alturaRet = scanner.nextDouble();
                figura = new Retangulo(largura, alturaRet);
                break;
            case 3:
                System.out.print("Digite a base do triângulo: ");
                double base = scanner.nextDouble();
                System.out.print("Digite a altura do triângulo: ");
                double alturaTri = scanner.nextDouble();
                figura = new Triangulo(base, alturaTri);
                break;
            case 4:
                System.out.print("Digite o raio do círculo: ");
                double raio = scanner.nextDouble();
                figura = new Circulo(raio);
                break;
            default:
                System.out.println("Opção inválida!");
                System.exit(0);
        }
        
        double area = figura.calcularArea();
        System.out.println("A área da figura geométrica é: " + area);
        scanner.close();
    }
}

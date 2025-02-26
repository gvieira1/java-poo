
import java.util.Scanner;

public class Ex16_horasMinutosSegundos {

    public static int lerTempoEmSegundos() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Digite o tempo em segundos: ");
			return scanner.nextInt();
		} 
    }

    public static int calcularHoras(int segundos) {
        return segundos / 3600; 
    }

    public static int calcularMinutos(int segundos) {
        return (segundos % 3600) / 60;  
    }

    public static int calcularSegundos(int segundos) {
        return segundos % 60;  
    }

    public static void escreverResultado(int horas, int minutos, int segundos) {
        System.out.println(horas + " hora(s), " + minutos + " minuto(s), " + segundos + " segundo(s).");
    }

    public static void main(String[] args) {
        int tempoEmSegundos = lerTempoEmSegundos();
        int horas = calcularHoras(tempoEmSegundos);
        int minutos = calcularMinutos(tempoEmSegundos);
        int segundos = calcularSegundos(tempoEmSegundos);
        
        escreverResultado(horas, minutos, segundos);
    }
}

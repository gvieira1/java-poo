import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex34_decimalRomanoComMap {

	    public static Map<Integer, String> criarMapaRomano() {
	        Map<Integer, String> mapaRomano = new HashMap<>();

	        mapaRomano.put(100, "C");
	        mapaRomano.put(200, "CC");
	        mapaRomano.put(300, "CCC");
	        mapaRomano.put(400, "CD");
	        mapaRomano.put(500, "D");
	        mapaRomano.put(600, "DC");
	        mapaRomano.put(700, "DCC");
	        mapaRomano.put(800, "DCCC");
	        mapaRomano.put(900, "CM");

	        mapaRomano.put(10, "X");
	        mapaRomano.put(20, "XX");
	        mapaRomano.put(30, "XXX");
	        mapaRomano.put(40, "XL");
	        mapaRomano.put(50, "L");
	        mapaRomano.put(60, "LX");
	        mapaRomano.put(70, "LXX");
	        mapaRomano.put(80, "LXXX");
	        mapaRomano.put(90, "XC");

	        mapaRomano.put(1, "I");
	        mapaRomano.put(2, "II");
	        mapaRomano.put(3, "III");
	        mapaRomano.put(4, "IV");
	        mapaRomano.put(5, "V");
	        mapaRomano.put(6, "VI");
	        mapaRomano.put(7, "VII");
	        mapaRomano.put(8, "VIII");
	        mapaRomano.put(9, "IX");

	        return mapaRomano;
	    }

	    public static String converterParaRomano(int numero, Map<Integer, String> mapaRomano) {
	        int centena = (numero / 100) * 100;
	        int dezena = ((numero % 100) / 10) * 10;
	        int unidade = numero % 10;

	        String romanoCentena = mapaRomano.getOrDefault(centena, "");
	        String romanoDezena = mapaRomano.getOrDefault(dezena, "");
	        String romanoUnidade = mapaRomano.getOrDefault(unidade, "");

	        return romanoCentena + romanoDezena + romanoUnidade;
	    }

	    public static int lerNumero() {
	        try (Scanner scanner = new Scanner(System.in)) {
	            System.out.print("Digite um número decimal (até 3 dígitos): ");
	            return scanner.nextInt();
	        }
	    }

	    public static void main(String[] args) {
	        Map<Integer, String> mapaRomano = criarMapaRomano();
	        int numero = lerNumero();
	        
	        if (numero < 1 || numero > 999) {
	            System.out.println("Número inválido. Digite um número entre 1 e 999.");
	            return;
	        }

	        String numeralRomano = converterParaRomano(numero, mapaRomano);
	        System.out.println("O equivalente em numeração romana é: " + numeralRomano);
	    }

}

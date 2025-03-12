/*
Escreva	um	número	por	extenso	aceitando	números	de	até	9 dígitos, usando métodos para as traduções
e vetores de Strings que guardam	cada tradução (ex.:	unidades = {“zero”,	 “um”,	“dois”,	...,“nove”	})
*/

import java.util.Scanner;

public class Ex18_numeroPorExtenso {

	private static final String[] UNIDADES = { "zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito",
			"nove" };

	private static final String[] DEZENAS = { "", "dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta",
			"setenta", "oitenta", "noventa" };

	private static final String[] DEZ_A_DEZENOVE = { "dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis",
			"dezessete", "dezoito", "dezenove" };

	private static final String[] CENTENAS = { "", "cem", "duzentos", "trezentos", "quatrocentos", "quinhentos",
			"seiscentos", "setecentos", "oitocentos", "novecentos" };

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite um número (0 a 999.999.999): ");
		int numero = scanner.nextInt();

		if (numero < 0 || numero > 999_999_999) {
			System.out.println("Número fora do limite permitido!");
		} else {
			System.out.println("Por extenso: " + converter(numero));
		}

		scanner.close();
	}

	public static String converter(int numero) {
		if (numero == 0)
			return "zero";

		String resultado = "";

		if (numero >= 1_000_000) {
			resultado += converterMenoresQueMil(numero / 1_000_000) + " milhão";
			if (numero / 1_000_000 > 1)
				resultado += "es";
			numero %= 1_000_000;
			if (numero > 0)
				resultado += " e ";
		}

		if (numero >= 1_000) {
			resultado += converterMenoresQueMil(numero / 1_000) + " mil";
			numero %= 1_000;
			if (numero > 0)
				resultado += " e ";
		}

		if (numero > 0) {
			resultado += converterMenoresQueMil(numero);
		}

		return resultado;
	}

	private static String converterMenoresQueMil(int num) {
		String texto = "";

		if (num == 100)
			return "cem";

		if (num >= 100) {
			texto += CENTENAS[num / 100];
			num %= 100;
			if (num > 0)
				texto += " e ";
		}

		if (num >= 10 && num <= 19) {
			texto += DEZ_A_DEZENOVE[num - 10];
			return texto;
		}

		if (num >= 20) {
			texto += DEZENAS[num / 10];
			num %= 10;
			if (num > 0)
				texto += " e ";
		}

		if (num > 0) {
			texto += UNIDADES[num];
		}

		return texto;
	}
}

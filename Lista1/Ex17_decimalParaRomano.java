
import java.util.Scanner;

/*Leia	um	número	decimal	(até	3	dígitos)	e	escreva	o	seu	equivalente	em	numeração	
romana.	Utilize	métodos	para	obter	cada	dígito	do	número	decimal	e	para	a	
transformação	de	numeração	decimal	para	romana	(Dica1:	1	=	I,	5	=	V,	10	=	X,	50	=	L,	100	=	
C,	500	=	D,	1.000	=	M;	Dica2:	utilize	um	vetor	guardando	a	tradução	para	cada	um	dos	
dígitos).*/

public class Ex17_decimalParaRomano {

    public static int lerNumero() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Digite um número decimal (até 3 dígitos): ");
			return scanner.nextInt();
		}
    }

    public static int obterCentena(int numero) {
        return numero / 100;  
    }

    public static int obterDezena(int numero) {
        return (numero % 100) / 10;  
    }

    public static int obterUnidade(int numero) {
        return numero % 10;  
    }

    public static String converterParaRomano(int numero) {
        String[] romanCentenas = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] romanDezenas = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] romanUnidades = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        int centena = obterCentena(numero);
        int dezena  = obterDezena(numero);
        int unidade = obterUnidade(numero);

        return romanCentenas[centena] + romanDezenas[dezena] + romanUnidades[unidade];
    }

    public static void main(String[] args) {
        int numero = lerNumero();
        String numeralRomano = converterParaRomano(numero);
        System.out.println("O equivalente em numeração romana é: " + numeralRomano);
    }
}


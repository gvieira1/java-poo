/* Faça	um	programa	que	leia	um	número	“n”	informado	pelo	usuário	e	diga	quantos	
números	primos	há	entre	0	e	“n”.	Esse	seu	programa	deve	rodar	em	2	threads,
de	forma que o	esforço	computacional seja uniformemente	dividido	entre	as	threads.*/

import java.util.Scanner;

public class Ex36_nPrimosComThreads {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um número n: ");
        int n = scanner.nextInt();
        scanner.close();

        int meio = n / 2;

        ContadorPrimos contador1 = new ContadorPrimos(0, meio);
        ContadorPrimos contador2 = new ContadorPrimos(meio + 1, n);

        contador1.start();
        contador2.start();

        try {
            contador1.join();
            contador2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int totalPrimos = contador1.getQuantidadePrimos() + contador2.getQuantidadePrimos();
        System.out.println("Quantidade de números primos entre 0 e " + n + ": " + totalPrimos);
    }
}


class ContadorPrimos extends Thread {
    private int inicio;
    private int fim;
    private int quantidadePrimos;

    public ContadorPrimos(int inicio, int fim) {
        this.inicio = inicio;
        this.fim = fim;
        this.quantidadePrimos = 0;
    }

    public int getQuantidadePrimos() {
        return quantidadePrimos;
    }

    @Override
    public void run() {
        for (int i = inicio; i <= fim; i++) {
            if (ehPrimo(i)) {
                quantidadePrimos++;
            }
        }
    }

    private boolean ehPrimo(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		ContaTerminal conta = new ContaTerminal();
		
		System.out.println("Por favor, digite o número da Agência: ");
		conta.setNumero(in.nextInt());
		
		System.out.println("Por favor, digite sua Agência: ");
		conta.setAgencia(in.next());
		in.nextLine();
		
		System.out.println("Por favor, digite seu nome: ");
		conta.setNomeCliente(in.nextLine());
		
		System.out.println("Por favor, digite o valor para saque: ");
		conta.setSaldo(in.nextDouble());
		
		in.close();
		System.out.println("\"Olá, " + conta.getNomeCliente() + "! Obrigado por criar uma conta em nosso banco,"
				+ " sua agência é " + conta.getAgencia()
				+ ", conta " + conta.getNumero() + ", e seu saldo de "+ conta.getSaldo() +" já está disponível para saque\".");

	}

}

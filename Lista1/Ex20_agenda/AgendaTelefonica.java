package Ex20_agenda;

import java.util.Scanner;

public class AgendaTelefonica {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Agenda agenda = new Agenda();

		while (true) {
			System.out.println("\n=== Agenda Telefônica ===");
			System.out.println("1. Adicionar Contato");
			System.out.println("2. Listar Contatos");
			System.out.println("3. Buscar Contato");
			System.out.println("4. Remover Contato");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine(); 

			switch (opcao) {
			case 1 -> {
				System.out.print("Nome: ");
				String nome = scanner.nextLine();
				System.out.print("Telefone: ");
				String telefone = scanner.nextLine();
				agenda.adicionarContato(nome, telefone);
			}
			case 2 -> agenda.listarContatos();
			case 3 -> {
				System.out.print("\nNome para buscar: ");
				String nome = scanner.nextLine();
				agenda.buscarContato(nome);
			}
			case 4 -> {
				System.out.print("\nNome para remover: ");
				String nome = scanner.nextLine();
				agenda.removerContato(nome);
			}
			case 0 -> {
				System.out.println("\nEncerrando a agenda. Até mais!");
				scanner.close();
				System.exit(0);
			}
			default -> System.out.println("\nOpção inválida. Tente novamente.");
			}
		}
	}
}

package Ex21_biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
	private static List<Livro> livros = new ArrayList<>();
    private static List<Emprestimo> emprestimos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien"));
        livros.add(new Livro("Dom Casmurro", "Machado de Assis"));
        livros.add(new Livro("1984", "George Orwell"));

        while (true) {
            System.out.println("\n=== Biblioteca ===");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Realizar Empréstimo");
            System.out.println("4. Listar Empréstimos");
            System.out.println("5. Devolver Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> cadastrarLivro(scanner);
                case 2 -> listarLivros();
                case 3 -> realizarEmprestimo(scanner);
                case 4 -> listarEmprestimos();
                case 5 -> devolverLivro(scanner);
                case 0 -> {
                    System.out.println("Encerrando o sistema. Até mais!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarLivro(Scanner scanner) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        livros.add(new Livro(titulo, autor));
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        System.out.println("\nLista de Livros:");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    private static void realizarEmprestimo(Scanner scanner) {
        System.out.print("Nome da pessoa: ");
        String nome = scanner.nextLine();
        System.out.print("Contato da pessoa: ");
        String contato = scanner.nextLine();
        Pessoa pessoa = new Pessoa(nome, contato);

        System.out.print("Título do livro para emprestar: ");
        String titulo = scanner.nextLine();
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo) && livro.isDisponivel()) {
                emprestimos.add(new Emprestimo(pessoa, livro));
                System.out.println("Empréstimo realizado com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado ou indisponível.");
    }

    private static void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }
        System.out.println("\nLista de Empréstimos:");
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo);
        }
    }

    private static void devolverLivro(Scanner scanner) {
        System.out.print("Título do livro para devolver: ");
        String titulo = scanner.nextLine();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getTitulo().equalsIgnoreCase(titulo)) {
                emprestimo.getLivro().setDisponivel(true);
                emprestimos.remove(emprestimo);
                System.out.println("Livro devolvido com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado nos empréstimos.");
    }
}

package Ex20_agenda;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
	private List<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<>();
    }

    public void adicionarContato(String nome, String telefone) {
        Contato contato = new Contato(nome, telefone);
        contatos.add(contato);
        System.out.println("Contato adicionado com sucesso!");
    }

    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("Agenda vazia.");
            return;
        }
        System.out.println("\nLista de Contatos:");
        for (Contato contato : contatos) {
            System.out.println(contato);
        }
    }

    public void buscarContato(String nome) {
        boolean encontrado = false;
        for (Contato contato : contatos) {
            if (contato.getNome().contains(nome)) {
                System.out.println("Contato encontrado: " + contato);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Contato não encontrado.");
        }
    }

    public void removerContato(String nome) {
        boolean removido = false;
        for (Contato contato : contatos) {
            if (contato.getNome().equalsIgnoreCase(nome)) {
                contatos.remove(contato);
                System.out.println("Contato removido com sucesso!");
                removido = true;
                break;
            }
        }
        if (!removido) {
            System.out.println("Contato não encontrado para remoção.");
        }
    }
}

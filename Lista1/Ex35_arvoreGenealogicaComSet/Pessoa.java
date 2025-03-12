package Ex35_arvoreGenealogicaComSet;

import java.util.HashSet;
import java.util.Set;

public class Pessoa {

    private String nome;
    private int idade;
    private Set<Pessoa> filhos;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.filhos = new HashSet<>();
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Set<Pessoa> getFilhos() {
        return filhos;
    }

    public void adicionarFilho(Pessoa filho) {
        filhos.add(filho);
    }

    public void imprimirArvoreGenealogica() {
        System.out.println("\nNome: " + nome + ", Idade: " + idade);
        if (!filhos.isEmpty()) {
            System.out.println("Filhos:");
            for (Pessoa filho : filhos) {
                System.out.println(" - " + filho.getNome() + ", Idade: " + filho.getIdade());
            }
        } else {
            System.out.println("Sem filhos registrados.");
        }
    }

    public static void main(String[] args) {
        Pessoa pai = new Pessoa("Carlos", 45);
        Pessoa mae = new Pessoa("Ana", 43);
        Pessoa filho1 = new Pessoa("Pedro", 20);
        Pessoa filha2 = new Pessoa("Julia", 18);

        pai.adicionarFilho(filho1);
        pai.adicionarFilho(filha2);
        mae.adicionarFilho(filho1);
        mae.adicionarFilho(filha2);

        pai.imprimirArvoreGenealogica();
        mae.imprimirArvoreGenealogica();
    }
}

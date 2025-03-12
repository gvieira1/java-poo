package Ex22_arvoreGenealogica;

public class Pessoa {

    private String nome;
    private int idade;
    private Pessoa pai;
    private Pessoa mae;

    public Pessoa(String nome, int idade, Pessoa pai, Pessoa mae) {
        this.nome = nome;
        this.idade = idade;
        this.pai = pai;
        this.mae = mae;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Pessoa getPai() {
        return pai;
    }

    public Pessoa getMae() {
        return mae;
    }

    public void imprimirArvoreGenealogica() {
        System.out.println("Nome: " + nome + ", Idade: " + idade);
        if (pai != null) {
            System.out.println("Pai: " + pai.getNome());
        } else {
            System.out.println("Pai: Desconhecido");
        }
        if (mae != null) {
            System.out.println("Mãe: " + mae.getNome());
        } else {
            System.out.println("Mãe: Desconhecida");
        }
    }

    public static void main(String[] args) {
        Pessoa pai = new Pessoa("Carlos", 45, null, null);
        Pessoa mae = new Pessoa("Ana", 43, null, null);
        Pessoa filho = new Pessoa("Pedro", 20, pai, mae);

        filho.imprimirArvoreGenealogica();
    }
}

package Ex21_biblioteca;

import java.util.Date;

public class Emprestimo {
	private Pessoa pessoa;
    private Livro livro;
    private Date dataEmprestimo;

    public Emprestimo(Pessoa pessoa, Livro livro) {
        this.pessoa = pessoa;
        this.livro = livro;
        this.dataEmprestimo = new Date();
        livro.setDisponivel(false);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Livro getLivro() {
        return livro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    @Override
    public String toString() {
        return pessoa + " | Livro: " + livro.getTitulo() + " | Data: " + dataEmprestimo;
    }
}

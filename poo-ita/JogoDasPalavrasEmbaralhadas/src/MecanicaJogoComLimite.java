
public class MecanicaJogoComLimite implements MecanicaDoJogo {
    private String palavraCorreta;
    private int tentativas;
    private int maxTentativas = 4;
    private int pontuacao = 0;

    public MecanicaJogoComLimite(String palavraCorreta) {
        this.palavraCorreta = palavraCorreta;
        this.tentativas = 0;
    }

    @Override
    public void iniciarJogo() {
        this.tentativas = 0;
    }

    @Override
    public boolean verificarResposta(String tentativa) {
        tentativas++;
        if (tentativa.equals(palavraCorreta)) {
            pontuacao += 10;
            return true;
        }
        return false;
    }

    @Override
    public boolean jogoAcabou() {
        return tentativas >= maxTentativas;
    }

    @Override
    public int pontuacao() {
        return pontuacao;
    }

	@Override
	public int tentativas() {
		return this.tentativas;
	}
}

public class MecanicaDoJogoComTempo implements MecanicaDoJogo {
    private String palavraCorreta;
    private int tentativas;
    private int maxTentativas = 3;  
    private int pontuacao = 0;     
    private long tempoLimite = 30000;  
    private long tempoInicial;
    private boolean tempoAcabou = false;

    public MecanicaDoJogoComTempo(String palavraCorreta) {
        this.palavraCorreta = palavraCorreta;
        this.tentativas = 0;
    }

    @Override
    public void iniciarJogo() {
        this.tentativas = 0;
        this.tempoInicial = System.currentTimeMillis();  
        this.tempoAcabou = false;  
    }

    @Override
    public boolean verificarResposta(String tentativa) {
       
        long tempoPassado = System.currentTimeMillis() - tempoInicial;
        if (tempoPassado >= tempoLimite) {
            tempoAcabou = true;  
            System.out.println("\nTempo esgotado! Você não adivinhou a palavra a tempo.");
            return false; 
        }

        if (tempoAcabou) {
            System.out.println("O tempo acabou! Não é mais possível tentar.");
            return false; 
        }

        tentativas++;

        if (tentativa.equals(palavraCorreta)) {
            pontuacao += 10;  
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean jogoAcabou() {
        return tempoAcabou || tentativas >= maxTentativas;
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

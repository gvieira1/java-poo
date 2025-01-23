
public interface MecanicaDoJogo {
    void iniciarJogo();
    boolean verificarResposta(String tentativa);
    boolean jogoAcabou();
    int pontuacao();
    int tentativas();
}

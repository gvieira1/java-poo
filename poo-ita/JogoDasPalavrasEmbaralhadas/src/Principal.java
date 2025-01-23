import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FabricaEmbaralhadores fabricaEmbaralhadores = new FabricaEmbaralhadores();
        FabricaMecanicaDoJogo fabricaMecanica = new FabricaMecanicaDoJogo();

        int pontuacaoTotal = 0;
        boolean jogarNovamente = true;
        
        System.out.println("Escolha o modo de jogo:" + "\n\t1. Modo com Limite de Tentativas \n\t2. Modo com Limite de Tempo \nDigite a opção desejada (1 ou 2): ");
        int opcaoModo = in.nextInt();
        in.nextLine(); 
        
        String modoDeJogo = "";
        if (opcaoModo == 1) {
            modoDeJogo = "limite";
        } else if (opcaoModo == 2) {
            modoDeJogo = "tempo";
        } else {
            System.out.println("Opção inválida. Usando o modo com limite de tentativas por padrão.");
            modoDeJogo = "limite";
        }

        while (jogarNovamente) {         
            String palavra = BancoDePalavras.getPalavraAleatoria();
      
            Embaralhador embaralhador = fabricaEmbaralhadores.criarEmbaralhador();
            String palavraEmbaralhada = embaralhador.embaralhar(palavra);
           
            MecanicaDoJogo jogo = fabricaMecanica.criarMecanica(palavra, modoDeJogo);

            System.out.println("Palavra embaralhada: " + palavraEmbaralhada);
            jogo.iniciarJogo();

            while (!jogo.jogoAcabou()) {
                System.out.print("Digite sua tentativa: ");
                String tentativa = in.nextLine();

                if (jogo.verificarResposta(tentativa)) {
                    System.out.println("Você acertou!");
                    pontuacaoTotal += jogo.pontuacao(); 
                    break; 
                } else {
                    System.out.println("-> Resposta errada!");
                }
                
                if(jogo.jogoAcabou()) {
                	System.out.println("\n\tObrigado por jogar!");
                	System.out.println("\n\tSua pontuação total: " + pontuacaoTotal);
                    jogarNovamente = false;
                }
            }

            
            System.out.println("\tSua pontuação nesta rodada é: " + jogo.pontuacao());
            System.out.print("\nDeseja jogar novamente? (s/n): ");
            String resposta = in.nextLine();
            
            if (resposta.equalsIgnoreCase("n") || jogo.jogoAcabou()) {
                jogarNovamente = false;
            }else {
            	jogarNovamente = true;
            }
        }

        System.out.println("\nVocê esgotou suas chances! Obrigado por jogar! \nPontuação final: " + pontuacaoTotal);
        in.close();
    }
}

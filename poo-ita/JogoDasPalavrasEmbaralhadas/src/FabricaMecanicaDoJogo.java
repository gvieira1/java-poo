
public class FabricaMecanicaDoJogo {
	
    public MecanicaDoJogo criarMecanica(String palavra, String modoDeJogo) {
        switch (modoDeJogo.toLowerCase()) {
            case "tempo":
                return new MecanicaDoJogoComTempo(palavra);
		case "limite":	
                return new MecanicaJogoComLimite(palavra);
		default:
                return new MecanicaJogoComLimite(palavra);
        }
    }
    
}

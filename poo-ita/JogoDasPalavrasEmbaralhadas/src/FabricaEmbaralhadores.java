import java.util.Random;

public class FabricaEmbaralhadores {
    public Embaralhador criarEmbaralhador() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            return new EmbaralhadorInverterParImpar();
        } else {
            return new EmbaralhadorAleatorio();
        }
    }
}

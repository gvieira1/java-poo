import java.util.Random;

public class BancoDePalavras {
    private static String[] palavras = {
        "java", "programacao", "computador", "notebook", "celular", 
        "internet", "desenvolvimento", "algoritmo", "tecnologia", "software",
        "hardware", "rede", "sistema", "cloud", "data", "api", "machinelearning", "python", "interface"
    };

    public static String getPalavraAleatoria() {
        Random rand = new Random();
        int index = rand.nextInt(palavras.length);
        return palavras[index];
    }
}

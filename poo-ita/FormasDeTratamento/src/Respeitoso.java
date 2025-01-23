
public class Respeitoso implements FormatadorNome {
    private String genero; 
    
    public Respeitoso(String genero) {
        this.genero = genero;
    }
    
    @Override
    public String formatarNome(String nome, String sobrenome) {
        if ("masculino".equalsIgnoreCase(genero)) {
            return "Sr. " + sobrenome;
        } else {
            return "Sra. " + sobrenome;
        }
    }
}

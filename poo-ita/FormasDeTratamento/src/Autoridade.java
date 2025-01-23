
public class Autoridade implements FormatadorNome{
	
	private String nome;
	private String sobrenome;
	private FormatadorNome formatador;
	
	 public Autoridade(String nome, String sobrenome, FormatadorNome formatador) {
	        this.nome = nome;
	        this.sobrenome = sobrenome;
	        this.formatador = formatador; 
	}
	
	public String getTratamento(){
		return formatarNome( nome, sobrenome);
	}
	@Override
	public String formatarNome(String nome, String sobrenome) {
		return formatador.formatarNome(nome, sobrenome); 
	}
	
}

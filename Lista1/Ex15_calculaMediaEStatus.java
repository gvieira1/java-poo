
public class Ex15_calculaMediaEStatus {

    public static double calcularMedia(double nota1, double nota2, double nota3) {
        return (nota1 + nota2 + nota3) / 3;
    }

	public static String obterStatus(double media) {
		if (media >= 6)
			return "Aprovado";
		else if (media >= 4 && media < 6)
			return "Verificação Suplementar";
		else
			return "Reprovado";

	}

    public static void main(String[] args) {
        double nota1 = 7.5;
        double nota2 = 5.0;
        double nota3 = 8.0;

        double media = calcularMedia(nota1, nota2, nota3);
        String status = obterStatus(media);
        String mediaFormatada = String.format("%.2f", media);
        
        System.out.println("Média do aluno: " + mediaFormatada);
        System.out.println("Status do aluno: " + status);
    }
}


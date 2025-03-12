import java.util.Scanner;

/*Escreva um programa que leia um número real entre 0.00 e 100.00 e o exiba por extenso como 
se fosse uma quantia em dinheiro, por exemplo: 1.00 -> "um real", .12.73 -> "doze reais "
e setenta e três centavos*/

public class Ex19_extensoReal {
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Insira um valor real entre 0  e 100: ");
		int valor = teclado.nextInt();
		
		String result = extenso(valor);
		System.out.println(result);
		teclado.close();
	}


public static String extenso(int num){
     int u,d;
     
     String extenso = "", conexao;
     String[] unidade,dezena,dezenaespecial;
     
     unidade = new String[10];
     dezena = new String[10];
     dezenaespecial = new String[10];
     
     unidade[0] = "";
     unidade[1] = "um";
     unidade[2] = "dois";
     unidade[3] = "três";
     unidade[4] = "quatro";
     unidade[5] = "cinco";
     unidade[6] = "seis";
     unidade[7] = "sete";
     unidade[8] = "oito";
     unidade[9] = "nove";
     
     dezena[0] = "";
     dezena[1] = "dez";
     dezena[2] = "vinte";
     dezena[3] = "trinta";
     dezena[4] = "quarenta";
     dezena[5] = "cinquenta";
     dezena[6] = "sessenta";
     dezena[7] = "setenta";
     dezena[8] = "oitenta";
     dezena[9] = "noventa";
     
     dezenaespecial[0] = "dez";
     dezenaespecial[1] = "onze";
     dezenaespecial[2] = "doze";
     dezenaespecial[3] = "treze";
     dezenaespecial[4] = "quatorze";
     dezenaespecial[5] = "quinze";
     dezenaespecial[6] = "dezesseis";
     dezenaespecial[7] = "dezessete";
     dezenaespecial[8] = "dezoito";
     dezenaespecial[9] = "dezenove";
     
     if (num >= 1 && num <= 99){
          d = num / 10;
          u = num % 10;
     
          conexao = "";
          if (d > 0 && u > 0){
               conexao = " e ";
          }
          if (num >= 10 && num <= 19){
               extenso = dezenaespecial[ u ];
          }else{
               extenso = dezena[ d ] + conexao + unidade[ u ];
          }
     }
     return (extenso);
}


}
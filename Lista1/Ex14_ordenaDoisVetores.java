/*Crie	dois	vetores	de	50	posições	com	valores	inteiros	aleatórios,	ordene	cada	vetor	
individualmente,	e	combine	os	dois	vetores	gerando	um	novo	vetor	de	100	posições,	de	
forma	que	esse	novo	vetor	já	seja	criado	ordenado.	*/

import java.util.Arrays;
import java.util.Random;

public class Ex14_ordenaDoisVetores {
	public static void main(String[] args) {

		Random random = new Random();
		
		int[] vetor1 = new int[50];
		int[] vetor2 = new int[50];
		
		for (int i = 0; i < vetor1.length; i++) {
			vetor1[i] = random.nextInt(1000);
		}
		for (int i = 0; i < vetor2.length; i++) {
			vetor2[i] = random.nextInt(1000);
		}

		Arrays.sort(vetor1);
		Arrays.sort(vetor2);
		
        int[] vetorCombinado = new int[100];
        
        System.arraycopy(vetor1, 0, vetorCombinado, 0, vetor1.length);
        System.arraycopy(vetor2, 0, vetorCombinado, vetor1.length, vetor2.length);
        
        Arrays.sort(vetorCombinado);
        System.out.println("Vetor 1 ordenado:");
        System.out.println(Arrays.toString(vetor1));
        
        System.out.println("Vetor 2 ordenado:");
        System.out.println(Arrays.toString(vetor2));
        
        System.out.println("Vetor combinado e ordenado:");
        System.out.println(Arrays.toString(vetorCombinado));
	}
}


import java.util.Arrays;
import java.util.Random;

public class Ex13_ordenaAleatorios {
    public static void main(String[] args) {
    	
        Random random = new Random();       
        int[] vetor = new int[100];
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = random.nextInt(1000); 
        }
        
        System.out.println("Vetor antes de ordenar:");
        for (int num : vetor) {
            System.out.print(num + " ");
        }
        System.out.println(); 
        
        Arrays.sort(vetor);
        System.out.println("Vetor depois de ordenar:");
        for (int num : vetor) {
            System.out.print(num + " ");
        }
    }
}

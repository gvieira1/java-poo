package Ex33_listVetor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Ex13_comList {
	 public static void main(String[] args) {
	    	
	        Random random = new Random();      
	        
	        List<Integer> lista = new ArrayList<>(100);
	        
	        for (int i = 0; i < 101; i++) {
	            lista.add(random.nextInt(1000)) ; 
	        }
	        
	        System.out.println("ArrayList antes de ordenar:");
	        for (int num : lista) {
	            System.out.print(num + " ");
	        }
	        System.out.println(); 
	        
	        Collections.sort(lista);
	        System.out.println("ArrayList depois de ordenar:");
	        for (int num : lista) {
	            System.out.print(num + " ");
	        }
	    }
}

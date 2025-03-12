package Ex33_listVetor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Ex14_comList {
	public static void main(String[] args) {

		Random random = new Random();	
		List<Integer> lista1 = new ArrayList<>(50);
		List<Integer> lista2 = new ArrayList<>(50);
		
		for (int i = 0; i < 51; i++) {
			lista2.add(random.nextInt(1000));
			lista1.add(random.nextInt(1000));
		}
		
		Collections.sort(lista2);
		Collections.sort(lista1);
		
		List<Integer> listaCompleta = new ArrayList<>(100);
        
		int i = 0, j = 0;
        while (i < lista1.size() && j < lista2.size()) {
            if (lista1.get(i) <= lista2.get(j)) {
                listaCompleta.add(lista1.get(i));
                i++;
            } else {
                listaCompleta.add(lista2.get(j));
                j++;
            }
        }
        
        System.out.println("Vetor 1 ordenado:" + lista1 );
        
        System.out.println("\nVetor 2 ordenado:" + lista2 );
        
        System.out.println("\nVetor combinado e ordenado:" + listaCompleta);
	}
}

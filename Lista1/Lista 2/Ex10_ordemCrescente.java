import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Leia três números quaisquer, imprimindo-os em ordem crescente.

public class Ex10_ordemCrescente {
	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		List<Double> lista = new ArrayList<Double>();
		System.out.println("Insira três valores reais: ");
		lista.add(teclado.nextDouble());
		lista.add(teclado.nextDouble());
		lista.add(teclado.nextDouble());		
		
		Collections.sort(lista);  
	    for (Double i : lista) {
	      System.out.print(i + ", ");
	    }
		teclado.close();
	}
}

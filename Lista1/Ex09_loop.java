// Determine	a	saída	do	seguinte	programa:
// O primeiro laço (i) começa em 2 e vai até 8, aumentando de 2 em 2 (2, 4, 6, 8).
// O segundo laço (j) começa com o valor de i e vai até 4, aumentando de 1 em 1.
// O terceiro laço (k) começa em 1 e aumenta de i em i (k = k + i), enquanto for menor ou igual a j.

public class Ex09_loop {
	public static void main(String[] args) {
		for (int i = 2; i <= 8; i = i + 2) {
			for (int j = i; j <= 4; j++) {
				for (int k = 1; k <= j; k = k + i) {
					System.out.println(i + ", " + j + ", " + k);
				}
			}
		}	
	}

}

/*/Construa	a	tabela	de	multiplicação	de	números	de	1	a	10
(ex.:	1	x	1	=	1,	1	x	2	=	2,	etc.).*/

public class Ex05_tabuada {
	public static void main(String[] args) {
		for(int i = 0; i<11; i++) {
			System.out.println("\n\tTabuada do " + i);
			for(int j= 1; j < 11; j++) {
				System.out.println(i + " X " + j + " = " + i * j );
			}
		}
	}
}

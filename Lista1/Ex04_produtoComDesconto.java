/*Para	cada produto	informado	(nome,	preço	e	quantidade),	escreva	o	nome	do	produto	
comprado	e	o	valor	total	a	ser	pago,	considerando	que	são	oferecidos	descontos	pelo	
número	de	unidades	compradas,	segundo	a	tabela	abaixo:		
a. Até	10	unidades:	valor	total	
b. de	11	a	20	unidades:	10%	de	desconto	
c. de	21	a	50	unidades:	20%	de	desconto	
d. acima	de	50	unidades:	25%	de	desconto*/

import java.util.Scanner;

public class Ex04_produtoComDesconto {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Double total = 0.0;

        System.out.print("Digite o nome do produto: ");
        String produto = scanner.next();
        
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();

        System.out.print("Digite a quantidade de produtos: ");
        double qtde = scanner.nextInt();
        
        if (qtde > 50) {
        	total = (preco * qtde) * 0.75;
        }else if (qtde > 20) {
        	total = (preco * qtde) * 0.80;
        }else if (qtde > 10) {
        	total = (preco * qtde) * 0.90;
        }else {
        	total = preco * qtde;
        }
        
        System.out.println("\nProduto comprado: " + produto + "\nPreço: " + total);

        scanner.close();
    }
}

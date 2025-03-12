package Ex19_supermercado;

import java.util.Scanner;

public class Supermercado {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        Produto arroz = new Produto("Arroz", 5.50, 10);
        Produto feijao = new Produto("Feijão", 7.80, 5);
        Produto leite = new Produto("Leite", 4.20, 8);

        Pedido pedido = new Pedido();

        pedido.adicionarItem(arroz, 2);
        pedido.adicionarItem(feijao, 1);
        pedido.adicionarItem(leite, 3);

        pedido.listarItens();

        Pagamento pagamento = new Pagamento("Cartão");
        pagamento.processarPagamento(pedido.calcularTotal());

        scanner.close();
	}

}

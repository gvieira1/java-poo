package Ex19_supermercado;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private List<ItemPedido> itens = new ArrayList<>();

    public void adicionarItem(Produto produto, int quantidade) {
        if (produto.reduzirEstoque(quantidade)) {
            itens.add(new ItemPedido(produto, quantidade));
            System.out.println("Item adicionado: " + produto.getNome() + " - Quantidade: " + quantidade);
        } else {
            System.out.println("Estoque insuficiente para " + produto.getNome());
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public void listarItens() {
        System.out.println("\nItens do Pedido:");
        for (ItemPedido item : itens) {
            System.out.println(item.getQuantidade() + "x " + item.getProduto().getNome() + " - " + formatarPreco(item.calcularSubtotal()));
        }
        System.out.println("Total: " + formatarPreco(calcularTotal()));
    }

    private String formatarPreco(double valor) {
        DecimalFormat df = new DecimalFormat("R$ #,##0.00");
        return df.format(valor);
    }
}

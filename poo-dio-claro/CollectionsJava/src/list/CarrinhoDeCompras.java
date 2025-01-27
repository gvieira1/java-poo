package list;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
	
	private List<Item> listaItems;
	
	public CarrinhoDeCompras() {
		this.listaItems = new ArrayList<>();
	}

	public void adicionarItem(String nome, double preco, int quantidade){
		listaItems.add(new Item(nome, preco, quantidade));
	}
	
	public void removerItem(String nome){
		List<Item> itemToRemove = new ArrayList<>();
		if(!listaItems.isEmpty()) {
			for(Item t: listaItems) {
				if (t.getNome().equalsIgnoreCase(nome)) {
					itemToRemove.add(t);
				}
			}
			listaItems.removeAll(itemToRemove);
		} else {
			System.out.println("A lista está vazia!");
		}
	}
	
	public Double calcularValorTotal(){
		Double valorTotal = 0.0;
		for(Item p: listaItems) {
			double valor =+ p.getPreco() * p.getQuantidade();
			valorTotal += valor;
		}
		return valorTotal;
	}
	
	public int obterNumeroTotalItems() {
	    return listaItems.size();
	  }
	
	public void exibirItens(){
		if(!listaItems.isEmpty()) {
			System.out.println(listaItems);
		} else {
			System.out.println("A lista está vazia!");
		}
	}

}

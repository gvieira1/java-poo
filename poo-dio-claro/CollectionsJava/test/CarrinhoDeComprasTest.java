import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import basics_list.CarrinhoDeCompras;

class CarrinhoDeComprasTest {

	@Test
	void testAdicionaItem() {
		CarrinhoDeCompras listaItems = new CarrinhoDeCompras();
		listaItems.adicionarItem("Biscoito", 3.45, 2);
		listaItems.adicionarItem("Leite", 4.00, 1);
		Double valor = listaItems.calcularValorTotal();
		
		assertEquals(10.90, valor);
	}
	
	@Test
	void testRemoveItem() {
		CarrinhoDeCompras listaItems = new CarrinhoDeCompras();
		listaItems.adicionarItem("Biscoito", 3.45, 2);
		listaItems.adicionarItem("Pão", 8.00, 3);
		listaItems.removerItem("Biscoito");
		int tamanho = listaItems.obterNumeroTotalItems();
		
		assertEquals(1, tamanho);
	}
	

}

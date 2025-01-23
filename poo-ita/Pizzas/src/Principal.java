
public class Principal {

	public static void main(String[] args) {
		Pizza pizza1 = new Pizza();
		pizza1.adicionaIngrediente("Queijo");
		pizza1.adicionaIngrediente("Presunto");
		
		Pizza pizza2 = new Pizza();
		pizza2.adicionaIngrediente("Queijo");
		pizza2.adicionaIngrediente("Tomate");
		pizza2.adicionaIngrediente("Bacon");
		
		Pizza pizza3 = new Pizza();
		pizza3.adicionaIngrediente("Queijo");
		pizza3.adicionaIngrediente("Calabresa");
		pizza3.adicionaIngrediente("Azeitona");
		pizza3.adicionaIngrediente("Bacon");
		pizza3.adicionaIngrediente("Milho");
		pizza3.adicionaIngrediente("Catupiry");
		
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
		carrinho.adicionarPizza(pizza1);
		carrinho.adicionarPizza(pizza2);
		carrinho.adicionarPizza(pizza3);
		
		System.out.println("Total do carrinho: R$ " + carrinho.getPrecoTotal());
		
		System.out.println("Quantidade de ingredientes utilizados:");
		for (String ingrediente : Pizza.getIngredientesContabilizados().keySet()) {
			System.out.println(ingrediente +  ": " + 
			Pizza.getIngredientesContabilizados().get(ingrediente));
		}
	}
}

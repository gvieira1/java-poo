import java.util.ArrayList;

public class CarrinhoDeCompras {
	private ArrayList<Pizza> pizzas = new ArrayList<>();
	
	public void adicionarPizza(Pizza pizza) {
		if (pizza.getIngredientes().isEmpty()) {
			System.out.println("A pizza não pode ser adicionada sem ingredientes.");
		} else {
			pizzas.add(pizza);
		}
	}
	
	public double getPrecoTotal() {
		double total = 0.0;
		for (Pizza pizza : pizzas) {
			total += pizza.getPreco();
		}
		return total;
	}
}

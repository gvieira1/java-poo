import java.util.HashMap;

public class Pizza {
	private static HashMap<String, Integer> ingredientesContabilizados = new HashMap<>();
	private HashMap<String, Integer> ingredientes = new HashMap<>();
	
	public void adicionaIngrediente(String ingrediente) {
		if(!ingredientes.containsKey(ingrediente)) {
		ingredientes.put(ingrediente, 1);
		contabilizaIngrediente(ingrediente);
		}
	}

	public static void contabilizaIngrediente(String ingrediente) {
		ingredientesContabilizados.put(ingrediente, 
		ingredientesContabilizados.getOrDefault(ingrediente, 0) + 1);
	}
	
	public double getPreco() {
		int quantidadeIngredientes = ingredientes.size();
		
		if (quantidadeIngredientes <= 2) {
			return 15.0;
		} else if (quantidadeIngredientes >= 3 && quantidadeIngredientes <= 5) {
			return 20.0;
		} else {
			return 23.0;
		}
	}

	public HashMap <String, Integer> getIngredientes(){
		return ingredientes;
	}
	
	public static HashMap<String, Integer> getIngredientesContabilizados(){
		return ingredientesContabilizados;
	}
	
	public static void zerarIngredientesContabilizados() {
        ingredientesContabilizados.clear();
    }
}

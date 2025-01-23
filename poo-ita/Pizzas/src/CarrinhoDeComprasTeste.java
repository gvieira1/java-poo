
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarrinhoDeComprasTeste {

    @Test
    public void testAdicionarPizzaComIngredientes() {
        Pizza pizza1 = new Pizza();
        pizza1.adicionaIngrediente("queijo");
        pizza1.adicionaIngrediente("tomate");
        
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adicionarPizza(pizza1);
        
        assertEquals(15, carrinho.getPrecoTotal(), 0.01);
    }

    @Test
    public void testAdicionarPizzaSemIngredientes() {
        Pizza pizza2 = new Pizza();
        
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adicionarPizza(pizza2);
        
        assertEquals(0, carrinho.getPrecoTotal(), 0.01);
    }

    @Test
    public void testPrecoTotalCarrinho() {
        Pizza pizza1 = new Pizza();
        pizza1.adicionaIngrediente("queijo");
        pizza1.adicionaIngrediente("tomate");
        
        Pizza pizza2 = new Pizza();
        pizza2.adicionaIngrediente("queijo");
        pizza2.adicionaIngrediente("presunto");
        pizza2.adicionaIngrediente("cebola");

        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adicionarPizza(pizza1);
        carrinho.adicionarPizza(pizza2);
        
        assertEquals(35.0, carrinho.getPrecoTotal(), 0.01);
    }
}

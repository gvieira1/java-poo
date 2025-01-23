import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PizzaTest {

    @BeforeEach
    public void setup() {
        Pizza.zerarIngredientesContabilizados();
    }

    @Test
    public void testPrecoCom2Ingredientes() {
        Pizza pizza = new Pizza();
        pizza.adicionaIngrediente("queijo");
        pizza.adicionaIngrediente("tomate");
        
        double preco = pizza.getPreco();
        assertEquals(15.0, preco);
    }

    @Test
    public void testPrecoCom3Ingredientes() {
        Pizza pizza = new Pizza();
        pizza.adicionaIngrediente("queijo");
        pizza.adicionaIngrediente("tomate");
        pizza.adicionaIngrediente("presunto");
        
        double preco = pizza.getPreco();
        assertEquals(20.0, preco);
    }

    @Test
    public void testPrecoCom6Ingredientes() {
        Pizza pizza = new Pizza();
        pizza.adicionaIngrediente("queijo");
        pizza.adicionaIngrediente("tomate");
        pizza.adicionaIngrediente("presunto");
        pizza.adicionaIngrediente("cebola");
        pizza.adicionaIngrediente("azeitona");
        pizza.adicionaIngrediente("pimentao");
        
        double preco = pizza.getPreco();
        assertEquals(23.0, preco);
    }

    @Test
    public void testRegistroDeIngredientes() {
        Pizza pizza = new Pizza();
        pizza.adicionaIngrediente("queijo");
        pizza.adicionaIngrediente("tomate");
        
        assertEquals(2, pizza.getIngredientes().size());
        
        assertTrue(Pizza.getIngredientesContabilizados().containsKey("queijo"));
        assertTrue(Pizza.getIngredientesContabilizados().containsKey("tomate"));
        
        assertEquals(1, Pizza.getIngredientesContabilizados().get("queijo"));
        assertEquals(1, Pizza.getIngredientesContabilizados().get("tomate"));
    }
}
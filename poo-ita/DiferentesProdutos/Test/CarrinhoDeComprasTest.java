import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarrinhoDeComprasTest {

    @Test
    public void testAdicionaERemoveProduto() {
        Produto produto1 = new Produto("Produto A", "123", 10.0);
        ProdutoComTamanho produto2 = new ProdutoComTamanho("Produto B", "124", 20.0, "M");
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionaProduto(produto1, 2);
        carrinho.adicionaProduto(produto2, 1);
       
        assertEquals(2, carrinho.getProdutos().get(produto1));
        assertEquals(1, carrinho.getProdutos().get(produto2));

        carrinho.removeProduto(produto1, 1);
        assertEquals(1, carrinho.getProdutos().get(produto1));
        
        carrinho.removeProduto(produto1, 1);
        assertFalse(carrinho.getProdutos().containsKey(produto1));  // Produto removido

        carrinho.removeProduto(produto2, 1);
        assertFalse(carrinho.getProdutos().containsKey(produto2));  // Produto removido
    }

    @Test
    public void testCalculaTotal() {
        Produto produto1 = new Produto("Produto A", "123", 10.0);
        ProdutoComTamanho produto2 = new ProdutoComTamanho("Produto B", "124", 20.0, "M");
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionaProduto(produto1, 2);
        carrinho.adicionaProduto(produto2, 1);

        assertEquals(40.0, carrinho.calculaTotal());
    }
}

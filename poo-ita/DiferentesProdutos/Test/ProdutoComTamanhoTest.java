import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoComTamanhoTest {

    @Test
    public void testEqualsEHashCode() {
        ProdutoComTamanho produto1 = new ProdutoComTamanho("Produto A", "123", 10.0, "M");
        ProdutoComTamanho produto2 = new ProdutoComTamanho("Produto A", "123", 10.0, "M");
        ProdutoComTamanho produto3 = new ProdutoComTamanho("Produto A", "123", 10.0, "L");
        ProdutoComTamanho produto4 = new ProdutoComTamanho("Produto B", "456", 10.0, "M");

        // Teste de igualdade
        assertEquals(produto1, produto2);
        assertNotEquals(produto1, produto3);
        assertNotEquals(produto1, produto4);

        // Teste de hashCode
        assertEquals(produto1.hashCode(), produto2.hashCode());
        assertNotEquals(produto1.hashCode(), produto3.hashCode());
        assertNotEquals(produto1.hashCode(), produto4.hashCode());
    }
}

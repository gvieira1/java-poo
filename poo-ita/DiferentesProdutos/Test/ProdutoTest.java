import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    public void testEqualsEHashCode() {
        Produto produto1 = new Produto("Produto A", "123", 10.0);
        Produto produto2 = new Produto("Produto B", "123", 20.0);
        Produto produto3 = new Produto("Produto C", "456", 10.0);

        // Teste de igualdade
        assertEquals(produto1, produto2);
        assertNotEquals(produto1, produto3);

        // Teste de hashCode
        assertEquals(produto1.hashCode(), produto2.hashCode());
        assertNotEquals(produto1.hashCode(), produto3.hashCode());
    }
}

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import basics_set.ConjuntoPalavrasUnicas;

class ConjuntoPalavrasUnicasTest {

	private ConjuntoPalavrasUnicas palavra;
	
	@BeforeEach
	void init() {
		palavra = new ConjuntoPalavrasUnicas();
		palavra.adicionarPalavra("Bolo");
		palavra.adicionarPalavra("Torta");
		palavra.adicionarPalavra("Empada");
	}
	
	@Test
	void removerPalavraTest() {
		palavra.removerPalavra("Bolo");
		int comprimento = palavra.size();
		assertEquals(2, comprimento);
	}
	
	@Test
	void verificarPalavraExistenteTest() {
		assertTrue(palavra.verificarPalavra("Torta"));
	}
	
	@Test
	void verificarPalavraNaoExistenteTest() {
		assertFalse(palavra.verificarPalavra("Abacate"));
	}

}

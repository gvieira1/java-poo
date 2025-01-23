import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmbaralhadorInverterParImparTest {

	@Test
	void testEmbaralharPalavraPar() {
		String palavraOriginal = "abcdef";
		Embaralhador embaralhador = new EmbaralhadorInverterParImpar();
		String palavraEmbaralhada = embaralhador.embaralhar(palavraOriginal);
		
		assertEquals("badcfe", palavraEmbaralhada);
	}
	
	@Test
	void testEmbaralharPalavraImpar() {
		String palavraOriginal = "abcdefg";
		Embaralhador embaralhador = new EmbaralhadorInverterParImpar();
		String palavraEmbaralhada = embaralhador.embaralhar(palavraOriginal);
		
		assertEquals("badcfeg", palavraEmbaralhada, "A última letra permanece inalterada");
	}

}


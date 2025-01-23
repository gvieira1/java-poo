import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AutoridadeTest {

	@Test
	public void testInformal() {
		FormatadorNome informal = new Informal();
		Autoridade autoridadeInformal = new Autoridade("Pedro", "Cabral", informal);
        assertEquals("Pedro", autoridadeInformal.getTratamento());
    }
	
	@Test
	public void testRespeitosoM() {
		 FormatadorNome respeitosoMasculino = new Respeitoso("masculino");
		 Autoridade autoridadeRespeitosa = new Autoridade("Pedro", "Cabral", respeitosoMasculino);
		 assertEquals("Sr. Cabral", autoridadeRespeitosa.getTratamento());
	}
	
	@Test
	public void testRespeitosoF() {
		 FormatadorNome respeitosoFeminino = new Respeitoso("feminino");
		 Autoridade autoridadeRespeitosa = new Autoridade("Maria", "Cabral", respeitosoFeminino);
		 assertEquals("Sra. Cabral", autoridadeRespeitosa.getTratamento());
	}

	@Test
	public void testComTitulo() {
		FormatadorNome comTitulo = new ComTitulo("Magnífico");
		Autoridade autoridadeComTitulo = new Autoridade("Pedro", "Cabral", comTitulo);
		assertEquals("Magnífico Pedro Cabral", autoridadeComTitulo.getTratamento());
	}
}



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import search_list.SomaNumeros;

class SomaNumerosTest {

	private SomaNumeros soma;
	
	@BeforeEach
	void init() {
		soma = new SomaNumeros();
		soma.adicionarNumero(2);
		soma.adicionarNumero(8);
		soma.adicionarNumero(99);
		soma.adicionarNumero(-1);
	}
	
	@Test
	void testEncontraMenor() {
		int resultado = soma.encontrarMenorNumero();
		assertEquals(-1, resultado);
	}

	@Test
	void testEncontraMaior() {
		int resultado = soma.encontrarMaiorNumero();
		assertEquals(99, resultado);
	}
	
	@Test
	void testEncontraSoma() {
		int resultado = soma.calcularSoma();
		assertEquals(108, resultado);
	}
}

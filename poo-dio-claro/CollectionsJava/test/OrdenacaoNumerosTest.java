
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import ordination_list.OrdenacaoNumeros;

class OrdenacaoNumerosTest {

	private OrdenacaoNumeros num;
	
	@BeforeEach
	void init() {
		num = new OrdenacaoNumeros();
		num.adicionarNumero(5);
		num.adicionarNumero(8);
		num.adicionarNumero(3);
		num.adicionarNumero(9);
		num.adicionarNumero(7);
	}

	@Test
	void ordenarCrescenteTest() {
		List<Integer> resultado = num.ordenarAscendente();
		List<Integer> listaEsperada = Arrays.asList(3, 5, 7, 8, 9);
		assertEquals(listaEsperada, resultado);
	}
	
	@Test
	void ordenarDecrescenteTest() {
		List<Integer> resultado = num.ordenarDescendente();
		List<Integer> listaEsperada = Arrays.asList(9, 8, 7, 5, 3);
		assertEquals(listaEsperada, resultado);
	}
	
	
}

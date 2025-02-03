import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import search_set.AgendaContatos;
import search_set.Contato;

class AgendaContatoTest {

	AgendaContatos contato;
	Set<Contato> resultado;
	Contato atualizado;
	
	@BeforeEach
	void init() {
		contato = new AgendaContatos();
		contato.adicionarContato("Osmar", 11111111);
		contato.adicionarContato("Pedro", 22222222);
		contato.adicionarContato("Joana", 33333333);
		contato.adicionarContato("Maria", 44444444);
		contato.adicionarContato("Pablo", 55555555);
		contato.adicionarContato("Larissa", 66666666);
	}
	
	@Test
	void pesquisaNomeExistenteTest() {
		resultado = contato.pesquisarPorNome("Jo");
		assertTrue(!resultado.isEmpty());
	}
	
	@Test
	void pesquisaNomeNaoExistenteTest() {
		resultado = contato.pesquisarPorNome("Oscar");
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	void atualizarContatoTest() {
	    Contato contatoAtualizado = contato.atualizarNumeroContato("Pablo", 77777777);
	    assertNotNull(contatoAtualizado);  
	    assertEquals(77777777, contatoAtualizado.getNumero());
	}

	@Test
	void atualizarContatoNaoEncontradoTest() {
	    Contato contatoNaoEncontrado = contato.atualizarNumeroContato("Joao", 88888888);
	    assertNull(contatoNaoEncontrado); 
	}


}

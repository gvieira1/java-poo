import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class EmbaralhadorAleatórioTest {
		
	    @Test
	    void testEmbaralharNaoDeveSerIgualAPalavraOriginal() {
	        String palavraOriginal = "abcdef";
	        Embaralhador embaralhador = new EmbaralhadorAleatorio();
	        String palavraEmbaralhada = embaralhador.embaralhar(palavraOriginal);
	        
	        assertNotEquals(palavraOriginal, palavraEmbaralhada, "A palavra embaralhada não deve ser igual à original");
	    }

	    @Test
	    void testEmbaralharManterOrdemDeCaracteres() {
	       
	        String palavraOriginal = "abcdef";
	        Embaralhador embaralhador = new EmbaralhadorAleatorio();
	        String palavraEmbaralhada = embaralhador.embaralhar(palavraOriginal);

	        List<Character> originalList = new ArrayList<>();
	        for (char c : palavraOriginal.toCharArray()) {
	            originalList.add(c);
	        }
	        List<Character> embaralhadaList = new ArrayList<>();
	        for (char c : palavraEmbaralhada.toCharArray()) {
	            embaralhadaList.add(c);
	        }

	        Collections.sort(originalList);
	        Collections.sort(embaralhadaList);
	        
	        assertEquals(originalList, embaralhadaList, "A palavra embaralhada deve ter os mesmos caracteres da original");
	    }

	    
	    @Test
	    void testEmbaralharComUmaUnicaLetra() {
	        String palavraOriginal = "a";
	        Embaralhador embaralhador = new EmbaralhadorAleatorio();
	        String palavraEmbaralhada = embaralhador.embaralhar(palavraOriginal);
	        assertEquals("a", palavraEmbaralhada, "A palavra com uma única letra não deve mudar");
	    }
	}





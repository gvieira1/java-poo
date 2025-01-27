package ordination_list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdenacaoNumeros{

	private List<Integer> numerosList;
	
	public OrdenacaoNumeros() {
		this.numerosList = new ArrayList<>();
	}

	public List<Integer> getNumeros() {
		return numerosList;
	}

	public void adicionarNumero(int numero) {
		numerosList.add(numero);
	}
	
	public List<Integer> ordenarAscendente(){
		Collections.sort(numerosList);
		return numerosList;
	}
	
	public List<Integer> ordenarDescendente(){
		numerosList.sort(Collections.reverseOrder());
		return numerosList;
	}

}

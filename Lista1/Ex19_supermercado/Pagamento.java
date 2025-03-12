package Ex19_supermercado;

import java.text.DecimalFormat;

public class Pagamento {
	private String metodo;
    
    public Pagamento(String metodo) {
        this.metodo = metodo;
    }

    public void processarPagamento(double valor) {
        System.out.println("\nPagamento de " + formatarPreco(valor) + " realizado com " + metodo);
    }

    private String formatarPreco(double valor) {
        DecimalFormat df = new DecimalFormat("R$ #,##0.00");
        return df.format(valor);
    }
}

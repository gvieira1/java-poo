public class Principal {

    public static void main(String[] args) {
        
        Paciente paciente1 = new Paciente(65.0, 1.66); 
        Paciente paciente2 = new Paciente(90.0, 1.84); 
        Paciente paciente3 = new Paciente(120.0, 1.72); 
        
        System.out.println("Paciente 1:");
        System.out.println("IMC: " + paciente1.calcularIMC());
        System.out.println("Diagnóstico: " + paciente1.diagnostico() + "\n");

        System.out.println("Paciente 2:");
        System.out.println("IMC: " + paciente2.calcularIMC());
        System.out.println("Diagnóstico: " + paciente2.diagnostico() + "\n");

        System.out.println("Paciente 3:");
        System.out.println("IMC: " + paciente3.calcularIMC());
        System.out.println("Diagnóstico: " + paciente3.diagnostico() + "\n");
    }
}

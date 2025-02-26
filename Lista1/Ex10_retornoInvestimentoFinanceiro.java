/* Calcule	o	retorno	de	um	investimento	financeiro	fazendo	as	contas	mês	a	mês,	sem	usar	a	
fórmula	de	juros	compostos.	O	usuário	deve	informar	quanto	será	investido	por	mês	e	
qual	será	a	taxa	de	juros	mensal.	O	programa	deve	informar	o	saldo	do	investimento	após	
um	ano	(soma	das	aplicações	mês	a	mês	considerando	os	juros	compostos),	e	perguntar	ao	
usuário	se	ele	deseja	que	seja	calculado	o	ano	seguinte,	sucessivamente.	Por	exemplo,	
caso	o	usuário	deseje	investir	R$	100,00	por	mês,	e	tenha	uma	taxa	de	juros	de	1%	ao	mês,	
o	programa	forneceria	a	seguinte	saída:	Saldo do investimento após 1 ano: 1280.93 
Deseja processar mais um ano? (S/N) */

import java.util.Scanner;

public class Ex10_retornoInvestimentoFinanceiro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o valor a ser investido por mês: R$ ");
        double investimentoMensal = scanner.nextDouble();

        System.out.print("Digite a taxa de juros mensal (em %): ");
        double taxaJuros = scanner.nextDouble() / 100; 

        double saldo = 0;
        int ano = 1;
        String continuar;

        do {
            for (int mes = 1; mes <= 12; mes++) {
                saldo = (saldo + investimentoMensal) * (1 + taxaJuros);
            }

            System.out.printf("Saldo do investimento após %d ano(s): R$ %.2f%n", ano, saldo);

            System.out.print("Deseja processar mais um ano? (S/N): ");
            continuar = scanner.next().toUpperCase();
            ano++;

        } while (continuar.equals("S"));

        System.out.println("Investimento encerrado.");
        scanner.close();
    }
}

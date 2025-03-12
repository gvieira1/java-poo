
//Qual a diferença	entre uma exceção (subclasses de Exception)	e um erro (subclasses de Error)?	

/*
 * As exceções são situações inesperadas que podem ocorrer durante a execução do
 * programa, mas que podem ser tratadas e, em muitos casos, resolvidas pelo
 * código. Elas geralmente indicam problemas comuns que o programador pode
 * antecipar e lidar adequadamente, como entradas inválidas ou falhas ao acessar
 * arquivos.
 * 
 * As exceções podem ser divididas em duas categorias: verificadas e não
 * verificadas. Exceções verificadas (checked) são aquelas que o compilador
 * exige que sejam tratadas, geralmente obrigando o programador a envolver o
 * código que pode gerar a exceção em um bloco try-catch ou declarando que o
 * método pode propagar essa exceção, como é o caso de um IOException.
 * 
 * Em contrapartida, as exceções não verificadas são aquelas que o compilador
 * não obriga a tratar ou declarar que podem ser propagadas, são exemplos de
 * exceções que não são verificadas a NullPointerException ou a
 * IllegalArgumentException. Em geral, essas exceções indicam falhas comuns de
 * programação.
 * 
 * No entanto, os erros representam falhas que impedem o ambiente de execução do
 * programa, como falha na camada de hardware ou software do sistema que faz a
 * máquina virtual ser executada, como por exemplo, falha na inicialização do
 * Java Virtual Machine, ou com o hardware onde a máquina virtual está sendo
 * executada, ou ainda, com o sistema operacional de hospedagem. Além disso, por
 * vezes mesmo que a Java Virtual Machine consiga lidar com elas, o custo de
 * lidar com tais erros muitas vezes torna o programa incontrolável e coloca em
 * risco o desenvolvedor de percas de dados ou mal funcionamento.
 * 
 * Exemplos de erros tratados como exceções inclui-se: OutOfMemoryError, que
 * embora a JVM consiga lida-lo de forma parecida com um erro que não poderia
 * ser tratado, tal como StackOverflowError, que é um erro fatal e que em regra
 * não deve ser capturado ou tratado porque não tem a ver com o código que foi
 * desenvolvido.
 * 
 * Então, exceções são situações que podem ser tratadas pelo código do
 * programa em si, enquanto erros são situações que não podem, ou seria
 * inadequado tentar tratar pelo próprio programa.
 */
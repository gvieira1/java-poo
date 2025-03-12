/*
 * Qual a diferença do efeito produzido pelos programas abaixo? Qual deles é
 * mais eficiente, assumindo que o computador tem mais de um processador?
 * 
 * //Programa A 
 * Thread[] threads = new Thread[10]; 
 * for(int i = 0;i<threads.length;i++){ 
 * 		threads[i] = new Thread(new MeuRunnable());
 * 		threads[i].start(); 
 * }
 * 
 * for(int i = 0;i<threads.length;i++){ 
 * 		threads[i].join(); 
 * }
 */

//Programa B
/*
 * Thread[] threads = new Thread[10]; 
 * for(int i = 0;i<threads.length;i++){
 * 		threads[i] = new Thread(new MeuRunnable()); 
 * 		threads[i].start();
 * 		threads[i].join(); 
 * }
 */

/*
 * O Programa A é mais eficiente porque inicia as 10 threads e as mantém em
 * execução paralela, chamando o join() apenas após o final do loop. Isso
 * permite que o sistema distribua as threads entre os núcleos disponíveis,
 * maximizando o uso do hardware e, consequentemente, melhorando o desempenho.
 * 
 * Por outro lado, o Programa B inicia cada thread e, em seguida, chama o
 * join(), fazendo com que a próxima thread só seja iniciada após a conclusão da
 * anterior. Isso resulta em um processamento sequencial, que não aproveita todo
 * o potencial da máquina.
 */
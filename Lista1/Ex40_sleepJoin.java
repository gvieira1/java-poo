/*
 * Qual	a	diferença	entre	o	método	sleep()	e	o	método	join()	da	classe	Thread?
 *
 * O método sleep() faz com que a thread pausa sua execução por um tempo
 * determinado, durante o qual ela não realiza nenhuma operação, permitindo que
 * outras threads sejam executadas. Esse método é útil quando é necessário que a
 * thread aguarde por um intervalo de tempo específico antes de retomar sua
 * execução. 
 * 
 * Já o método join() faz com que a thread chamadora aguarde até que a
 * thread em que foi chamado termine sua execução, ou seja, a thread chamadora
 * fica bloqueada até que a thread associada complete sua tarefa. Esse
 * comportamento é útil quando é necessário garantir que uma thread termine sua
 * execução antes que a execução de outras threads prossiga.
 */
/*Para	que	serve	o	modificador	synchronized?	Em	que	situações	ele	deve	ser	usado?	Por	que	
não	usar	em	todos	os	métodos	do	programa?


O synchronized é usado para garantir que apenas uma thread acesse um recurso compartilhado de cada vez,
evitando problemas como dados corrompidos. 
 
Ele deve ser utilizado quando várias threads precisam acessar a mesma informação ao mesmo tempo, para
garantir que o processo seja feito de forma segura.
  
No entanto, não deve ser usado em todos os métodos, pois isso pode diminuir o desempenho do programa, 
já que as threads terão que esperar umas pelas outras. Além disso, o uso excessivo pode causar deadlock,
onde as threads ficam travadas esperando umas pelas outras, o que atrapalha o funcionamento do sistema.*/
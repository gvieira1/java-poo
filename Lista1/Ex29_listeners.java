/*
 * Explique com suas palavras como é o mecanismo de listener usado no Swing para
 * permitir que os eventos de clique dos botões sejam tratados pelo programa.
 */	

/*
 * Esse mecanismo segue um padrão onde o objeto interessado (o listener) se 
 * registra para ser notificado quando um evento específico acontece. 
 * Para isso, implementamos a interface ActionListener, que possui o método 
 * actionPerformed(ActionEvent e), responsável por definir o comportamento 
 * desejado quando o evento é acionado.A associação entre o botão e o listener
 * é feita através do método addActionListener(), onde passamos uma instância 
 * da classe que implementa o listener. Quando o botão é clicado, um objeto do
 * tipo ActionEvent é criado e passado como argumento para o método actionPerformed(),
 * que então executa a ação programada.
 */

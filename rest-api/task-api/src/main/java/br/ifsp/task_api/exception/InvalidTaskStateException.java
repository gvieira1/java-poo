package br.ifsp.task_api.exception;

public class InvalidTaskStateException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;

	public InvalidTaskStateException(String mensagem) {
        super(mensagem);
    }
}

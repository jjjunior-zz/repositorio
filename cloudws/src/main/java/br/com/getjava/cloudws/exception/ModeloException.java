package br.com.getjava.cloudws.exception;

public class ModeloException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ModeloException(String descricao) {
		super(descricao);
	}

	public ModeloException(String descricao, Exception e) {
		super(descricao, e);
	}
}

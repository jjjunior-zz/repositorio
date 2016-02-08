package br.com.bluesoft.votacao.exception;

public class ModeloException extends Exception {

	private static final long serialVersionUID = 1L;

	public ModeloException(String descricao) {
		super(descricao);
	}

	public ModeloException(String descricao, Exception e) {
		super(descricao, e);
	}
}

package br.com.bluesoft.votacao.exception;

public class UtilException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UtilException(String descricao) {
		super(descricao);
	}

	public UtilException(String descricao, Exception e) {
		super(descricao, e);
	}
	
	public UtilException(Exception e) {
		super(e);
	}
}

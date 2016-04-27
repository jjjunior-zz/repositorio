package br.com.getjava.cloudws.exception;

public class ModelException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ModelException(String description) {
		super(description);
	}

	public ModelException(String description, Exception e) {
		super(description, e);
	}
}

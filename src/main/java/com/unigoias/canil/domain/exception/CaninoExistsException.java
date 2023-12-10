package com.unigoias.canil.domain.exception;

public class CaninoExistsException extends RuntimeException {

	private static final long serialVersionUID = 5773104929929491898L;
	
	public CaninoExistsException() {
		super("Canino já cadastrado!");
	}

	public CaninoExistsException(String mensagem) {
		super(mensagem);
	}
}

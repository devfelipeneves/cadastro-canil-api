package com.unigoias.canil.domain.exception;

public class CaninoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6117792626980075523L;

	public CaninoNotFoundException() {
		super("Canino não encontrado!");
	}
	
	public CaninoNotFoundException(String mensagem) {
		super(mensagem);
	}
}

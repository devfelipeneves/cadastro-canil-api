package com.unigoias.canil.domain.exception;

public class RacaExistsException extends RuntimeException {

	private static final long serialVersionUID = 692619923297157405L;

	public RacaExistsException() {
		super("Raca já cadastrada!");
	}
	
	public RacaExistsException(String mensagem) {
		super(mensagem);
	}
}

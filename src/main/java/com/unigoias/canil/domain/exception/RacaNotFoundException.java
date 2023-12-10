package com.unigoias.canil.domain.exception;

public class RacaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 955453572836663225L;

	public RacaNotFoundException() {
		super("Raca não encontrada!");
	}
	
	public RacaNotFoundException(String mensagem) {
		super(mensagem);
	}
}

package com.unigoias.canil.domain.exception;

public class RacaIntegrityException extends RuntimeException {

	private static final long serialVersionUID = -7021894757236137843L;

	public RacaIntegrityException(String mensagem) {
		super(mensagem);
	}
}

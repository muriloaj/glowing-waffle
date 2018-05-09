package br.com.boletoapi.boleto.services.exceptions;

public class RequiredInformationMissingException extends RuntimeException {

	private static final long serialVersionUID = -4463678723733624149L;
	
	public RequiredInformationMissingException() {}
	
	public RequiredInformationMissingException(String message) {
		super(message);
	}

}

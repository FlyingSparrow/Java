package com.sparrow.integration.exception;

/**
 * @author wangjianchun
 */
public class IntegrationException extends RuntimeException {

	private static final long serialVersionUID = -7481715650242808055L;

	public IntegrationException(){}
	
	public IntegrationException(String message){
		super(message);
	}
	
	public IntegrationException(String message, Throwable cause){
		super(message, cause);
	}
	
	public IntegrationException(Throwable cause){
		super(cause);
	}
	
}

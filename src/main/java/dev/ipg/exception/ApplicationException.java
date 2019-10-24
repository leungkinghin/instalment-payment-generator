package dev.ipg.exception;

public class ApplicationException extends RuntimeException {
	
	public ApplicationException(String errMsg) {
		super(errMsg);
	}
	public ApplicationException(Throwable e) {
		super(e);
	}
}

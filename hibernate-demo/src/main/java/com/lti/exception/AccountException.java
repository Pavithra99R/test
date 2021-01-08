package com.lti.exception;

public class AccountException extends RuntimeException{

	public AccountException() {
		super();
	}

	public AccountException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountException(String message) {
		super(message);
	}

	public AccountException(Throwable cause) {
		super(cause);
	}
	
//	public AccountException(String s){
//		super(s);
//	}

}

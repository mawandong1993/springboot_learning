package com.note.cloud_note.exception;

import java.io.Serializable;

public class PasswordException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8382869134049117087L;

	public PasswordException() {
	}

	public PasswordException(String message) {
		super(message);
	}

	public PasswordException(Throwable cause) {
		super(cause);
	}

	public PasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

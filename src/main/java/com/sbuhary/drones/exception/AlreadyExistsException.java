package com.sbuhary.drones.exception;

public class AlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -6569851755443489793L;

	public AlreadyExistsException() {
		super();
	}

	public AlreadyExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public AlreadyExistsException(final String message) {
		super(message);
	}

	public AlreadyExistsException(final Throwable cause) {
		super(cause);
	}
}
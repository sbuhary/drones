package com.sbuhary.drones.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8717039610584691161L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(final String message) {
		super(message);
	}

	public NotFoundException(final Throwable cause) {
		super(cause);
	}

	public NotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
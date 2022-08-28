package com.sbuhary.drones.exception;

public class LimitExceededException extends RuntimeException {

	private static final long serialVersionUID = 3025954355094635885L;

	public LimitExceededException() {
		super();
	}

	public LimitExceededException(final String message) {
		super(message);
	}

	public LimitExceededException(final Throwable cause) {
		super(cause);
	}

	public LimitExceededException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
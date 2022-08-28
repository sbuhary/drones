package com.sbuhary.drones.exception;

/**
 * 
 * @author SBUHARY
 *
 */
public class NotAllowedException extends RuntimeException {

	private static final long serialVersionUID = -659984073541999540L;

	public NotAllowedException() {
		super();
	}

	public NotAllowedException(final String message) {
		super(message);
	}

	public NotAllowedException(final Throwable cause) {
		super(cause);
	}

	public NotAllowedException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
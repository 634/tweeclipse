package com.github.tweeclipse.exceptions;

public class TweeclipseException extends RuntimeException {

	private static final long serialVersionUID = 7157542916676011015L;

	public TweeclipseException() {
		super();
	}

	public TweeclipseException(Throwable t) {
		super(t);
	}

	public TweeclipseException(String m) {
		super(m);
	}

	public TweeclipseException(String s, Throwable t) {
		super(s, t);
	}
}

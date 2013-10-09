package com.magnabyte.pedidonotifmail.util;

public class PedidoException extends RuntimeException {
	private static final long serialVersionUID = -3349271878727228928L;

	public PedidoException() {
		super();
	}

	public PedidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public PedidoException(String message) {
		super(message);
	}

	public PedidoException(Throwable cause) {
		super(cause);
	}

}

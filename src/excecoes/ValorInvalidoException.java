package excecoes;

@SuppressWarnings("serial")
public class ValorInvalidoException extends Exception {

	public ValorInvalidoException() {
	}

	public ValorInvalidoException(String message) {
		super(message);
	}

	public ValorInvalidoException(Throwable cause) {
		super(cause);
	}

	public ValorInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValorInvalidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

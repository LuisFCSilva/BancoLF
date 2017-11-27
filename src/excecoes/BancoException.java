package excecoes;

@SuppressWarnings("serial")
public class BancoException extends Exception {

	public BancoException() {
		// TODO Auto-generated constructor stub
	}

	public BancoException(String message) {
		super(message);
	}

	public BancoException(Throwable cause) {
		super(cause);
	}

	public BancoException(String message, Throwable cause) {
		super(message, cause);
	}

	public BancoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

package excecoes;

@SuppressWarnings("serial")
public class ContaInativaException extends Exception {

	public ContaInativaException() {
	}

	public ContaInativaException(String arg0) {
		super(arg0);
	}

	public ContaInativaException(Throwable arg0) {
		super(arg0);
	}

	public ContaInativaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ContaInativaException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}

package objects;

public class ContaCorrente extends Conta {
	
	
	public ContaCorrente(Cliente cliente) {
		super(cliente);
		this.setTipo("Corrente");
		this.setSaldo(50f);
		this.setMensalidade(30f);
		System.out.println("Conta " + this.getTipo() + " criada com sucesso.");
	}

}

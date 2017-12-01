package objects;

public class ContaPoupanca extends Conta {
	
	
	public ContaPoupanca(Cliente cliente) {
		super(cliente);
		this.setTipo("Poupanca");
		this.setSaldo(50f);
		this.setMensalidade(30f);
		System.out.println("Conta " + this.getTipo() + " criada com sucesso.");
	}

}

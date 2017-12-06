package objects;

import java.time.LocalDate;

public class ContaCorrente extends Conta {
	
	
	public ContaCorrente(Cliente cliente) {
		super(cliente);
		this.setTipo("Corrente");
		this.setSaldo(50f);
		this.setMensalidade(30f);
		System.out.println("Conta " + this.getTipo() + " criada com sucesso.");
	}
	
	public ContaCorrente(Cliente cliente, String tipoConta, boolean status, LocalDate data, int numConta, float saldo) {
		this.setCliente(cliente);
		this.setTipo(tipoConta);
		this.setStatus(status);
		this.setDataCriacao(data);
		this.setNumeroConta(numConta);
		this.setSaldo(saldo);
	}

}

package objects;

import java.time.LocalDate;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(Cliente cliente) {
		super(cliente);
		this.setTipo("Poupanca");
		this.setSaldo(50f);
		this.setMensalidade(30f);
		System.out.println("Conta " + this.getTipo() + " criada com sucesso.");
	}

	public ContaPoupanca(Cliente cliente, String tipoConta, boolean status, LocalDate data, int numConta, float saldo) {
		this.setCliente(cliente);
		this.setTipo(tipoConta);
		this.setStatus(status);
		this.setDataCriacao(data);
		this.setNumeroConta(numConta);
		this.setSaldo(saldo);
	}

}

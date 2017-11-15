package objects;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 * @author Luis Fernando
 *
 */
public abstract class Conta {

	private Cliente cliente;
	private float saldo;
	private int numeroConta;
	private float mensalidade;
	private boolean status;
	private String tipo;
	private LocalDate dataCriacao;
	private LocalDateTime dataTransacao;

	private enum Operacao {
		SAQUE, DEPOSITO, TRANSFERENCIA, MENSALIDADE
	}

	public void exibirSaldo() {

	}

	public void sacar(float valor) {

	}
	
	/**
	 * Metodo não será implementado (a principio)
	 * @param valor
	 */
	public void depositar(float valor) {

	}

	public void pagarMensalidade() {

	}

	public void transferir(Conta conta, float valor) {

	}

	public void inativarConta() {

	}

	public void gravarExtrato(float valor, LocalDateTime dataTransacao, Operacao tipoOperacao) {

	}

	public void exibirExtrato() {

	}

	public void exibirDetalhesConta() {

	}

}

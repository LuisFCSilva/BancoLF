package objects;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import excecoes.BancoException;
import excecoes.ContaInativaException;
import excecoes.SaldoInsuficienteException;
import excecoes.ValorInvalidoException;

/**
 * 
 * @author Luis Fernando
 *
 */
public class Conta {

	private Cliente cliente;
	private float saldo;
	private int numeroConta;
	private float mensalidade;
	private boolean status;
	private String tipo;
	private LocalDate dataCriacao;
	private LocalDateTime dataTransacao;

	private NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance();

	private enum Operacao {
		SAQUE, DEPOSITO, TRANSFERENCIA, MENSALIDADE
	}

	public void exibirSaldo() {

	}

	/**
	 * 
	 * @param valor
	 *            -- valor do saque
	 * @throws ValorInvalidoException
	 *             -- Valores negativos e igual a zero
	 * @throws ContaInativaException
	 *             -- Status da conta igual FALSE
	 * @throws SaldoInsuficienteException
	 *             -- valor sacado MAIOR que o saldo da conta
	 * @throws BancoException
	 *             -- Outros problemas e erros
	 */
	public void sacar(float valor)
			throws ValorInvalidoException, ContaInativaException, SaldoInsuficienteException, BancoException {
		if (valor <= saldo && valor > 0 && status) {
			saldo -= valor;
			System.out.println("Saque no valor de " + formatoMoeda.format(valor) + " realizado com sucesso.");
			gravarExtrato(valor, LocalDateTime.now(), Operacao.SAQUE);
		} else if (valor > saldo) {
			throw new SaldoInsuficienteException("Saldo insuficiente para realizar está operação.");
		} else if (valor <= 0) {
			throw new ValorInvalidoException("Valor inválido para executar está operação.");
		} else if (status == false) {
			throw new ContaInativaException("A conta está inativa e não pode executar está operação.");
		} else {
			throw new BancoException("Sistema temporariamente indisponível. Tente novamente mais tarde.");
		}
	}

	public void depositar(float valor) throws ValorInvalidoException, ContaInativaException, BancoException {
		if (valor > 0 && status) {
			saldo += valor;
		} else if (valor <= 0) {
			throw new ValorInvalidoException("Valor inválido para executar está operação.");
		} else if (status == false) {
			throw new ContaInativaException("A conta está inativa e não pode executar está operação.");
		} else {
			throw new BancoException("Sistema temporariamente indisponível. Tente novamente mais tarde.");
		}

	}

	public void pagarMensalidade() {

	}

	/**
	 * Metodo não será implementado (a principio)
	 * 
	 * @param valor
	 */
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public float getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(float mensalidade) {
		this.mensalidade = mensalidade;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(LocalDateTime dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

}

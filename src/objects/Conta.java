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

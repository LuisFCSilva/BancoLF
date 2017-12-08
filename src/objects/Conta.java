package objects;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import excecoes.BancoException;
import excecoes.ContaInativaException;
import excecoes.SaldoInsuficienteException;
import excecoes.ValorInvalidoException;

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

	private NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance();
	private DateTimeFormatter formatadorDataSimples = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private DateTimeFormatter formatadorDataMedium = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

	private List<String> extrato = new ArrayList<>();

	public Conta() {
	}

	public Conta(Cliente cliente) {
		this.cliente = cliente;
		this.status = true;
		this.dataCriacao = LocalDate.now();
		this.setNumeroConta(99999);
	}

	private enum Operacao {
		SAQUE, DEPOSITO, TRANSFERENCIA, MENSALIDADE
	}

	public void exibirSaldo() {
		System.out.println("Saldo atual: " + formatoMoeda.format(saldo));
	}

	/**
	 * 
	 * @param valor
	 *            -- Valor do saque
	 * @throws ValorInvalidoException
	 *             -- Valores negativos ou igual a zero
	 * @throws SaldoInsuficienteException
	 *             -- Valor sacado MAIOR que o saldo da conta
	 * @throws BancoException
	 *             -- Outros problemas e erros
	 */
	public void sacar(float valor)
			throws ValorInvalidoException, SaldoInsuficienteException, BancoException {
		if (valor <= saldo && valor > 0 && status) {
			saldo -= valor;
			System.out.println("Saque no valor de " + formatoMoeda.format(valor) + " realizado com sucesso.");
			gravarExtrato(valor, LocalDateTime.now(), Operacao.SAQUE);
		} else if (valor > saldo) {
			throw new SaldoInsuficienteException("Saldo insuficiente para realizar está operação.");
		} else if (valor <= 0) {
			throw new ValorInvalidoException("Valor inválido para executar está operação.");
		} else {
			throw new BancoException("Sistema temporariamente indisponível. Tente novamente mais tarde.");
		}
	}

	/**
	 * 
	 * @param valor
	 *            -- Valor depositado
	 * @throws ValorInvalidoException
	 *             -- Valores negativos ou igual a zero
	 * @throws BancoException
	 *             -- Outros problemas e erros
	 */
	public void depositar(float valor) throws ValorInvalidoException, BancoException {
		if (valor > 0 && status) {
			saldo += valor;
			System.out.println("Deposito no valor de " + formatoMoeda.format(valor) + " realizado com sucesso.");
		} else if (valor <= 0) {
			throw new ValorInvalidoException("Valor inválido para executar está operação.");
		} else {
			throw new BancoException("Sistema temporariamente indisponível. Tente novamente mais tarde.");
		}

	}

	/**
	 * 
	 * @throws ValorInvalidoException
	 *             -- Valores negativos ou igual a zero
	 * @throws SaldoInsuficienteException
	 *             -- Valor sacado MAIOR que o saldo da conta
	 * @throws BancoException
	 *             -- Outros problemas e erros
	 */
	public void pagarMensalidade()
			throws ValorInvalidoException, SaldoInsuficienteException, BancoException {
		if (this.mensalidade <= saldo && this.mensalidade > 0 && status) {
			saldo -= this.mensalidade;
			System.out
					.println("Mensalidade no valor de " + formatoMoeda.format(this.mensalidade) + " paga com sucesso.");
			gravarExtrato(this.mensalidade, LocalDateTime.now(), Operacao.MENSALIDADE);
		} else if (this.mensalidade <= 0) {
			throw new ValorInvalidoException("Valor inválido para executar está operação.");
		} else if (this.mensalidade > saldo) {
			throw new SaldoInsuficienteException("Saldo insuficiente para realizar está operação.");
		} else {
			throw new BancoException("Sistema temporariamente indisponível. Tente novamente mais tarde.");
		}
	}

	/**
	 * Metodo não será implementado (a principio)
	 * 
	 * @param valor
	 */
	public void transferir(Conta conta, float valor) {
	}

	/**
	 * 
	 * @throws ContaInativaException
	 *             -- Status da conta igual FALSE
	 * @throws BancoException
	 *             -- Retornar erro caso o saldo da conta esteja maior do que ZERO
	 */
	public void inativarConta() throws ContaInativaException, BancoException {
		if (status == true && saldo == 0) {
			status = false;
			System.out.println("Conta " + tipo + " inativada com sucesso.");
		} else if (saldo > 0 && status) {
			throw new BancoException("A conta possui saldo e não pode ser inativada.");
		} else {
			throw new BancoException("Sistema temporariamente indisponível. Tente novamente mais tarde.");
		}

	}

	/**
	 * Adicionar um String com os dados da operação em um ArrayList
	 * 
	 * @param valor
	 * @param dataTransacao
	 * @param tipoOperacao
	 */
	public void gravarExtrato(float valor, LocalDateTime dataTransacao, Operacao tipoOperacao) {
		String textoExtrato;
		textoExtrato = (formatadorDataMedium.format(dataTransacao) + " - " + tipoOperacao.toString() + " - "
				+ formatoMoeda.format(valor));
		extrato.add(textoExtrato);
	}

	/**
	 * Exibe o extrato inserido no ArrayList
	 */
	public void exibirExtrato() {
		System.out.printf("\n\n\n%80s", "********* Extrato *********\n");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------\n");
		System.out.printf("\n%s %5s %5s", "Data ", " Tipo Operacao ", " Valor ");
		System.out.println("\n_________________________________________________");
		for (String listExtrato : extrato) {
			System.out.println(listExtrato);
			System.out.println("_________________________________________________");
		}
	}

	/**
	 * Exibição dos detalhes da conta no Console
	 */
	public void exibirDetalhesConta() {
		System.out.print("----------------------------------");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------\n");
		System.out.printf("\n%23s", "Status da Conta:");
		System.out.printf("\nCliente: %25s", this.cliente.getNome());
		System.out.printf("\nTipo de Conta: %21s", this.getTipo());
		System.out.printf("\nNº Conta: %24s", this.getNumeroConta());
		System.out.printf("\nSaldo atual: %23s", formatoMoeda.format(saldo));
		System.out.printf("\nStatus: %25s", this.converterStatus(status));
		System.out.printf("\nConta criada em: %21s", dataCriacao.format(formatadorDataSimples));
		System.out.print("\n----------------------------------");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------\n");
	}

	/**
	 * Conversão simples do status usando operador ternário
	 * @param status
	 * @return
	 */
	public String converterStatus(boolean status) {
		return status == true ? "Ativo" : "Inativo";
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
		if (numeroConta == 0) {
			setNumeroConta(99999);
			return numeroConta;
		} else {
			return numeroConta;
		}
	}

	public void setNumeroConta(int numeroConta) {
		Random r = new Random(numeroConta);
		int numero = r.nextInt();
		this.numeroConta = numero;
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

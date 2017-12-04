package exibicao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import excecoes.BancoException;
import excecoes.ContaInativaException;
import excecoes.SaldoInsuficienteException;
import excecoes.ValorInvalidoException;
import inputOutput.GerenciamentoDadosConta;
import interfaces.UtilsBanco;
import objects.Cliente;
import objects.Conta;
import objects.ContaCorrente;
import objects.ContaPoupanca;

public class ExibicaoBanco implements UtilsBanco {

	private Scanner scan = new Scanner(System.in);

	private Conta conta;
	private Cliente cliente;
	private static GerenciamentoDadosConta gerCont = new GerenciamentoDadosConta();

	@Override
	public void apresentarBanco() {
		System.out.printf("\n%80s", "********* Banco Deycoval *********\n");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------\n");
	}

	@Override
	public void carregarConta() {
		System.out.println("Escolha uma opção: ");
		System.out.print("[1] para criar um novo usuário - [2] para carregar uma conta já existente: ");

		int escolha = scan.nextInt();

		switch (escolha) {
		case 1:
			criarUsuario();
			break;
		case 2:
			try {
				conta = gerCont.carregarDadosArquivo();
				exibirOperacoes(conta);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				criarUsuario();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			exibirErro();
			carregarConta();
			break;
		}
	}

	@Override
	public void criarUsuario() {
		System.out.print("\nPor favor, digite o seu nome: ");
		String nome = scan.next();

		cliente = new Cliente(nome);
		criarConta(cliente);
	}

	@Override
	public void criarConta(Cliente cliente) {
		System.out.println("\nBem vindo, " + cliente.getNome());
		System.out.println("Escolha um tipo de conta que deseja criar... ");
		System.out.print("[CC] para Conta Poupanca - [CP] para Conta Corrente: ");

		String tipoConta = scan.next();

		switch (tipoConta) {
		case "CC":
			conta = new ContaCorrente(cliente);
			exibirOperacoes(conta);
			break;
		case "CP":
			conta = new ContaPoupanca(cliente);
			exibirOperacoes(conta);
			break;
		default:
			exibirErro();
			criarConta(cliente);
			break;
		}

	}
	
	@Override
	public void exibirOperacoes(Conta conta) {
		apresentarBanco();
		System.out.println("Bem vindo, " + conta.getCliente().getNome());
		System.out.println("Escolha um tipo de operação:");
		System.out.print("[1] para ver o Saldo - "
						+"[2] para saque - "
						+"[3] para deposito - "
						+"[4] para transferência - "
						+"[5] para pagar a Mensalidade - "
						+"[6] para ver o extrato - "
						+"[0] para avançar: ");
		
		int escolha = scan.nextInt();
		float valor;
		
		switch(escolha) {
			case 1:
				conta.exibirSaldo();
				repetirOperacoes(conta);
				break;
			case 2:
				System.out.print("\nDigite o valor a ser sacado: ");
				valor = scan.nextFloat();
			try {
				conta.sacar(valor);
			} catch (ValorInvalidoException e) {
				System.out.println(e.getMessage());
				exibirOperacoes(conta);
			} catch (ContaInativaException e) {
				e.printStackTrace();
			} catch (SaldoInsuficienteException e) {
				System.out.println(e.getLocalizedMessage());
				exibirOperacoes(conta);
			} catch (BancoException e) {
				e.getMessage();
			}
			
		}
	}

	@Override
	public void repetirOperacoes(Conta conta) {

	}

	@Override
	public void finalizarOperacoes(Conta conta) {

	}

	public void exibirErro() {
		System.out.println("\nO valor informado é inválido." + "\nTente novamente...");
	}
}

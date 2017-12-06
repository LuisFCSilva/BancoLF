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
	
	public ExibicaoBanco() {
		apresentarBanco();
		carregarConta();
	}

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
		if (conta.isStatus()) {
			apresentarBanco();
			System.out.println("Bem vindo, " + conta.getCliente().getNome());
			System.out.println("Escolha um tipo de operação:");
			System.out.print( "[1] para ver o Saldo - " 
							+ "[2] para saque - " 
							+ "[3] para deposito - "
							+ "[4] para transferência - " 
							+ "[5] para pagar a Mensalidade - " 
							+ "[6] para ver o extrato - "
							+ "[0] para avançar: ");

			int escolha = scan.nextInt();
			float valor;

			switch (escolha) {
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
				} catch (SaldoInsuficienteException e) {
					System.out.println(e.getLocalizedMessage());
					exibirOperacoes(conta);
				} catch (BancoException e) {
					e.getMessage();
				} finally {
					repetirOperacoes(conta);
				}
				break;
			case 3:
				System.out.print("\nDigite o valor a ser depositado: ");
				valor = scan.nextFloat();
				try {
					conta.depositar(valor);
				} catch (ValorInvalidoException e) {
					System.out.println(e.getMessage());
					exibirOperacoes(conta);
				} catch (BancoException e) {
					System.out.println(e.getMessage());
					exibirOperacoes(conta);
				} finally {
					repetirOperacoes(conta);
				}
				break;
			case 4:
				System.out.print("\nOpção Inválida ");
				exibirOperacoes(conta);
				break;
			case 5:
				try {
					conta.pagarMensalidade();
				} catch (ValorInvalidoException e) {
					System.out.println(e.getMessage());
					exibirOperacoes(conta);
				} catch (SaldoInsuficienteException e) {
					System.out.println(e.getMessage());
					exibirOperacoes(conta);
				} catch (BancoException e) {
					System.out.println(e.getMessage());
					exibirOperacoes(conta);
				} finally {
					repetirOperacoes(conta);
				}
				break;
			case 6:
				conta.exibirExtrato();
				repetirOperacoes(conta);
				break;
			case 0:
				finalizarOperacoes(conta);
				break;
			default:
				exibirErro();
				exibirOperacoes(conta);
				break;
			}
		} else {
			finalizarOperacoes(conta);
		}
	}

	@Override
	public void repetirOperacoes(Conta conta) {
		System.out.print("\nDeseja executa uma nova operação: [S / N]: ");
		
		String escolha = scan.next();
		
		switch(escolha) {
			case "S":
				exibirOperacoes(conta);
				break;
			case "N":
				finalizarOperacoes(conta);
				break;
			default:
				exibirErro();
				break;
		}
	}

	@Override
	public void finalizarOperacoes(Conta conta) {
		apresentarBanco();
		System.out.println("Escolha uma das opção abaixo ..");
		System.out.print( "[1] para ver o status da conta - " 
						+ "[2] para inativar a conta - " 
						+ "[3] para realizar nova operação - " 
						+ "[0] para sair: ");
		
		int escolha = scan.nextInt();
		
		switch(escolha) {
			case 1:
				conta.exibirDetalhesConta();
				finalizarOperacoes(conta, "");
				break;
			case 2:
				try {
					conta.inativarConta();
				} catch (ContaInativaException e) {
					System.out.println(e.getMessage());
					finalizarOperacoes(conta);
				} catch (BancoException e) {
					System.out.println(e.getMessage());
					finalizarOperacoes(conta);
				} finally {
					finalizarOperacoes(conta, "");
				}
				break;
			case 3:
				if(conta.isStatus()) {
				exibirOperacoes(conta);
				} else {
					System.out.println("\nConta inativa. Impossível realizar novas operações.");
				}
				finalizarOperacoes(conta, "");
				break;
			case 0:
				finalizarGravarConta();
				break;
			default:
				exibirErro();
				finalizarOperacoes(conta);
				break;
		}
		
	}

	public void exibirErro() {
		System.out.println("\nO valor informado é inválido." + "\nTente novamente...");
	}
	
	public void finalizarGravarConta() {
		try {
			gerCont.gravarDadosArquivo(conta);
		} catch (BancoException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.printf("%80s", "Obrigado por utilizar o banco Deycoval!\n");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------");
		System.out.print("----------------------------------\n");
		System.exit(0);
	}
	
	public void finalizarOperacoes(Conta conta, String str) {
		System.out.print("\nDeseja realizar outra operação? [S/N]: ");

		String escolha = scan.next();

		switch (escolha) {
		case "S":
			finalizarOperacoes(conta);
			break;
		case "N":
			finalizarGravarConta();
			break;
		default:
			System.out.println("\nOpção invalida." + "Tente novamente ..");
			repetirOperacoes(conta);
			break;
		}
	}
}

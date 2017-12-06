package inputOutput;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import excecoes.BancoException;
import interfaces.GerenciamentoContas;
import objects.Cliente;
import objects.Conta;
import objects.ContaCorrente;
import objects.ContaPoupanca;

public class GerenciamentoDadosConta implements GerenciamentoContas {

	private static final String ARQUIVO_CONTA = "conta.txt";
	private Conta conta;
	private Cliente cliente;

	@Override
	public void gravarDadosArquivo(Conta conta) throws BancoException, IOException {
		File arquivo = new File(ARQUIVO_CONTA);
		if (!arquivo.exists()) {
			try {
				arquivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CONTA))) {
			writer.write(conta.getCliente().getNome() + "," + conta.getDataCriacao() + "," + conta.getNumeroConta()
					+ "," + conta.isStatus() + "," + conta.getTipo() + "," + conta.getSaldo());
		}

	}

	@Override
	public Conta carregarDadosArquivo() throws FileNotFoundException, IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CONTA))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split("\\,");

				String nomeUsuario = tokens[0];

				cliente = new Cliente(nomeUsuario);

				String data = tokens[1];
				LocalDate dataCriacaoConta = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

				int numConta = Integer.parseInt(tokens[2]);

				boolean statusConta = Boolean.parseBoolean(tokens[3]);

				String tipoConta = tokens[4];

				float saldoConta = Float.parseFloat(tokens[5]);

				if (tipoConta.equals("Corrente")) {
					conta = new ContaCorrente(cliente, tipoConta, statusConta, dataCriacaoConta, numConta, saldoConta);
					return conta;
				} else if (tipoConta.equals("Poupanca")) {
					conta = new ContaPoupanca(cliente, tipoConta, statusConta, dataCriacaoConta, numConta, saldoConta);
					return conta;
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("\nArquivo não encontrado, favor criar um novo usuário.");
		}
		return conta;
	}

	@Override
	public void gravarExtrato(float valor, LocalDateTime dataTransacao, Operacao tipoOperacao) {

	}

}

package inputOutput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

import excecoes.BancoException;
import interfaces.GerenciamentoContas;
import objects.Conta;

public class GerenciamentoDadosConta implements GerenciamentoContas {

	@Override
	public void gravarDadosArquivo(Conta conta) throws BancoException, IOException {

	}

	@Override
	public Conta carregarDadosArquivo() throws FileNotFoundException, IOException {
		return null;
	}

	@Override
	public void gravarExtrato(float valor, LocalDateTime dataTransacao, Operacao tipoOperacao) {
		
	}

}

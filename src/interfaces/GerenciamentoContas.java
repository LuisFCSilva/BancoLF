package interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

import excecoes.BancoException;
import objects.Conta;

public interface GerenciamentoContas {
	
	public enum Operacao {
		SAQUE, DEPOSITO, TRANSFERENCIA, MENSALIDADE
	}
	
	public void gravarDadosArquivo(Conta conta) throws BancoException, IOException;
	public Conta carregarDadosArquivo() throws FileNotFoundException, IOException;
	public void gravarExtrato(float valor, LocalDateTime dataTransacao, Operacao tipoOperacao);
}

package interfaces;

import objects.Conta;
import objects.Cliente;

public interface UtilsBanco {
	
	public void apresentarBanco();

	public void carregarConta();

	public void criarUsuario();

	public void criarConta(Cliente cliente);

	public void exibirOperacoes(Conta conta);
	
	public void repetirOperacoes(Conta conta);

	public void finalizarOperacoes(Conta conta);

}

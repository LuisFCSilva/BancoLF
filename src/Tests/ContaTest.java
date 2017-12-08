package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.BancoException;
import excecoes.SaldoInsuficienteException;
import excecoes.ValorInvalidoException;
import objects.Cliente;
import objects.Conta;
import objects.ContaCorrente;

public class ContaTest {

	private Conta conta = null;
	private Cliente cliente = null;

	@Before
	public void inicializar() {
		cliente = new Cliente("Teste");
		conta = new ContaCorrente(cliente);
		conta.setSaldo(50f);
		conta.setStatus(true);
	}

	@Test
	public void sacar20Reais() throws Exception {
		conta.sacar(20f);

		assertEquals(30f, conta.getSaldo(), 0.1);
		assertTrue(conta.getSaldo() == 30f);
	}

	@Test(expected = ValorInvalidoException.class)
	public void sacarValorNegativo() throws Exception {
		conta.sacar(-10f);
	}

	@Test(expected = SaldoInsuficienteException.class)
	public void sacarValorMaiorQueSaldo() throws Exception {
		conta.sacar(100f);
	}

	@Test
	public void depositar50Reais() throws Exception {
		conta.depositar(50f);

		assertEquals(100f, conta.getSaldo(), 0.1);
	}

	@Test(expected = ValorInvalidoException.class)
	public void depositarValorNegativo() throws Exception {
		conta.depositar(-50);
	}

	@Test
	public void pagarMensalidade50Reais() throws Exception {
		conta.setMensalidade(50f);
		conta.pagarMensalidade();
		assertEquals(0f, conta.getSaldo(), 0.1);
	}

	@Test(expected = ValorInvalidoException.class)
	public void pagarMensalidadeValorNegativo() throws Exception {
		conta.setMensalidade(-50f);
		conta.pagarMensalidade();
	}

	@Test(expected = SaldoInsuficienteException.class)
	public void pagarMensalidadeSemSaldo() throws Exception {
		conta.setMensalidade(100f);
		conta.pagarMensalidade();
	}

	@Test
	public void inativarContaAtivaSaldoZero() throws Exception {
		conta.setSaldo(0f);
		conta.inativarConta();
		assertTrue(conta.isStatus() == false);
	}

	@Test(expected = BancoException.class)
	public void inativarContaComSaldo() throws Exception {
		conta.inativarConta();
	}
	
	@Test
	public void testarConversaoDeStatus() {
		assertEquals("Ativo", conta.converterStatus(true));
	}

}

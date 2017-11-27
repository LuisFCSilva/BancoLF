package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import excecoes.ContaInativaException;
import excecoes.SaldoInsuficienteException;
import excecoes.ValorInvalidoException;
import objects.Conta;

public class BancoLFTest {

	private Conta conta = null;

	@Before
	public void inicializar() {
		conta = new Conta();
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

	@Test(expected=ContaInativaException.class)
	public void sacarContaInativa() throws Exception {
		conta.setStatus(false);
		conta.sacar(10f);
	}
	
	@Test(expected=SaldoInsuficienteException.class)
	public void sacarValorMaiorQueSaldo() throws Exception {
		conta.sacar(100f);
	}
	
	@Test
	public void depositar50Reais() throws Exception {
		conta.depositar(50f);
		
		assertEquals(100f, conta.getSaldo(), 0.1);
	}
	
	@Test (expected=ValorInvalidoException.class)
	public void depositarValorNegativo() throws Exception {
		conta.depositar(-50);
	}
	
	@Test (expected=ContaInativaException.class)
	public void depositarContaInativa() throws Exception{
		conta.setStatus(false);
		conta.depositar(10f);
	}

}

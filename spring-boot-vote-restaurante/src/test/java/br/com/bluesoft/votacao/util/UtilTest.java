package br.com.bluesoft.votacao.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.bluesoft.votacao.exception.UtilException;

public class UtilTest {

	@Test
	public void deveValidarQuantidadeTotalMaximaDeRestaurantes() {		
		int numeroDeSorteios = Util.quantidadeDeVotacao(5, 2);		
		assertEquals(numeroDeSorteios, 10);		
	}
	
	@Test
	public void deveValidarQuantidadeTotalMinimaDeRestaurantes() {		
		int numeroDeSorteios = Util.quantidadeDeVotacao(2, 2);		
		assertEquals(numeroDeSorteios, 1);		
	}
	
	@Test(expected=UtilException.class)
	public void naoDeveValidarQuantidadeTotalRestaurantesMenorQueDois() {		
		int numeroDeSorteios = Util.quantidadeDeVotacao(1, 2);		
		assertEquals(numeroDeSorteios, 1);		
	}
	
	@Test(expected=UtilException.class)
	public void naoDeveValidarQuantidadeTotalDeRestaurantesMaiorQueCinco() {		
		int numeroDeSorteios = Util.quantidadeDeVotacao(6, 2);	
		System.out.println(numeroDeSorteios);
		assertEquals(numeroDeSorteios, 15);		
	}
	
	@Test(expected=UtilException.class)
	public void naoDeveValidarQuantidadeMaximaDeRestaurantesPorVotacao() {		
		int numeroDeSorteios = Util.quantidadeDeVotacao(5, 3);		
		assertEquals(numeroDeSorteios, 1);		
	}
}

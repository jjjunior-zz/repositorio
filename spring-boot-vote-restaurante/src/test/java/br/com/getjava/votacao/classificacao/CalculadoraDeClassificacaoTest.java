package br.com.getjava.votacao.classificacao;

import org.junit.Assert;
import org.junit.Test;

import br.com.getjava.votacao.classificacao.CalculadoraDeClassificacao;
import br.com.getjava.votacao.classificacao.ClassificaGanhador;
import br.com.getjava.votacao.classificacao.ClassificaPerdedor;
import br.com.getjava.votacao.classificacao.Classificacao;
import br.com.getjava.votacao.domain.ClassificacaoRestaurante;
import br.com.getjava.votacao.domain.DiferencaClassificacao;
import br.com.getjava.votacao.domain.Restaurante;
import br.com.getjava.votacao.enumeration.RestauranteEnum;

public class CalculadoraDeClassificacaoTest {

	@Test
	public void deveCalcularClassificacaoParaRestauranteGanhadorQuandoClassificacaoDeAmbosForemIguais() {

		DiferencaClassificacao dc = DiferencaClassificacao.newIntance(0, 3, 50, 50);

		Restaurante r = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(), RestauranteEnum.MCDONALDS.getPathImagem());

		ClassificacaoRestaurante cr = ClassificacaoRestaurante.newInstance(r, 0);

		CalculadoraDeClassificacao calculadora = CalculadoraDeClassificacao.newIntance();

		Classificacao c = ClassificaGanhador.newIntance();

		Integer classificacao = calculadora.realizarCalculo(dc, cr, c);

		Assert.assertEquals(5, classificacao.intValue());
	}

	@Test
	public void deveCalcularClassificacaoParaRestaurantePerdedorQuandoClassificacaoDeAmbosForemIguais() {

		DiferencaClassificacao dc = DiferencaClassificacao.newIntance(0, 3, 50, 50);

		Restaurante r = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(), RestauranteEnum.MCDONALDS.getPathImagem());

		ClassificacaoRestaurante cr = ClassificacaoRestaurante.newInstance(r, 0);

		CalculadoraDeClassificacao calculadora = CalculadoraDeClassificacao.newIntance();

		Classificacao c = ClassificaPerdedor.newIntance();

		Integer classificacao = calculadora.realizarCalculo(dc, cr, c);

		Assert.assertEquals(-5, classificacao.intValue());
	}

	@Test
	public void deveCalcularClassificacaoParaRestauranteGanhador() {

		// Diferenca entre ganhador e perdedor é 100 por isso este registro entre 99 e 106
		DiferencaClassificacao dc = DiferencaClassificacao.newIntance(99, 106, 64, 36);

		Restaurante r = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(), RestauranteEnum.MCDONALDS.getPathImagem());

		ClassificacaoRestaurante cr = ClassificacaoRestaurante.newInstance(r, 100);

		CalculadoraDeClassificacao calculadora = CalculadoraDeClassificacao.newIntance();

		Classificacao c = ClassificaGanhador.newIntance();

		Integer classificacao = calculadora.realizarCalculo(dc, cr, c);

		Assert.assertEquals(103, classificacao.intValue());
	}

	@Test
	public void deveCalcularClassificacaoParaRestaurantePerdedor() {

		// Diferenca entre ganhador e perdedor é 100 por isso este registro entre 99 e 106
		DiferencaClassificacao dc = DiferencaClassificacao.newIntance(99, 106, 64, 36);

		Restaurante r = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(), RestauranteEnum.MCDONALDS.getPathImagem());

		// pontuacao anterior 100
		ClassificacaoRestaurante cr = ClassificacaoRestaurante.newInstance(r, 95);

		CalculadoraDeClassificacao calculadora = CalculadoraDeClassificacao.newIntance();

		Classificacao c = ClassificaPerdedor.newIntance();

		Integer classificacao = calculadora.realizarCalculo(dc, cr, c);

		Assert.assertEquals(91, classificacao.intValue());
	}
}

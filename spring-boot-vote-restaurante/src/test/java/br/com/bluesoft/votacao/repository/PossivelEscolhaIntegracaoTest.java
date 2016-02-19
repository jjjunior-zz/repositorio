package br.com.bluesoft.votacao.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-data-test.xml" })
@Transactional
@Rollback(true)
public class PossivelEscolhaIntegracaoTest {

	@Autowired
	private PossivelEscolhaRepository	possivelEscolhaRepository;

	@Autowired
	private RestauranteRepository		restauranteRepository;

	@Test
	public void deveBuscarMenorEscolha() {
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(), RestauranteEnum.MCDONALDS.getPathImagem());
		Restaurante r1 = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome(), RestauranteEnum.BURGER_KING.getPathImagem());

		this.restauranteRepository.saveAndFlush(r0);
		this.restauranteRepository.saveAndFlush(r1);

		PossivelEscolha possivelEscolha = PossivelEscolha.newInstance();
		possivelEscolha.setRestauranteLadoEsquerdo(r0);
		possivelEscolha.setRestauranteLadoDireito(r1);
		possivelEscolhaRepository.saveAndFlush(possivelEscolha);

		Integer id = this.possivelEscolhaRepository.buscarMenorEscolha();

		assertEquals(1, id.intValue());
	}

	@Test
	public void deveBuscarRestaurantesNaoSelecionados() {

		Restaurante ra = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(), RestauranteEnum.MCDONALDS.getPathImagem());
		Restaurante rb = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome(), RestauranteEnum.BURGER_KING.getPathImagem());
		Restaurante rc = Restaurante.newInstance(RestauranteEnum.KFC.getNome(), RestauranteEnum.KFC.getPathImagem());
		Restaurante rd = Restaurante.newInstance(RestauranteEnum.OUTBACK.getNome(), RestauranteEnum.OUTBACK.getPathImagem());
		Restaurante re = Restaurante.newInstance(RestauranteEnum.SUBWAY.getNome(), RestauranteEnum.SUBWAY.getPathImagem());

		restauranteRepository.saveAndFlush(ra);
		restauranteRepository.saveAndFlush(rb);
		restauranteRepository.saveAndFlush(rc);
		restauranteRepository.saveAndFlush(rd);
		restauranteRepository.saveAndFlush(re);

		PossivelEscolha p1 = PossivelEscolha.newInstance(rb, ra);
		PossivelEscolha p2 = PossivelEscolha.newInstance(rc, ra);
		PossivelEscolha p3 = PossivelEscolha.newInstance(rd, ra);
		PossivelEscolha p4 = PossivelEscolha.newInstance(re, ra);
		PossivelEscolha p5 = PossivelEscolha.newInstance(rc, rb);
		PossivelEscolha p6 = PossivelEscolha.newInstance(rd, rb);
		PossivelEscolha p7 = PossivelEscolha.newInstance(re, rb);
		PossivelEscolha p8 = PossivelEscolha.newInstance(rd, rc);
		PossivelEscolha p9 = PossivelEscolha.newInstance(re, rc);
		PossivelEscolha p10 = PossivelEscolha.newInstance(re, rd);

		possivelEscolhaRepository.saveAndFlush(p1);
		possivelEscolhaRepository.saveAndFlush(p2);
		possivelEscolhaRepository.saveAndFlush(p3);
		possivelEscolhaRepository.saveAndFlush(p4);
		possivelEscolhaRepository.saveAndFlush(p5);
		possivelEscolhaRepository.saveAndFlush(p6);
		possivelEscolhaRepository.saveAndFlush(p7);
		possivelEscolhaRepository.saveAndFlush(p8);
		possivelEscolhaRepository.saveAndFlush(p9);
		possivelEscolhaRepository.saveAndFlush(p10);

		List<Restaurante> restaurantesEsquerdo = new ArrayList<>();
		List<Restaurante> restaurantesDireito = new ArrayList<>();

		restaurantesEsquerdo.add(ra);
		restaurantesDireito.add(ra);

		List<PossivelEscolha> possiveisEscolhasSemMcDonalds = possivelEscolhaRepository.buscarRestaurantesNaoVotados(restaurantesEsquerdo, restaurantesDireito);

		assertEquals(6, possiveisEscolhasSemMcDonalds.size());
	}
}

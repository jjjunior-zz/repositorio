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
		
		List<Restaurante> restaurantes = new ArrayList<>();
		
		restaurantes.add(ra);
		restaurantes.add(rb);
		restaurantes.add(rc);
		restaurantes.add(rd);
		restaurantes.add(re);		

		List<PossivelEscolha> possiveisEscolhas = new ArrayList<>();
		
		possiveisEscolhas.add(PossivelEscolha.newInstance(rb, ra));
		possiveisEscolhas.add(PossivelEscolha.newInstance(rc, ra));
		possiveisEscolhas.add(PossivelEscolha.newInstance(rd, ra));
		possiveisEscolhas.add(PossivelEscolha.newInstance(re, ra));
		possiveisEscolhas.add(PossivelEscolha.newInstance(rc, rb));
		possiveisEscolhas.add(PossivelEscolha.newInstance(rd, rb));
		possiveisEscolhas.add(PossivelEscolha.newInstance(re, rb));
		possiveisEscolhas.add(PossivelEscolha.newInstance(rd, rc));
		possiveisEscolhas.add(PossivelEscolha.newInstance(re, rc));
		possiveisEscolhas.add(PossivelEscolha.newInstance(re, rd));
		
		restaurantes.forEach(restaurante -> restauranteRepository.saveAndFlush(restaurante));
		possiveisEscolhas.forEach(possivelEscolha -> possivelEscolhaRepository.saveAndFlush(possivelEscolha));
		
		List<Restaurante> restaurantesEsquerdo = new ArrayList<>();
		List<Restaurante> restaurantesDireito = new ArrayList<>();

		restaurantesEsquerdo.add(ra);
		restaurantesDireito.add(ra);

		List<PossivelEscolha> possiveisEscolhasSemMcDonalds = possivelEscolhaRepository.buscarRestaurantesNaoVotados(restaurantesEsquerdo, restaurantesDireito);

		assertEquals(6, possiveisEscolhasSemMcDonalds.size());
	}
}

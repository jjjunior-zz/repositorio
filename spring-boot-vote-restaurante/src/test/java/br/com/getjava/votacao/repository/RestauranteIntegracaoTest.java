package br.com.getjava.votacao.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.getjava.votacao.domain.Restaurante;
import br.com.getjava.votacao.enumeration.RestauranteEnum;
import br.com.getjava.votacao.repository.RestauranteRepository;

@ContextConfiguration(locations = { "classpath:spring-data-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(true)
public class RestauranteIntegracaoTest {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Test
	public void deveIncluirUmRestaurante() {
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(), RestauranteEnum.MCDONALDS.getPathImagem());

		this.restauranteRepository.saveAndFlush(r0);

		List<Restaurante> lista = this.restauranteRepository.findAll();

		assertEquals(1, lista.size());
		assertEquals(RestauranteEnum.MCDONALDS.getNome(), lista.get(0).getNome());
	}

	@Test
	public void deveBuscarTodosRestaurantes() {

		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.KFC.getNome(), RestauranteEnum.KFC.getPathImagem());
		Restaurante r1 = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome(), RestauranteEnum.BURGER_KING.getPathImagem());

		this.restauranteRepository.saveAndFlush(r0);
		this.restauranteRepository.saveAndFlush(r1);

		List<Restaurante> lista = this.restauranteRepository.findAll();

		assertEquals(2, lista.size());
		assertEquals(RestauranteEnum.KFC.getNome(), lista.get(0).getNome());
		assertEquals(RestauranteEnum.BURGER_KING.getNome(), lista.get(1).getNome());
	}

	@Test
	public void deveExcluirUmRestaurante() {

		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.OUTBACK.getNome(), RestauranteEnum.OUTBACK.getPathImagem());

		this.restauranteRepository.saveAndFlush(r0);
		List<Restaurante> lista = this.restauranteRepository.findByNome(RestauranteEnum.OUTBACK.getNome());

		Restaurante r1 = lista.get(0);

		this.restauranteRepository.delete(r1);

		List<Restaurante> lista1 = this.restauranteRepository.findByNome(RestauranteEnum.OUTBACK.getNome());

		assertEquals(0, lista1.size());
	}
}

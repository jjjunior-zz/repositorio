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

import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-data-test.xml" })
@Transactional
@Rollback(true)
public class ClassificacaoRestauranteIntegracaoTest {

	@Autowired
	private ClassificacaoRestauranteRepository	classificacaoRestauranteRepository;

	@Autowired
	private RestauranteRepository				restauranteRepository;

	@Test
	public void deveBuscarMenorClassificacaoDeRestaurantes() {
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(), RestauranteEnum.MCDONALDS.getPathImagem());
		Restaurante r1 = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome(), RestauranteEnum.BURGER_KING.getPathImagem());
		List<Restaurante> restaurantes = new ArrayList<>();
		restaurantes.add(r0);
		restaurantes.add(r1);
		restaurantes.forEach(r -> this.restauranteRepository.saveAndFlush(r));

		List<ClassificacaoRestaurante> lista = new ArrayList<>();
		lista.add(ClassificacaoRestaurante.newInstance(r0, 0));
		lista.add(ClassificacaoRestaurante.newInstance(r1, 0));
		lista.forEach(c -> classificacaoRestauranteRepository.saveAndFlush(c));

		List<ClassificacaoRestaurante> c1 = classificacaoRestauranteRepository.findByRestauranteIn(restaurantes);

		assertEquals(2, c1.size());
	}
}

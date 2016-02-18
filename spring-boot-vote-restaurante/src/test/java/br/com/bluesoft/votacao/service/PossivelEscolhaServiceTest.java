package br.com.bluesoft.votacao.service;

import static org.junit.Assert.assertEquals;

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
import br.com.bluesoft.votacao.repository.PossivelEscolhaRepository;
import br.com.bluesoft.votacao.repository.RestauranteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-data-test.xml"})
@Transactional @Rollback(true)
public class PossivelEscolhaServiceTest {
	
	@Autowired
	private PossivelEscolhaRepository possivelEscolhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	
	@Test
	public void deveBuscarMenorEscolha() {		
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(),RestauranteEnum.MCDONALDS.getPathImagem());
		Restaurante r1 = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome(),RestauranteEnum.BURGER_KING.getPathImagem());
		
		this.restauranteRepository.saveAndFlush(r0);		
		this.restauranteRepository.saveAndFlush(r1);		
		
		PossivelEscolha possivelEscolha = PossivelEscolha.newInstance();
		possivelEscolha.setRestauranteLadoEsquerdo(r0);
		possivelEscolha.setRestauranteLadoDireito(r1);		
		possivelEscolhaRepository.saveAndFlush(possivelEscolha);
		
		Integer id = this.possivelEscolhaRepository.buscarMenorEscolha();
		
		assertEquals(1,id.intValue());		
	}	
}

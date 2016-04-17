package br.com.getava.votacao.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.getjava.votacao.domain.PossivelEscolha;
import br.com.getjava.votacao.domain.Restaurante;
import br.com.getjava.votacao.enumeration.RestauranteEnum;
import br.com.getjava.votacao.exception.ModeloException;
import br.com.getjava.votacao.repository.PossivelEscolhaRepository;
import br.com.getjava.votacao.repository.RestauranteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml","classpath:applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PossivelEscolhaRepositoryTest {
	
	@Autowired
	private PossivelEscolhaRepository possivelEscolhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	
	@Test
	public void deveBuscarMenorEscolha() throws ModeloException{		
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(),RestauranteEnum.MCDONALDS.getPathImagem());
		Restaurante r1 = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome(),RestauranteEnum.BURGER_KING.getPathImagem());
		
		this.restauranteRepository.incluirRestautante(r0);		
		this.restauranteRepository.incluirRestautante(r1);		
		
		PossivelEscolha possivelEscolha = PossivelEscolha.newInstance();
		possivelEscolha.setRestauranteLadoEsquerdo(r0);
		possivelEscolha.setRestauranteLadoDireito(r1);		
		possivelEscolhaRepository.incluirPossivelEscolha(possivelEscolha);
		
		Integer id = this.possivelEscolhaRepository.buscarMenorEscolha();
		
		assertEquals(20,id.intValue());		
	}	
}

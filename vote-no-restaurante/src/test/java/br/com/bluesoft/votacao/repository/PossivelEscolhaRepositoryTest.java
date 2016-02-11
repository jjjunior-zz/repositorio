package br.com.bluesoft.votacao.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;
import br.com.bluesoft.votacao.exception.ModeloException;

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
	public void deveIncluirUmaPossivelEscolha() throws ModeloException{		
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome());
		Restaurante r1 = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome());
		
		this.restauranteRepository.incluirRestautante(r0);		
		this.restauranteRepository.incluirRestautante(r1);		
		
		PossivelEscolha possivelEscolha = PossivelEscolha.newInstance();
		possivelEscolha.setRestauranteLadoEsquerdo(r0);
		possivelEscolha.setRestauranteLadoDireito(r1);		
		possivelEscolhaRepository.incluirPossivelEscolha(possivelEscolha);
		
		List<PossivelEscolha> escolhas = this.possivelEscolhaRepository.buscarPossiveisEscolhasPorRestaurante(r0, r1);
		
		assertEquals(1, escolhas.size());		
	}	
}

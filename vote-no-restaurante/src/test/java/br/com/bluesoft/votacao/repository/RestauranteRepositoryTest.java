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


import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;
import br.com.bluesoft.votacao.exception.ModeloException;
import br.com.bluesoft.votacao.repository.RestauranteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml","classpath:applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RestauranteRepositoryTest {
	
	@Autowired
	private RestauranteRepository restauranteRepository;	
	
	@Test
	public void deveIncluirUmRestaurante() throws ModeloException{		
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome());
		
		this.restauranteRepository.incluirRestautante(r0);		
		
		List<Restaurante> lista =  this.restauranteRepository.buscarTodosRestaurantes();		
		
		assertEquals(1, lista.size());		
		assertEquals(RestauranteEnum.MCDONALDS.getNome(), lista.get(0).getNome());
	}
	
	@Test
	public void deveBuscarTodosRestaurantes() throws ModeloException{
	
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome());
		Restaurante r1 = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome());
		
		this.restauranteRepository.incluirRestautante(r0);
		this.restauranteRepository.incluirRestautante(r1);
	
		List<Restaurante> lista =  this.restauranteRepository.buscarTodosRestaurantes();		

		assertEquals(2, lista.size());		
		assertEquals(RestauranteEnum.MCDONALDS.getNome(), lista.get(0).getNome());
		assertEquals(RestauranteEnum.BURGER_KING.getNome(), lista.get(1).getNome());
	}
	
	@Test
	public void deveExcluirUmRestaurante() throws ModeloException{		
	
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome());
		
		this.restauranteRepository.incluirRestautante(r0);		
		List<Restaurante> lista =  this.restauranteRepository.buscarRestaurantePorNome(RestauranteEnum.MCDONALDS.getNome());
		
		Restaurante r1 = lista.get(0);
		
		this.restauranteRepository.excluirRestautante(r1);
	
		List<Restaurante> lista1 =  this.restauranteRepository.buscarRestaurantePorNome(RestauranteEnum.MCDONALDS.getNome());		

		assertEquals(0, lista1.size());		
	}
}

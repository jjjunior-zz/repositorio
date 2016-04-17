package br.com.getava.votacao.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.getjava.votacao.domain.Restaurante;
import br.com.getjava.votacao.enumeration.RestauranteEnum;
import br.com.getjava.votacao.exception.ModeloException;
import br.com.getjava.votacao.repository.RestauranteRepository;

@TransactionConfiguration(defaultRollback = true)
@Transactional
@ContextConfiguration(locations = {"classpath:test-context.xml","classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RestauranteRepositoryTest {
	
	@Autowired
	private RestauranteRepository restauranteRepository;	
	
	@Test
	public void deveIncluirUmRestaurante() throws ModeloException{		
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(),RestauranteEnum.MCDONALDS.getPathImagem());		
		
		this.restauranteRepository.incluirRestautante(r0);		
		
		List<Restaurante> lista =  this.restauranteRepository.buscarTodosRestaurantes();		
		
		assertEquals(1, lista.size());		
		assertEquals(RestauranteEnum.MCDONALDS.getNome(), lista.get(0).getNome());
	}
	
	@Test
	public void deveBuscarTodosRestaurantes() throws ModeloException{
	
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(),RestauranteEnum.MCDONALDS.getPathImagem());
		Restaurante r1 = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome(),RestauranteEnum.BURGER_KING.getPathImagem());		
		
		this.restauranteRepository.incluirRestautante(r0);
		this.restauranteRepository.incluirRestautante(r1);
	
		List<Restaurante> lista =  this.restauranteRepository.buscarTodosRestaurantes();		

		assertEquals(2, lista.size());		
		assertEquals(RestauranteEnum.MCDONALDS.getNome(), lista.get(0).getNome());
		assertEquals(RestauranteEnum.BURGER_KING.getNome(), lista.get(1).getNome());
	}
	
	@Test
	public void deveExcluirUmRestaurante() throws ModeloException{		
	
		Restaurante r0 = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(),RestauranteEnum.MCDONALDS.getPathImagem());		
		
		this.restauranteRepository.incluirRestautante(r0);		
		List<Restaurante> lista =  this.restauranteRepository.buscarRestaurantePorNome(RestauranteEnum.MCDONALDS.getNome());
				
		Restaurante r1 = lista.get(0);
		
		this.restauranteRepository.excluirRestautante(r1);
	
		List<Restaurante> lista1 =  this.restauranteRepository.buscarRestaurantePorNome(RestauranteEnum.MCDONALDS.getNome());		

		assertEquals(0, lista1.size());		
	}
}

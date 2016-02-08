package br.com.bluesoft.votacao.repository.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.dao.ModeloException;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.repository.RestauranteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml","classpath:applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RestauranteRepositoryTest {
	
	@Autowired
	private RestauranteRepository restauranteRepository;	
	
	@Test
	public void deveIncluirDoisRestaurantes() throws ModeloException{		
		Restaurante r0 = Restaurante.newInstance("McDonalds");		
		Restaurante r1 = Restaurante.newInstance("Bobs");
		
		this.restauranteRepository.incluirRestautante(r0);
		this.restauranteRepository.incluirRestautante(r1);
		
		List<Restaurante> lista =  this.restauranteRepository.buscarTodosRestaurantes();		
		
		assertEquals(2, lista.size());		
	}
	
	@Test
	public void devebuscarTodosRestaurantes() throws ModeloException{
		//criando cenario
		Restaurante r0 = Restaurante.newInstance("McDonalds");		
		this.restauranteRepository.incluirRestautante(r0);
		
		//testando
		List<Restaurante> lista =  this.restauranteRepository.buscarTodosRestaurantes();
		
		//verificando
		assertEquals(1, lista.size());		
	}	
}

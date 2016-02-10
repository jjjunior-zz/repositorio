package br.com.bluesoft.votacao.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.PossivelEscollha;
import br.com.bluesoft.votacao.exception.ModeloException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml","classpath:applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PossivelEscolhaRepositoryTest {
	
	@Autowired
	private PossivelEscolhaRepository possivelEscolhaRepository;		
	
	@Test
	public void deveBuscarTodasPossiveisEscolhas() throws ModeloException{
		
		PossivelEscollha p = PossivelEscollha.newInstance();
		p.setRestauranteLadoDireito("A");
		p.setRestauranteLadoEsquerdo("B");
		
		possivelEscolhaRepository.incluirPossivelEscolha(p);		
		List<PossivelEscollha> escolhas = this.possivelEscolhaRepository.buscarTodasPossiveisEscolhas();
		
		
		Assert.assertEquals(11, escolhas.size());
	}		
}

package br.com.getjava.cloudws.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.getjava.cloudws.domain.Instancia;
import br.com.getjava.cloudws.domain.Template;
import br.com.getjava.cloudws.enumeration.Bits;
import br.com.getjava.cloudws.enumeration.SistemaOperacional;
import br.com.getjava.cloudws.enumeration.TipoMaquina;

public class InstanciaRepositoryTest {

	
	private InstanciaRepository instanciaRepository;
	
	@Before
	public void antes() {
		instanciaRepository = InstanciaRepository.newInstance(JpaConnection.getInstance("cloudwstest"));		
	}

	@After
	public void depois() {
		instanciaRepository.rollback();
		instanciaRepository.getEntityManager().close();
	}

	@Test
	public void deveEcontrarInstanciaInserida() {		
		Template template = Template.newInstance("teste", "test2", SistemaOperacional.LINUX, Bits.bits64);
		Instancia instancia = Instancia.newInstance(2, 2, 2, TipoMaquina.MICRO, template);
		instanciaRepository.begin();
		instanciaRepository.persist(instancia);
		instanciaRepository.flush();		

		List<Instancia> instancias = instanciaRepository.findAll();
		Instancia intancia1 = instancias.get(0);
		
		assertEquals(instancia.getId(), intancia1.getId());
		assertEquals(template.getId(), instancia.getTemplate().getId());
	}

}

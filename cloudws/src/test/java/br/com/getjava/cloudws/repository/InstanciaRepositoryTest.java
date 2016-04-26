package br.com.getjava.cloudws.repository;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.getjava.cloudws.domain.Instancia;
import br.com.getjava.cloudws.domain.Template;
import br.com.getjava.cloudws.domain.Usuario;
import br.com.getjava.cloudws.enumeration.Bits;
import br.com.getjava.cloudws.enumeration.SistemaOperacional;
import br.com.getjava.cloudws.enumeration.TipoMaquina;
import br.com.getjava.cloudws.enumeration.TipoUsuario;

public class InstanciaRepositoryTest {
	
	private InstanciaRepository instanciaRepository;
	private UsuarioRepository usuarioRepository;
	
	@Before
	public void antes() {
		instanciaRepository = InstanciaRepository.newInstance(JpaConnection.getInstance("cloudwstest"));		
		usuarioRepository = UsuarioRepository.newInstance(JpaConnection.getInstance("cloudwstest"));		
	}
	
	@After
	public void depois() {
		instanciaRepository.getEntityManager().close();
		usuarioRepository.getEntityManager().close();
	}	

	@Test
	public void deveEcontrarInstanciaPorNome() {	
		Usuario usuario = Usuario.newInstance("jjjunior@gmail.com", "123456", TipoUsuario.ROOT);
		usuarioRepository.incluirUsuario(usuario);
		usuarioRepository.commit();
		
		Usuario usuario1 = usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());		
		
		Template template = Template.newInstance("teste", "test2", SistemaOperacional.LINUX, Bits.bits64);
		Instancia instancia = Instancia.newInstance("teste",2,2, 2, TipoMaquina.MICRO, template,usuario1);
		
		instanciaRepository.incluirInstancia(instancia);
		instanciaRepository.commit();
		
		Instancia instancia1 = instanciaRepository.buscarIntanciaPorNome("teste");		
				
		assertEquals(instancia.getId(), instancia1.getId());
		assertEquals(instancia.getNome(), instancia1.getNome());
		assertEquals(template.getId(), instancia1.getTemplate().getId());
		
		
	}

}

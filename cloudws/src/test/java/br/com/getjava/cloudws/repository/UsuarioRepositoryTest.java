package br.com.getjava.cloudws.repository;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.getjava.cloudws.domain.Usuario;
import br.com.getjava.cloudws.enumeration.TipoUsuario;

public class UsuarioRepositoryTest {	
	
	private UsuarioRepository usuarioRepository;
	
	@Before
	public void antes() {				
		usuarioRepository = UsuarioRepository.newInstance(JpaConnection.getInstance("cloudwstest"));		
	}

	@After
	public void depois() {		
		usuarioRepository.rollback();
		usuarioRepository.getEntityManager().close();
	}

	@Test
	public void deveEcontrarUsuarioPorEmail() {	
		Usuario usuario = Usuario.newInstance("jjjunior@gmail.com", "123456", TipoUsuario.ROOT);
		usuarioRepository.incluirUsuario(usuario);	
		
		Usuario usuario1 = usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());		
				
		assertEquals(usuario.getId(), usuario1.getId());
		assertEquals(usuario.getEmail(), usuario1.getEmail());		
	}

}

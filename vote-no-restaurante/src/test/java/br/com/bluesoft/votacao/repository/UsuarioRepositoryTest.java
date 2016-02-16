package br.com.bluesoft.votacao.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.Usuario;
import br.com.bluesoft.votacao.exception.ModeloException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml","classpath:applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void deveIncluirUmUsuario() throws ModeloException{
		Usuario u0 = Usuario.newInstance("João", "joão@gmail.com");		
		this.usuarioRepository.incluirUsuario(u0);		
		
		List<Usuario> lista =  this.usuarioRepository.buscarTodosUsuarios();
		
		assertEquals(1, lista.size());		
		assertEquals("Joao", lista.get(0).getNome());
		assertEquals("joao@gmail.com", lista.get(0).getEmail());
	}	
	
	@Test
	public void deveBuscarTodosUsuarios() throws ModeloException{		
		
		Usuario usuario0 = Usuario.newInstance("Junior", "jjjunior@gmail.com");		
		Usuario usuario1 = Usuario.newInstance("Joao", "jjjunior1@gmail.com");		
		
		usuarioRepository.incluirUsuario(usuario0);
		usuarioRepository.incluirUsuario(usuario1);		
		
		List<Usuario> usuarios = this.usuarioRepository.buscarTodosUsuarios();
		Assert.assertEquals(2, usuarios.size());
	}
	
	@Test
	public void deveBuscarUsuarioPorEmail() throws ModeloException{		
		
		Usuario usuario = Usuario.newInstance("Junior", "jjjunior@gmail.com");		
		usuarioRepository.incluirUsuario(usuario);				
		
		List<Usuario> usuarios = this.usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());
		Assert.assertEquals(1, usuarios.size());
		Assert.assertEquals("jjjunior@gmail.com", usuario.getEmail());
		Assert.assertEquals("Junior", usuario.getNome());
	}
	
	@Test
	public void deveExcluirUsuario() throws ModeloException{		
		
		Usuario usuario = Usuario.newInstance("Junior", "jjjunior@gmail.com");		
		usuarioRepository.incluirUsuario(usuario);				
		
		List<Usuario> usuarios = this.usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());
		
		usuarioRepository.excluirUsuario(usuario);
		
		usuarios = this.usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());
		
		Assert.assertEquals(0, usuarios.size());
		
	}
	
	@Test
	public void deveAtualizarUsuario() throws ModeloException{		
		
		Usuario usuario = Usuario.newInstance("Junior", "jjjunior@gmail.com");		
		usuarioRepository.incluirUsuario(usuario);
		
		usuario.setEmail("jjotinha47@msn.com");
		
		usuarioRepository.atualizarUsuario(usuario);
		
		List<Usuario> usuarios = this.usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());
		
		Assert.assertEquals(1, usuarios.size());
		Assert.assertEquals("jjotinha47@msn.com", usuarios.get(0).getEmail());
		
	}	
}

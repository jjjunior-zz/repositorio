package br.com.bluesoft.votacao.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.Usuario;
import br.com.bluesoft.votacao.repository.UsuarioRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-data-test.xml"})
@Transactional @Rollback(true)
public class UsuarioServiceTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	@Test
	public void deveIncluirUmUsuario() {
		Usuario u0 = Usuario.newInstance("João", "joão@gmail.com");		
		this.usuarioRepository.saveAndFlush(u0);		
		
		List<Usuario> lista =  this.usuarioRepository.findAll();
		
		assertEquals(1, lista.size());		
		assertEquals("Joao", lista.get(0).getNome());
		assertEquals("joao@gmail.com", lista.get(0).getEmail());
	}	
	
	@Test
	public void deveBuscarTodosUsuarios(){		
		
		Usuario usuario0 = Usuario.newInstance("Junior", "jjjunior@gmail.com");		
		Usuario usuario1 = Usuario.newInstance("Joao", "jjjunior1@gmail.com");		
		
		usuarioRepository.saveAndFlush(usuario0);
		usuarioRepository.saveAndFlush(usuario1);		
		
		List<Usuario> usuarios = this.usuarioRepository.findAll();
		Assert.assertEquals(2, usuarios.size());
	}
	
	@Test
	public void deveBuscarUsuarioPorEmail() {		
		
		Usuario usuario = Usuario.newInstance("Junior", "jjjunior@gmail.com");		
		usuarioRepository.saveAndFlush(usuario);				
		
		List<Usuario> usuarios = this.usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());
		Assert.assertEquals(1, usuarios.size());
		Assert.assertEquals("jjjunior@gmail.com", usuario.getEmail());
		Assert.assertEquals("Junior", usuario.getNome());
	}
	
	@Test
	public void deveExcluirUsuario(){		
		
		Usuario usuario = Usuario.newInstance("Junior", "jjjunior@gmail.com");		
		usuarioRepository.saveAndFlush(usuario);				
		
		List<Usuario> usuarios = this.usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());
		
		usuarioRepository.saveAndFlush(usuario);
		
		usuarios = this.usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());
		
		Assert.assertEquals(1, usuarios.size());
		
	}
	
	@Test
	public void deveAtualizarUsuario() {		
		
		Usuario usuario = Usuario.newInstance("Junior", "jjjunior@gmail.com");		
		usuarioRepository.saveAndFlush(usuario);
		
		usuario.setEmail("jjotinha47@msn.com");
		
		usuarioRepository.saveAndFlush(usuario);
		
		List<Usuario> usuarios = this.usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());
		
		Assert.assertEquals(1, usuarios.size());
		Assert.assertEquals("jjotinha47@msn.com", usuarios.get(0).getEmail());
		
	}	
}

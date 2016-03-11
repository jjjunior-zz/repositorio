package br.com.bluesoft.votacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.Usuario;
import br.com.bluesoft.votacao.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscarUsuarioPorIndice(Integer indice) {
		return this.usuarioRepository.findOne(indice);
	}

	public List<Usuario> buscarUsuarioPorEmail(String email) {
		return this.usuarioRepository.findByEmail(email);
	}

	public List<Usuario> buscarTodosUsuarios() {
		return this.usuarioRepository.findAll();
	}

	@Transactional(readOnly = false)
	public void incluirUsuario(Usuario usuario) {
		this.usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = false)
	public void excluirUsuario(Usuario usuario) {
		this.usuarioRepository.delete(usuario);
	}

	@Transactional(readOnly = false)
	public void atualizarUsuario(Usuario usuario) {
		this.usuarioRepository.save(usuario);
	}
}

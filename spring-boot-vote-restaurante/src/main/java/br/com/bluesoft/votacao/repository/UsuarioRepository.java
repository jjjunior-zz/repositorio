package br.com.bluesoft.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesoft.votacao.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public List<Usuario> findByEmail(String email);

}

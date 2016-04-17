package br.com.getjava.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.getjava.votacao.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public List<Usuario> findByEmail(String email);

}

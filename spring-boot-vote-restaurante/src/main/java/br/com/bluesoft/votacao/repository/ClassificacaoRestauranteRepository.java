package br.com.bluesoft.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;

public interface ClassificacaoRestauranteRepository extends JpaRepository<ClassificacaoRestaurante, Integer> {

}

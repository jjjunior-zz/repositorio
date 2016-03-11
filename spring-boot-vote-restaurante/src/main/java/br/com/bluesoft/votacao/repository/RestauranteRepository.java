package br.com.bluesoft.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesoft.votacao.domain.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

	public List<Restaurante> findByNome(String nome);
}

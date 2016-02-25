package br.com.bluesoft.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bluesoft.votacao.domain.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

	@Query("select r from Restaurante r where r.nome = :nome")
	public List<Restaurante> buscarRestaurantePorNome(@Param("nome") String nome);
}

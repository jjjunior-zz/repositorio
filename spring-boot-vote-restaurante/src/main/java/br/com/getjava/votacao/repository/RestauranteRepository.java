package br.com.getjava.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.getjava.votacao.domain.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

	public List<Restaurante> findByNome(String nome);
}

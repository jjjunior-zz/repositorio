package br.com.bluesoft.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.Restaurante;

public interface ClassificacaoRestauranteRepository extends JpaRepository<ClassificacaoRestaurante, Integer> {
	
	public ClassificacaoRestaurante findByRestaurante(Restaurante restaurante);	

	public List<ClassificacaoRestaurante> findByRestauranteIn(List<Restaurante> restaurantes);
	
}

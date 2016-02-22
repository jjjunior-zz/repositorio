package br.com.bluesoft.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.Restaurante;

public interface ClassificacaoRestauranteRepository extends JpaRepository<ClassificacaoRestaurante, Integer> {
	
	@Query("select cr from ClassificacaoRestaurante cr where cr.restaurante = :restaurante")
	public ClassificacaoRestaurante buscarClassificacaoPorRestaurante(@Param("restaurante") Restaurante restaurante);
	
	@Query("select r from ClassificacaoRestaurante r where r.restaurante in :restaurantes")
	public List<ClassificacaoRestaurante> buscarClassificacaoPorRestaurantes(@Param("restaurantes") List<Restaurante> restaurantes);
	
}

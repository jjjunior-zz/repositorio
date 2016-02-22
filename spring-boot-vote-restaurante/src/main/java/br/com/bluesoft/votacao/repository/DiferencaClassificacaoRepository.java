package br.com.bluesoft.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bluesoft.votacao.domain.DiferencaClassificacao;

public interface DiferencaClassificacaoRepository extends JpaRepository<DiferencaClassificacao, Integer> {
	
	
	@Query("select d from DiferencaClassificacao d where d.diferencaInicial >= :valor and d.diferencaFinal <= :valor " )
	public DiferencaClassificacao buscarPorDiferencaInicialEFinal(@Param("valor") Integer valor);

}
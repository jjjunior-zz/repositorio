package br.com.bluesoft.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bluesoft.votacao.domain.PossivelEscolha;

public interface PossivelEscolhaRepository extends JpaRepository<PossivelEscolha, Integer>  {

	@Query("select min(p.id) from PossivelEscolha p")
	public Integer buscarMenorEscolha();
	
}

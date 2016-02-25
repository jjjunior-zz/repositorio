package br.com.bluesoft.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.domain.Restaurante;

public interface PossivelEscolhaRepository extends JpaRepository<PossivelEscolha, Integer> {

	@Query("select min(p.id) from PossivelEscolha p")
	public Integer buscarMenorEscolha();

	@Query("select p from PossivelEscolha p where p.restauranteLadoEsquerdo not in :restaurantesEsquerdo and p.restauranteLadoDireito not in :restaurantesDireito")
	public List<PossivelEscolha> buscarRestaurantesNaoVotados(@Param("restaurantesEsquerdo") List<Restaurante> restauranteEsquerdo,
			@Param("restaurantesDireito") List<Restaurante> restaurantesDireito);
}

package br.com.bluesoft.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.domain.Restaurante;

public interface PossivelEscolhaRepository extends JpaRepository<PossivelEscolha, Integer> {

	@Query("select min(p.id) from PossivelEscolha p")
	public Integer buscarMenorEscolha();

	public List<PossivelEscolha> findByRestauranteLadoEsquerdoNotInAndRestauranteLadoDireitoNotIn(List<Restaurante> restauranteEsquerdo,List<Restaurante> restauranteDireito);
}

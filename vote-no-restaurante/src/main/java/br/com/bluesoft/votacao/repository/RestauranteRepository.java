package br.com.bluesoft.votacao.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.bluesoft.votacao.domain.Restaurante;

@Repository
public class RestauranteRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<Restaurante> listaAll(){
		return null;
	}

}

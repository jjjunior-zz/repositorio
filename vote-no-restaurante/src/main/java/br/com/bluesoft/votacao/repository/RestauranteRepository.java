package br.com.bluesoft.votacao.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.dao.DAO;
import br.com.bluesoft.votacao.dao.ParametroDaConsulta;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.exception.ModeloException;

@Repository
public class RestauranteRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private DAO<Restaurante, Integer> daoRestaurante;	
	
	@PostConstruct
	public void init(){
		this.daoRestaurante = DAO.newInstance(this.entityManager, Restaurante.class);
	}
	
	public Restaurante buscarRestaurantePorIndice(int indice){		
		return this.daoRestaurante.selecionarPeloIndice(indice);
	}
	
	public List<Restaurante> buscarTodosRestaurantes(){		
		return this.daoRestaurante.selecionar(Restaurante.TUDO);
	}
	
	public List<Restaurante> buscarRestaurantePorNome(String nome){		
		return this.daoRestaurante.selecionarComParametro(Restaurante.SELECIONAR_POR_NOME,ParametroDaConsulta.com("nome", nome).parametros());
	}
	
	@Transactional
	public void incluirRestautante(Restaurante restaurante) throws ModeloException{ 		
		this.daoRestaurante.inserir(restaurante);
	}
	
	@Transactional
	public void excluirRestautante(Restaurante restaurante) throws ModeloException{ 		
		this.daoRestaurante.deletar(restaurante);		
	}
}

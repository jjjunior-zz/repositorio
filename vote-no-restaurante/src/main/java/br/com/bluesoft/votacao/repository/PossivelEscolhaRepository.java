package br.com.bluesoft.votacao.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.dao.DAO;
import br.com.bluesoft.votacao.dao.ParametroDaConsulta;
import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.exception.ModeloException;

@Repository
public class PossivelEscolhaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private DAO<PossivelEscolha, Integer> daoEscolha;	
	
	@PostConstruct
	public void init(){
		this.daoEscolha = DAO.newInstance(this.entityManager, PossivelEscolha.class);
	}
	
	public PossivelEscolha buscarPossivelEscolhaPorIndice(Integer indice){		
		return this.daoEscolha.selecionarPeloIndice(indice);
	}	
	
	public List<PossivelEscolha> buscarTodasPossiveisEscolhas(){		
		return this.daoEscolha.selecionar(PossivelEscolha.TUDO);
	}	
	
	@Transactional
	public void incluirPossivelEscolha(PossivelEscolha possivelEscolha) throws ModeloException{		
		this.daoEscolha.inserir(possivelEscolha);
	}
	
	public List<PossivelEscolha> buscarPossiveisEscolhasPorRestaurante(Restaurante esquerda, Restaurante direita){
		Map<String,Object> parametros = ParametroDaConsulta.com("restauranteEsquerdo", esquerda).mais("restauranteDireito", direita).parametros();		
		return this.daoEscolha.selecionarComParametro(PossivelEscolha.SELECIONAR_POR_RESTAURANTE, parametros);
	}
}

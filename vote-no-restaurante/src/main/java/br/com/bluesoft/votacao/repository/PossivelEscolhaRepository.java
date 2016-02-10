package br.com.bluesoft.votacao.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.dao.DAO;
import br.com.bluesoft.votacao.domain.PossivelEscollha;
import br.com.bluesoft.votacao.exception.ModeloException;

@Repository
public class PossivelEscolhaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private DAO<PossivelEscollha, Long> daoEscolha;	
	
	@PostConstruct
	public void init(){
		this.daoEscolha = DAO.newInstance(this.entityManager, PossivelEscollha.class);
	}
	
	public PossivelEscollha buscarPossivelEscolhaPorIndice(Long indice){		
		return this.daoEscolha.selecionarPeloIndice(indice);
	}	
	
	public List<PossivelEscollha> buscarTodasPossiveisEscolhas(){		
		return this.daoEscolha.selecionar(PossivelEscollha.TUDO);
	}
	
	@Transactional
	public void incluirPossivelEscolha(PossivelEscollha possivelEscollha ) throws ModeloException{		
		this.daoEscolha.inserir(possivelEscollha);
	}
}

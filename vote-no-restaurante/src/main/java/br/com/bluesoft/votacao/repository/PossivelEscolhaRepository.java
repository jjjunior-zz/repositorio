package br.com.bluesoft.votacao.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.dao.DAO;
import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.exception.ModeloException;

@Repository
public class PossivelEscolhaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private DAO<PossivelEscolha, Integer> daoEscolha;
	private DAO<Integer, Integer> daoMenorEscolha;

	@PostConstruct
	public void init() {
		this.daoEscolha = DAO.newInstance(this.entityManager, PossivelEscolha.class);
		this.daoMenorEscolha = DAO.newInstance(this.entityManager, Integer.class);
	}

	public PossivelEscolha buscarPossivelEscolhaPorIndice(Integer indice) {
		return this.daoEscolha.selecionarPeloIndice(indice);
	}

	public List<PossivelEscolha> buscarTodasPossiveisEscolhas() {
		return this.daoEscolha.selecionar(PossivelEscolha.TUDO);
	}

	public Integer buscarMenorEscolha() {
		Integer count = daoMenorEscolha.selecionarUnicoResultado(PossivelEscolha.MENOR_ESCOLHA);
		if (count == null) {
			return 0;
		}
		return count.intValue();
	}

	@Transactional
	public void incluirPossivelEscolha(PossivelEscolha possivelEscollha) throws ModeloException {
		this.daoEscolha.inserir(possivelEscollha);
	}
}

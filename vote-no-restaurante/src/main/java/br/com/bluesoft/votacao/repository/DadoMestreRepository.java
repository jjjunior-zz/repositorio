package br.com.bluesoft.votacao.repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.dao.DAO;
import br.com.bluesoft.votacao.domain.PossivelEscollha;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;
import br.com.bluesoft.votacao.exception.ModeloException;

  
@Repository
public class DadoMestreRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private DAO<PossivelEscollha, Long> daoEscolha;
	private DAO<Restaurante, Long> daoRestaurante;

	@PostConstruct
	public void init() {
		this.daoEscolha = DAO.newInstance(this.entityManager, PossivelEscollha.class);
		this.daoRestaurante = DAO.newInstance(this.entityManager, Restaurante.class);		
	}

	@Transactional
	public void carregar() {
		try {
			carregarDadosPossiveisEscolhas();
		} catch (ModeloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void carregarDadosPossiveisEscolhas() throws ModeloException {

		Restaurante ra = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(),RestauranteEnum.MCDONALDS.getPathImagem());
		Restaurante rb = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome(),RestauranteEnum.BURGER_KING.getPathImagem());
		Restaurante rc = Restaurante.newInstance(RestauranteEnum.KFC.getNome(), RestauranteEnum.KFC.getPathImagem());
		Restaurante rd = Restaurante.newInstance(RestauranteEnum.OUTBACK.getNome(),	RestauranteEnum.OUTBACK.getPathImagem());
		Restaurante re = Restaurante.newInstance(RestauranteEnum.SUBWAY.getNome(),RestauranteEnum.SUBWAY.getPathImagem());
		
		daoRestaurante.inserir(ra);
		daoRestaurante.inserir(rb);
		daoRestaurante.inserir(rc);
		daoRestaurante.inserir(rd);
		daoRestaurante.inserir(re);

		PossivelEscollha p1 = PossivelEscollha.newInstance(rb, ra);
		PossivelEscollha p2 = PossivelEscollha.newInstance(rc, ra);
		PossivelEscollha p3 = PossivelEscollha.newInstance(rd, ra);
		PossivelEscollha p4 = PossivelEscollha.newInstance(re, ra);
		PossivelEscollha p5 = PossivelEscollha.newInstance(rc, rb);
		PossivelEscollha p6 = PossivelEscollha.newInstance(rd, rb);
		PossivelEscollha p7 = PossivelEscollha.newInstance(re, rb);
		PossivelEscollha p8 = PossivelEscollha.newInstance(rd, rc);
		PossivelEscollha p9 = PossivelEscollha.newInstance(re, rc);
		PossivelEscollha p10 = PossivelEscollha.newInstance(re, rd);

		daoEscolha.inserir(p1);
		daoEscolha.inserir(p2);
		daoEscolha.inserir(p3);
		daoEscolha.inserir(p4);
		daoEscolha.inserir(p5);
		daoEscolha.inserir(p6);
		daoEscolha.inserir(p7);
		daoEscolha.inserir(p8);
		daoEscolha.inserir(p9);
		daoEscolha.inserir(p10);
	}
}

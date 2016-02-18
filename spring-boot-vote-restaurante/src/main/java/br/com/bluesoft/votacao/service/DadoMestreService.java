package br.com.bluesoft.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;
import br.com.bluesoft.votacao.repository.PossivelEscolhaRepository;
import br.com.bluesoft.votacao.repository.RestauranteRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DadoMestreService {

	private PossivelEscolhaRepository possivelEscolhaRepository;
	private RestauranteRepository restauranteRepository;

	@Autowired
	public DadoMestreService(PossivelEscolhaRepository possivelEscolhaRepository,RestauranteRepository restauranteRepository) {
		this.possivelEscolhaRepository = possivelEscolhaRepository;
		this.restauranteRepository = restauranteRepository;
	}

	@Transactional(readOnly = false)
	public void carregar() {
		
		Restaurante ra = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(),RestauranteEnum.MCDONALDS.getPathImagem());
		Restaurante rb = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome(),RestauranteEnum.BURGER_KING.getPathImagem());
		Restaurante rc = Restaurante.newInstance(RestauranteEnum.KFC.getNome(), RestauranteEnum.KFC.getPathImagem());
		Restaurante rd = Restaurante.newInstance(RestauranteEnum.OUTBACK.getNome(),RestauranteEnum.OUTBACK.getPathImagem());
		Restaurante re = Restaurante.newInstance(RestauranteEnum.SUBWAY.getNome(),RestauranteEnum.SUBWAY.getPathImagem());

		restauranteRepository.saveAndFlush(ra);
		restauranteRepository.saveAndFlush(rb);
		restauranteRepository.saveAndFlush(rc);
		restauranteRepository.saveAndFlush(rd);
		restauranteRepository.saveAndFlush(re);

		PossivelEscolha p1 = PossivelEscolha.newInstance(rb, ra);
		PossivelEscolha p2 = PossivelEscolha.newInstance(rc, ra);
		PossivelEscolha p3 = PossivelEscolha.newInstance(rd, ra);
		PossivelEscolha p4 = PossivelEscolha.newInstance(re, ra);
		PossivelEscolha p5 = PossivelEscolha.newInstance(rc, rb);
		PossivelEscolha p6 = PossivelEscolha.newInstance(rd, rb);
		PossivelEscolha p7 = PossivelEscolha.newInstance(re, rb);
		PossivelEscolha p8 = PossivelEscolha.newInstance(rd, rc);
		PossivelEscolha p9 = PossivelEscolha.newInstance(re, rc);
		PossivelEscolha p10 = PossivelEscolha.newInstance(re, rd);

		possivelEscolhaRepository.saveAndFlush(p1);
		possivelEscolhaRepository.saveAndFlush(p2);
		possivelEscolhaRepository.saveAndFlush(p3);
		possivelEscolhaRepository.saveAndFlush(p4);
		possivelEscolhaRepository.saveAndFlush(p5);
		possivelEscolhaRepository.saveAndFlush(p6);
		possivelEscolhaRepository.saveAndFlush(p7);
		possivelEscolhaRepository.saveAndFlush(p8);
		possivelEscolhaRepository.saveAndFlush(p9);
		possivelEscolhaRepository.saveAndFlush(p10);
	}
}

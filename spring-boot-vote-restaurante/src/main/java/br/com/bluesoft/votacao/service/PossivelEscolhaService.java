package br.com.bluesoft.votacao.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.repository.PossivelEscolhaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PossivelEscolhaService {

	@Autowired
	private PossivelEscolhaRepository possivelEscolhaRepository;

	public PossivelEscolha buscarPossivelEscolhaPorIndice(Integer indice) {
		return this.possivelEscolhaRepository.findOne(indice);
	}

	public List<PossivelEscolha> buscarTodasPossiveisEscolhas() {
		return this.possivelEscolhaRepository.findAll();
	}

	public Integer buscarMenorEscolha() {
		Integer count = this.possivelEscolhaRepository.buscarMenorEscolha();
		if (count == null) {
			return 0;
		}
		return count.intValue();
	}

	@Transactional(readOnly = false)
	public void incluirPossivelEscolha(PossivelEscolha possivelEscollha) {
		this.possivelEscolhaRepository.save(possivelEscollha);
	}

	public List<PossivelEscolha> buscarRestaurantesNaoVotados(List<Restaurante> restaurantesEsquerdo, List<Restaurante> restaurantesDireito) {
		List<PossivelEscolha> possiveisEscolhas = this.possivelEscolhaRepository.buscarRestaurantesNaoVotados(restaurantesEsquerdo, restaurantesDireito);
		return possiveisEscolhas == null ? Collections.emptyList() : possiveisEscolhas;
	}
}

package br.com.getjava.votacao.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.getjava.votacao.domain.PossivelEscolha;
import br.com.getjava.votacao.domain.Restaurante;
import br.com.getjava.votacao.repository.PossivelEscolhaRepository;

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

	public List<PossivelEscolha> buscarRestaurantesNaoVotados(List<Restaurante> restaurantes) {
		List<PossivelEscolha> possiveisEscolhas = this.possivelEscolhaRepository.findByRestauranteLadoEsquerdoNotInAndRestauranteLadoDireitoNotIn(restaurantes, restaurantes);
		return possiveisEscolhas == null ? Collections.emptyList() : possiveisEscolhas;
	}
}

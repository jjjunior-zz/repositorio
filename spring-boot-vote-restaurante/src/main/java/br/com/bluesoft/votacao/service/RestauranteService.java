package br.com.bluesoft.votacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.repository.RestauranteRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	public Restaurante buscarRestaurantePorIndice(int indice) {
		return this.restauranteRepository.findOne(indice);
	}

	public List<Restaurante> buscarTodosRestaurantes() {
		return this.restauranteRepository.findAll();
	}

	public List<Restaurante> buscarRestaurantePorNome(String nome) {
		return this.restauranteRepository.buscarRestaurantePorNome(nome);
	}

	@Transactional(readOnly = false)
	public void incluirRestautante(Restaurante restaurante) {
		this.restauranteRepository.save(restaurante);
	}

	@Transactional(readOnly = false)
	public void excluirRestautante(Restaurante restaurante) {
		this.restauranteRepository.save(restaurante);
	}
}

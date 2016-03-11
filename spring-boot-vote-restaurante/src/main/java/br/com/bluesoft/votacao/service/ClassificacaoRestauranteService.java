package br.com.bluesoft.votacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.repository.ClassificacaoRestauranteRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ClassificacaoRestauranteService {

	@Autowired
	private ClassificacaoRestauranteRepository classificacaoRestauranteRepository;

	public ClassificacaoRestaurante buscarClassificacaoPorIndice(Integer indice) {
		return this.classificacaoRestauranteRepository.findOne(indice);
	}
	
	public ClassificacaoRestaurante buscarClassificacaoPorRestaurante(Restaurante restaurante) {
		return this.classificacaoRestauranteRepository.findByRestaurante(restaurante);
	}
	
	public List<ClassificacaoRestaurante> buscarClassificacaoPorRestaurantes(List<Restaurante> restaurantes) {
		return this.classificacaoRestauranteRepository.findByRestauranteIn(restaurantes);
	}

	public List<ClassificacaoRestaurante> buscarTodasClassificacoes() {
		return this.classificacaoRestauranteRepository.findAll();
	}	

	@Transactional(readOnly = false)
	public void incluirClassificacao(ClassificacaoRestaurante classificacaoRestaurante) {
		classificacaoRestauranteRepository.save(classificacaoRestaurante);
	}
	
	@Transactional(readOnly = false)
	public void excluirClassificacao(ClassificacaoRestaurante classificacaoRestaurante) {
		classificacaoRestauranteRepository.delete(classificacaoRestaurante);
	}	
}

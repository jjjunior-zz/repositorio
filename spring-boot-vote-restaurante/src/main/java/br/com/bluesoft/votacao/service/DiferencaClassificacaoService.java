package br.com.bluesoft.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.DiferencaClassificacao;
import br.com.bluesoft.votacao.repository.DiferencaClassificacaoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DiferencaClassificacaoService {

	@Autowired
	private DiferencaClassificacaoRepository diferencaClassificacaoRepository;

	public DiferencaClassificacao buscarClassificacaoPorIndice(int valor) {
		return this.diferencaClassificacaoRepository.buscarPorDiferencaInicialEFinal(valor);
	}	
}

package br.com.getjava.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.getjava.votacao.domain.DiferencaClassificacao;
import br.com.getjava.votacao.repository.DiferencaClassificacaoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DiferencaClassificacaoService {

	@Autowired
	private DiferencaClassificacaoRepository diferencaClassificacaoRepository;

	public DiferencaClassificacao buscarClassificacaoPorDiferencaEntreValor(int valor) {
		return this.diferencaClassificacaoRepository.buscarPorDiferencaInicialEFinal(valor);
	}	
}

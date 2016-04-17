package br.com.getjava.votacao.classificacao;

import br.com.getjava.votacao.domain.ClassificacaoRestaurante;
import br.com.getjava.votacao.domain.DiferencaClassificacao;

public interface Classificacao {

	public Integer calcular(ClassificacaoRestaurante classificacaoRestaurante, DiferencaClassificacao diferencaClassificacao);

}

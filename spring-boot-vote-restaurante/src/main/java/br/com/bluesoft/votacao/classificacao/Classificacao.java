package br.com.bluesoft.votacao.classificacao;

import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.DiferencaClassificacao;

public interface Classificacao{
	
	public Integer calcular(ClassificacaoRestaurante classificacaoRestaurante, DiferencaClassificacao diferencaClassificacao);

}

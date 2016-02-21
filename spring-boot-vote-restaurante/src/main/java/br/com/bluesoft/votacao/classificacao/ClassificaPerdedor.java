package br.com.bluesoft.votacao.classificacao;

import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.DiferencaClassificacao;

public class ClassificaPerdedor implements Classificacao {
	
	@Override
	public Integer calcular(ClassificacaoRestaurante classificacaoRestaurante, DiferencaClassificacao diferencaClassificacao) {		
		Double pontosEsperados =  diferencaClassificacao.getPorcentualInferior().doubleValue() / 100;		
		Double resultado = classificacaoRestaurante.getClassificacaoAnterior() + 10*(0 - pontosEsperados);		
		return resultado.intValue();		
	}

}

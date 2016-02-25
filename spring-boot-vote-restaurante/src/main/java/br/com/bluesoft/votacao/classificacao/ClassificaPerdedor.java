package br.com.bluesoft.votacao.classificacao;

import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.DiferencaClassificacao;

public class ClassificaPerdedor implements Classificacao {
	
	private ClassificaPerdedor(){		
	}
	
	public static ClassificaPerdedor newIntance(){
		return new ClassificaPerdedor();
	}
	
	@Override
	public Integer calcular(ClassificacaoRestaurante classificacaoRestaurante, DiferencaClassificacao diferencaClassificacao) {		
		Double pontosEsperados =  diferencaClassificacao.getPorcentualInferior().doubleValue() / 100;		
		Double resultado = classificacaoRestaurante.getClassificacaoAnterior() + 10*(0 - pontosEsperados);		
		return resultado.intValue();		
	}

}

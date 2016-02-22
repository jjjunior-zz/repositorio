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
	public int calcular(ClassificacaoRestaurante classificacaoRestaurante, DiferencaClassificacao diferencaClassificacao) {		
		float pontosEsperados = (float) diferencaClassificacao.getPorcentualInferior() / 100;		
		float resultado = classificacaoRestaurante.getClassificacaoAnterior() + 10*(0 - pontosEsperados);		
		return (int)resultado;		
	}

}

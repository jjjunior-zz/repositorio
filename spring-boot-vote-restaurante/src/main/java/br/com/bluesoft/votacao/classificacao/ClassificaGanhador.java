package br.com.bluesoft.votacao.classificacao;

import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.DiferencaClassificacao;

public class ClassificaGanhador implements Classificacao {
	
	private ClassificaGanhador(){		
	}
	
	public static ClassificaGanhador newIntance(){
		return new ClassificaGanhador();
	}

	@Override
	public int calcular(ClassificacaoRestaurante classificacaoRestaurante,DiferencaClassificacao diferencaClassificacao) {
		float pontosEsperados =  (float)diferencaClassificacao.getPorcentualSuperior() / 100;		
		float resultado = classificacaoRestaurante.getClassificacaoAnterior() + 10*(1 - pontosEsperados);		
		return (int) resultado;		
	}
}

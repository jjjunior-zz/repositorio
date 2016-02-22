package br.com.bluesoft.votacao.classificacao;

import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.DiferencaClassificacao;

public class CalculadoraDeClassificacao {	
	
	private CalculadoraDeClassificacao(){		
	}
	
	public static CalculadoraDeClassificacao newIntance(){
		return new CalculadoraDeClassificacao();
	}
	
	public Integer realizarCalculo(DiferencaClassificacao diferencaClassificacao,ClassificacaoRestaurante classificacaoRestaurante, Classificacao classificacao){
		return classificacao.calcular(classificacaoRestaurante, diferencaClassificacao);
	}
}

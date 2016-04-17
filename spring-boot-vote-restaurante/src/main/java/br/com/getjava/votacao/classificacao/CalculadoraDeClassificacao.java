package br.com.getjava.votacao.classificacao;

import br.com.getjava.votacao.domain.ClassificacaoRestaurante;
import br.com.getjava.votacao.domain.DiferencaClassificacao;

public class CalculadoraDeClassificacao {

	private CalculadoraDeClassificacao() {
	}

	public static CalculadoraDeClassificacao newIntance() {
		return new CalculadoraDeClassificacao();
	}

	public Integer realizarCalculo(DiferencaClassificacao diferencaClassificacao, ClassificacaoRestaurante classificacaoRestaurante, Classificacao classificacao) {
		return classificacao.calcular(classificacaoRestaurante, diferencaClassificacao);
	}
}

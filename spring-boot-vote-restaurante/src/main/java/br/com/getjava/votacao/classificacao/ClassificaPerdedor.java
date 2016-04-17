package br.com.getjava.votacao.classificacao;

import br.com.getjava.votacao.domain.ClassificacaoRestaurante;
import br.com.getjava.votacao.domain.DiferencaClassificacao;

public class ClassificaPerdedor implements Classificacao {

	private ClassificaPerdedor() {
	}

	public static ClassificaPerdedor newIntance() {
		return new ClassificaPerdedor();
	}

	@Override
	public Integer calcular(ClassificacaoRestaurante classificacaoRestaurante, DiferencaClassificacao diferencaClassificacao) {
		Double pontosEsperados = diferencaClassificacao.getPorcentualInferior().doubleValue() / 100;
		Double resultado = classificacaoRestaurante.getClassificacaoAnterior() + 10 * (0 - pontosEsperados);
		return resultado.intValue();
	}

}

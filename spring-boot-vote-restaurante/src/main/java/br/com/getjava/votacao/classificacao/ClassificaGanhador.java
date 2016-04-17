package br.com.getjava.votacao.classificacao;

import br.com.getjava.votacao.domain.ClassificacaoRestaurante;
import br.com.getjava.votacao.domain.DiferencaClassificacao;

public class ClassificaGanhador implements Classificacao {

	private ClassificaGanhador() {
	}

	public static ClassificaGanhador newIntance() {
		return new ClassificaGanhador();
	}

	@Override
	public Integer calcular(ClassificacaoRestaurante classificacaoRestaurante, DiferencaClassificacao diferencaClassificacao) {
		Double pontosEsperados = diferencaClassificacao.getPorcentualSuperior().doubleValue() / 100;
		Double resultado = classificacaoRestaurante.getClassificacaoAnterior() + 10 * (1 - pontosEsperados);
		return resultado.intValue();
	}
}

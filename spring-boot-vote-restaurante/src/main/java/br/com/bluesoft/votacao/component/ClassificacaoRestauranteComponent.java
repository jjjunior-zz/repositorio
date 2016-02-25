package br.com.bluesoft.votacao.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bluesoft.votacao.classificacao.CalculadoraDeClassificacao;
import br.com.bluesoft.votacao.classificacao.ClassificaGanhador;
import br.com.bluesoft.votacao.classificacao.ClassificaPerdedor;
import br.com.bluesoft.votacao.classificacao.Classificacao;
import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.DiferencaClassificacao;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.repository.ClassificacaoRestauranteRepository;
import br.com.bluesoft.votacao.repository.DiferencaClassificacaoRepository;

@Component
public class ClassificacaoRestauranteComponent {
	private ClassificacaoRestaurante			classificacaoRestauranteDireita;
	private ClassificacaoRestaurante			classificacaoRestauranteEsquerda;

	@Autowired
	private ClassificacaoRestauranteRepository	classificacaoRestauranteRepository;

	@Autowired
	private DiferencaClassificacaoRepository	diferencaClassificacaoRepository;	

	private DiferencaClassificacao				diferencaClassificacao;

	public void buscarClassificacaoRestaurantes(Restaurante direito, Restaurante esquerdo) {
		this.classificacaoRestauranteDireita = classificacaoRestauranteRepository.findOne(direito.getId());
		this.classificacaoRestauranteEsquerda = classificacaoRestauranteRepository.findOne(esquerdo.getId());
	}

	public DiferencaClassificacao buscarDiferencaClassificacao() {
		Integer diferenca = 0;
		if (classificacaoRestauranteDireita.getClassificacaoAnterior() >= classificacaoRestauranteEsquerda.getClassificacaoAnterior()) {
			diferenca = classificacaoRestauranteDireita.getClassificacaoAnterior().intValue() - classificacaoRestauranteEsquerda.getClassificacaoAnterior().intValue();
		} else {
			diferenca = classificacaoRestauranteEsquerda.getClassificacaoAnterior().intValue() - classificacaoRestauranteDireita.getClassificacaoAnterior().intValue();
		}
		this.diferencaClassificacao = diferencaClassificacaoRepository.buscarPorDiferencaInicialEFinal(diferenca);
		return diferencaClassificacao;
	}

	public void calcularClassificacaoRestauranteGanhador(Restaurante restaurante) {
		Classificacao classificacao = ClassificaGanhador.newIntance();
		CalculadoraDeClassificacao calculadoraDeClassificacao = CalculadoraDeClassificacao.newIntance();

		if (restaurante.getId() == classificacaoRestauranteDireita.getRestaurante().getId()) {
			Integer valorClassificacao = calculadoraDeClassificacao.realizarCalculo(this.diferencaClassificacao, classificacaoRestauranteDireita, classificacao);
			classificacaoRestauranteDireita.setClassificacaoAtual(valorClassificacao);
			classificacaoRestauranteDireita.setClassificacaoAnterior(valorClassificacao);
			classificacaoRestauranteRepository.save(classificacaoRestauranteDireita);
		} else {
			Integer valorClassificacao = calculadoraDeClassificacao.realizarCalculo(this.diferencaClassificacao, classificacaoRestauranteEsquerda, classificacao);
			classificacaoRestauranteEsquerda.setClassificacaoAtual(valorClassificacao);
			classificacaoRestauranteEsquerda.setClassificacaoAnterior(valorClassificacao);
			classificacaoRestauranteRepository.save(classificacaoRestauranteEsquerda);
		}

		calcularClassificacaoRestaurantePerdedor(restaurante);

	}

	private void calcularClassificacaoRestaurantePerdedor(Restaurante restaurante) {
		CalculadoraDeClassificacao calculadoraDeClassificacao = CalculadoraDeClassificacao.newIntance();
		Classificacao classificacao = ClassificaPerdedor.newIntance();

		if (restaurante.getId() == classificacaoRestauranteDireita.getRestaurante().getId()) {
			Integer valorClassificacao = calculadoraDeClassificacao.realizarCalculo(this.diferencaClassificacao, classificacaoRestauranteDireita, classificacao);
			classificacaoRestauranteDireita.setClassificacaoAtual(valorClassificacao);
			classificacaoRestauranteDireita.setClassificacaoAnterior(valorClassificacao);
			classificacaoRestauranteRepository.save(classificacaoRestauranteDireita);
		} else {
			Integer valorClassificacao = calculadoraDeClassificacao.realizarCalculo(this.diferencaClassificacao, classificacaoRestauranteEsquerda, classificacao);
			classificacaoRestauranteEsquerda.setClassificacaoAtual(valorClassificacao);
			classificacaoRestauranteEsquerda.setClassificacaoAnterior(valorClassificacao);
			classificacaoRestauranteRepository.save(classificacaoRestauranteEsquerda);
		}
	}
}

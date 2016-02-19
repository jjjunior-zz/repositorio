package br.com.bluesoft.votacao;

public class EloRating {

	private double	pontuacaoFinalRestauranteEsquerdo;
	private double	pontuacaoFinalRestauranteDireito;

	public void calcularPontuacao(double classificacaoAtualEsquerda, double classificacaoAtualDireita, double pontuacaoEsquerda, double pontuacaoDireita) {

		double finalResult1 = 0;
		double finalResult2 = 0;

		double E = 0;

		if (pontuacaoEsquerda != pontuacaoDireita) {
			if (pontuacaoEsquerda > pontuacaoDireita) {
				E = 120 - Math.round(1 / (1 + Math.pow(10, ((classificacaoAtualDireita - classificacaoAtualEsquerda) / 400))) * 120);
				finalResult1 = classificacaoAtualEsquerda + E;
				finalResult2 = classificacaoAtualDireita - E;
			} else {
				E = 120 - Math.round(1 / (1 + Math.pow(10, ((classificacaoAtualEsquerda - classificacaoAtualDireita) / 400))) * 120);
				finalResult1 = classificacaoAtualEsquerda - E;
				finalResult2 = classificacaoAtualDireita + E;
			}
		} else {
			if (classificacaoAtualEsquerda == classificacaoAtualDireita) {
				finalResult1 = classificacaoAtualEsquerda;
				finalResult2 = classificacaoAtualDireita;
			} else {
				if (classificacaoAtualEsquerda > classificacaoAtualDireita) {
					E = (120 - Math.round(1 / (1 + Math.pow(10, ((classificacaoAtualEsquerda - classificacaoAtualDireita) / 400))) * 120))
							- (120 - Math.round(1 / (1 + Math.pow(10, ((classificacaoAtualDireita - classificacaoAtualEsquerda) / 400))) * 120));
					finalResult1 = classificacaoAtualEsquerda - E;
					finalResult2 = classificacaoAtualDireita + E;
				} else {
					E = (120 - Math.round(1 / (1 + Math.pow(10, ((classificacaoAtualDireita - classificacaoAtualEsquerda) / 400))) * 120))
							- (120 - Math.round(1 / (1 + Math.pow(10, ((classificacaoAtualEsquerda - classificacaoAtualDireita) / 400))) * 120));
					finalResult1 = classificacaoAtualEsquerda + E;
					finalResult2 = classificacaoAtualDireita - E;
				}
			}
		}
		this.pontuacaoFinalRestauranteEsquerdo = finalResult1 - classificacaoAtualEsquerda;
		this.pontuacaoFinalRestauranteDireito = finalResult2 - classificacaoAtualDireita;
	}

	public double getPontuacaoFinalRestauranteEsquerdo() {
		return pontuacaoFinalRestauranteEsquerdo;
	}

	public double getPontuacaoFinalRestauranteDireito() {
		return pontuacaoFinalRestauranteDireito;
	}
}

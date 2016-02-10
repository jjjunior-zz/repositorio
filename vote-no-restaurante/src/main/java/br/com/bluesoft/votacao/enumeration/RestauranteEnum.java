package br.com.bluesoft.votacao.enumeration;

public enum RestauranteEnum {

	MCDONALDS("McDonalds"), BURGER_KING("BurgerKing"),KFC("KFC"), OUTBACK("Outback"),SUBWAY("Subway");

	private final String nome;

	RestauranteEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
}

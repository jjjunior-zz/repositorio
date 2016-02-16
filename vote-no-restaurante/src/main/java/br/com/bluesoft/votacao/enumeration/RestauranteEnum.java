package br.com.bluesoft.votacao.enumeration;

public enum RestauranteEnum {

	MCDONALDS("McDonalds","/resources/images/mcdonalds.jpg"), BURGER_KING("BurgerKing","/resources/images/burgerking.jpg"),KFC("KFC","/resources/images/kfc.jpg"), OUTBACK("Outback","/resources/images/outback.jpg"),SUBWAY("Subway","/resources/images/subway.jpg");

	private final String nome;
	private final String pathImagem;

	RestauranteEnum(String nome, String pathImagem) {
		this.nome = nome;
		this.pathImagem = pathImagem;
	}

	public String getNome() {
		return this.nome;
	}

	public String getPathImagem() {
		return pathImagem;
	}	
}
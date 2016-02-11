package br.com.bluesoft.votacao.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "possivel_escolha")
@Entity
@NamedQueries({
		@NamedQuery(name = PossivelEscolha.TUDO, query = "select p from PossivelEscolha p"),
		@NamedQuery(name = PossivelEscolha.SELECIONAR_POR_RESTAURANTE, query = "select p from PossivelEscolha p where p.restauranteLadoEsquerdo = :restauranteEsquerdo and p.restauranteLadoDireito = :restauranteDireito"),
		@NamedQuery(name = PossivelEscolha.SELECIONAR_RESTAURANTES_NAO_VOTADOS, query = "select p from PossivelEscolha p where p.restauranteLadoEsquerdo not in :restaurantesEsquerdo and p.restauranteLadoDireito not in :restaurantesDireito") })
public class PossivelEscolha implements Serializable {

	private static final long	serialVersionUID					= 1L;

	public static final String	TUDO								= "br.com.bluesoft.votacao.domain.PossivelEscolha.tudo";
	public static final String	SELECIONAR_POR_RESTAURANTE			= "br.com.bluesoft.votacao.domain.PossivelEscolha.selecionarPorRestaurante";
	public static final String	SELECIONAR_RESTAURANTES_NAO_VOTADOS	= "br.com.bluesoft.votacao.domain.PossivelEscolha.selecionarRestaurantesNaoVotados";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer				id;

	@OneToOne
	private Restaurante			restauranteLadoEsquerdo;

	@OneToOne
	private Restaurante			restauranteLadoDireito;

	PossivelEscolha() {
	}

	public static PossivelEscolha newInstance() {
		return new PossivelEscolha();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Restaurante getRestauranteLadoEsquerdo() {
		return restauranteLadoEsquerdo;
	}

	public void setRestauranteLadoEsquerdo(Restaurante restauranteLadoEsquerdo) {
		this.restauranteLadoEsquerdo = restauranteLadoEsquerdo;
	}

	public Restaurante getRestauranteLadoDireito() {
		return restauranteLadoDireito;
	}

	public void setRestauranteLadoDireito(Restaurante restauranteLadoDireito) {
		this.restauranteLadoDireito = restauranteLadoDireito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PossivelEscolha other = (PossivelEscolha) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

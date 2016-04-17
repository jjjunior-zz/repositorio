package br.com.getjava.votacao.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "possivel_escolha")
public class PossivelEscolha extends AbstractPersistable<Integer> {

	private static final long	serialVersionUID	= 1L;

	@OneToOne
	@JoinColumn(name = "restaurante_esquerdo_id")
	private Restaurante			restauranteLadoEsquerdo;

	@OneToOne
	@JoinColumn(name = "restaurante_direito_id")
	private Restaurante			restauranteLadoDireito;

	@Transient
	private String				pathImagemLadoEsquerdo;

	@Transient
	private String				pathImagemLadoDireito;

	PossivelEscolha() {
	}

	PossivelEscolha(Restaurante restauranteLadoEsquerdo, Restaurante restauranteLadoDireito) {
		this.restauranteLadoEsquerdo = restauranteLadoEsquerdo;
		this.restauranteLadoDireito = restauranteLadoDireito;
	}

	public static PossivelEscolha newInstance() {
		return new PossivelEscolha();
	}

	public static PossivelEscolha newInstance(Restaurante restauranteLadoEsquerdo, Restaurante restauranteLadoDireito) {
		return new PossivelEscolha(restauranteLadoEsquerdo, restauranteLadoDireito);
	}

	@Override
	public void setId(Integer id) {
		super.setId(id);
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

	public String getPathImagemLadoEsquerdo() {
		return pathImagemLadoEsquerdo;
	}

	public void setPathImagemLadoEsquerdo(String pathImagemLadoEsquerdo) {
		this.pathImagemLadoEsquerdo = pathImagemLadoEsquerdo;
	}

	public String getPathImagemLadoDireito() {
		return pathImagemLadoDireito;
	}

	public void setPathImagemLadoDireito(String pathImagemLadoDireito) {
		this.pathImagemLadoDireito = pathImagemLadoDireito;
	}
}

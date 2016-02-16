package br.com.bluesoft.votacao.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "possivel_escolha")
@Entity
@NamedQueries({ 
	@NamedQuery(name = PossivelEscollha.TUDO, query = "select p from PossivelEscollha p"),
	@NamedQuery(name = PossivelEscollha.MENOR_ESCOLHA, query = "select min(p.id) from PossivelEscollha p")
})
public class PossivelEscollha implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String TUDO = "br.com.bluesoft.votacao.domain.PossivelEscollha.tudo";
	public static final String MENOR_ESCOLHA = "br.com.bluesoft.votacao.domain.PossivelEscollha.MenorEscolha";

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)	
	private Integer id;	
	
	@OneToOne
	@JoinColumn(name = "restaurante_esquerdo_id")
	private Restaurante restauranteLadoEsquerdo;
	
	@OneToOne
	@JoinColumn(name = "restaurante_direito_id")
	private Restaurante restauranteLadoDireito;
	
	@Transient
	private String pathImagemLadoEsquerdo;
	
	@Transient
	private String pathImagemLadoDireito;	
	
	PossivelEscollha(){		
	}
	
	PossivelEscollha(Restaurante restauranteLadoEsquerdo,Restaurante restauranteLadoDireito){
		this.restauranteLadoEsquerdo = restauranteLadoEsquerdo;
		this.restauranteLadoDireito = restauranteLadoDireito;
	}	
	
	public static PossivelEscollha newInstance() {
		return new PossivelEscollha();
	}
	
	public static PossivelEscollha newInstance(Restaurante restauranteLadoEsquerdo,Restaurante restauranteLadoDireito) {
		return new PossivelEscollha(restauranteLadoEsquerdo,restauranteLadoDireito);
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
		PossivelEscollha other = (PossivelEscollha) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

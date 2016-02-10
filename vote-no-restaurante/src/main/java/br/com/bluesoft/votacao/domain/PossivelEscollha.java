package br.com.bluesoft.votacao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "possivel_escolha")
@Entity
@NamedQueries({ 
	@NamedQuery(name = PossivelEscollha.TUDO, query = "select p from PossivelEscollha p")	
})
public class PossivelEscollha implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String TUDO = "br.com.bluesoft.votacao.domain.PossivelEscollha.tudo";

	@Id @GeneratedValue
	private long id;	
	
	@Column(name = "restaurante_lado_esquerdo")
	private String restauranteLadoEsquerdo;
	
	@Column(name = "restaurante_lado_direito")
	private String restauranteLadoDireito;	
	
	@Transient
	private String pathImagemLadoEsquerdo;	
	
	@Transient
	private String pathImagemLadoDireito;
	
	PossivelEscollha(){		
	}
	
	public static PossivelEscollha newInstance() {
		return new PossivelEscollha();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRestauranteLadoEsquerdo() {
		return restauranteLadoEsquerdo;
	}

	public void setRestauranteLadoEsquerdo(String restauranteLadoEsquerdo) {
		this.restauranteLadoEsquerdo = restauranteLadoEsquerdo;
	}

	public String getRestauranteLadoDireito() {
		return restauranteLadoDireito;
	}

	public void setRestauranteLadoDireito(String restauranteLadoDireito) {
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

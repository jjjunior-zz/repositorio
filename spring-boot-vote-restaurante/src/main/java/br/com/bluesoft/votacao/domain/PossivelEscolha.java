package br.com.bluesoft.votacao.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "possivel_escolha")
@NamedQueries({ 
	@NamedQuery(name = PossivelEscolha.TUDO, query = "select p from PossivelEscolha p"),
	@NamedQuery(name = PossivelEscolha.MENOR_ESCOLHA, query = "select min(p.id) from PossivelEscolha p"),
	@NamedQuery(name = PossivelEscolha.SELECIONAR_RESTAURANTES_NAO_VOTADOS, query = "select p from PossivelEscolha p where p.restauranteLadoEsquerdo not in :restaurantesEsquerdo and p.restauranteLadoDireito not in :restaurantesDireito")
})
public class PossivelEscolha extends AbstractPersistable<Integer> {
	
	private static final long serialVersionUID = 1L;

	public static final String TUDO = "br.com.bluesoft.votacao.domain.PossivelEscolha.tudo";	
	public static final String SELECIONAR_RESTAURANTES_NAO_VOTADOS = "br.com.bluesoft.votacao.domain.PossivelEscolha.selecionaNaoVotados";
	public static final String MENOR_ESCOLHA = "br.com.bluesoft.votacao.domain.PossivelEscolha.menorEscolha";
	
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
	
	PossivelEscolha(){		
	}
	
	PossivelEscolha(Restaurante restauranteLadoEsquerdo,Restaurante restauranteLadoDireito){
		this.restauranteLadoEsquerdo = restauranteLadoEsquerdo;
		this.restauranteLadoDireito = restauranteLadoDireito;
	}	
	
	public static PossivelEscolha newInstance() {
		return new PossivelEscolha();
	}
	
	public static PossivelEscolha newInstance(Restaurante restauranteLadoEsquerdo,Restaurante restauranteLadoDireito) {
		return new PossivelEscolha(restauranteLadoEsquerdo,restauranteLadoDireito);
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

package br.com.bluesoft.votacao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "classificacao_restaurante")
public class ClassificacaoRestaurante extends AbstractPersistable<Integer> {
	
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "restaurante_id")	
	private Restaurante restaurante;

	@Column(name = "classificacao_atual" )
	private Float classificacaoAtual;
	
	@Column(name = "classificacao_anterior" )
	private Float classificacaoAnterior;	
	
	ClassificacaoRestaurante(){}	
	
	ClassificacaoRestaurante(Restaurante restaurante,Float classificacaoAnterior) {	
		this.restaurante = restaurante;		
		this.classificacaoAnterior = classificacaoAnterior;				
	}
	
	public static ClassificacaoRestaurante newInstance(Restaurante restaurante, Float classificacaoAnterior ) {
		return new ClassificacaoRestaurante(restaurante, classificacaoAnterior);
	}
	
	public Float getClassificacaoAtual() {
		return classificacaoAtual;
	}
	public void setClassificacaoAtual(Float classificacaoAtual) {
		this.classificacaoAtual = classificacaoAtual;
	}
	public Float getClassificacaoAnterior() {
		return classificacaoAnterior;
	}
	public void setClassificacaoAnterior(Float classificacaoAnterior) {
		this.classificacaoAnterior = classificacaoAnterior;
	}
}

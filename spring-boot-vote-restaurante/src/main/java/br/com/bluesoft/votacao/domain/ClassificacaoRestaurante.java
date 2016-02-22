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
	private Integer classificacaoAtual;
	
	@Column(name = "classificacao_anterior" )
	private Integer classificacaoAnterior;	
	
	ClassificacaoRestaurante(){}	
	
	ClassificacaoRestaurante(Restaurante restaurante,Integer classificacaoAnterior) {	
		this.restaurante = restaurante;		
		this.classificacaoAnterior = classificacaoAnterior;				
	}
	
	public static ClassificacaoRestaurante newInstance(Restaurante restaurante, Integer classificacaoAnterior ) {
		return new ClassificacaoRestaurante(restaurante, classificacaoAnterior);
	}
	
	public Integer getClassificacaoAtual() {
		return classificacaoAtual;
	}
	public void setClassificacaoAtual(Integer classificacaoAtual) {
		this.classificacaoAtual = classificacaoAtual;
	}
	public Integer getClassificacaoAnterior() {
		return classificacaoAnterior;
	}
	public void setClassificacaoAnterior(Integer classificacaoAnterior) {
		this.classificacaoAnterior = classificacaoAnterior;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}	
}

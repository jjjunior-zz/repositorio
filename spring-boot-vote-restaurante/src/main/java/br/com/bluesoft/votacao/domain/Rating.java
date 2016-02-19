package br.com.bluesoft.votacao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "rating")
public class Rating extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = 1L;

	@Column(name ="diferenca_inicial", nullable = false )
	public Integer diferencaInicial;
	
	@Column(name ="diferenca_final", nullable = false )
	public Integer diferencaFinal;
	
	@Column(name ="porcentual_superior", nullable = false )
	public Integer porcentualSuperior;
	
	@Column(name ="porcentual_inferior", nullable = false )
	public Integer porcentualInferior;	

	Rating(Integer diferencaInicial, Integer diferencaFinal, Integer porcentualSuperior, Integer porcentualInferior) {		
		this.diferencaInicial = diferencaInicial;
		this.diferencaFinal = diferencaFinal;
		this.porcentualSuperior = porcentualSuperior;
		this.porcentualInferior = porcentualInferior;
	}
	
	public static Rating newIntance(Integer diferencaInicial, Integer diferencaFinal, Integer porcentualSuperior, Integer porcentualInferior){
		return new Rating(diferencaInicial, diferencaFinal, porcentualSuperior, porcentualInferior);
	}

	public Integer getDiferencaInicial() {
		return diferencaInicial;
	}

	public void setDiferencaInicial(Integer diferencaInicial) {
		this.diferencaInicial = diferencaInicial;
	}

	public Integer getDiferencaFinal() {
		return diferencaFinal;
	}

	public void setDiferencaFinal(Integer diferencaFinal) {
		this.diferencaFinal = diferencaFinal;
	}

	public Integer getPorcentualSuperior() {
		return porcentualSuperior;
	}

	public void setPorcentualSuperior(Integer porcentualSuperior) {
		this.porcentualSuperior = porcentualSuperior;
	}

	public Integer getPorcentualInferior() {
		return porcentualInferior;
	}

	public void setPorcentualInferior(Integer porcentualInferior) {
		this.porcentualInferior = porcentualInferior;
	}
}

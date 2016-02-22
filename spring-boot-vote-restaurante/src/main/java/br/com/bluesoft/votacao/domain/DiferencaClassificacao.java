package br.com.bluesoft.votacao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "diferenca_classificacao")
public class DiferencaClassificacao extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = 1L;

	@Column(name ="diferenca_inicial", nullable = false )
	private int diferencaInicial;
	
	@Column(name ="diferenca_final", nullable = false )
	private int diferencaFinal;
	
	@Column(name ="porcentual_superior", nullable = false )
	private int porcentualSuperior;
	
	@Column(name ="porcentual_inferior", nullable = false )
	private int porcentualInferior;	
	
	DiferencaClassificacao(){		
	}

	DiferencaClassificacao(int diferencaInicial, int diferencaFinal, int porcentualSuperior, int porcentualInferior) {		
		this.diferencaInicial = diferencaInicial;
		this.diferencaFinal = diferencaFinal;
		this.porcentualSuperior = porcentualSuperior;
		this.porcentualInferior = porcentualInferior;
	}
	
	public static DiferencaClassificacao newIntance(int diferencaInicial, int diferencaFinal, int porcentualSuperior, int porcentualInferior){
		return new DiferencaClassificacao(diferencaInicial, diferencaFinal, porcentualSuperior, porcentualInferior);
	}

	public int getDiferencaInicial() {
		return diferencaInicial;
	}

	public void setDiferencaInicial(int diferencaInicial) {
		this.diferencaInicial = diferencaInicial;
	}

	public int getDiferencaFinal() {
		return diferencaFinal;
	}

	public void setDiferencaFinal(int diferencaFinal) {
		this.diferencaFinal = diferencaFinal;
	}

	public int getPorcentualSuperior() {
		return porcentualSuperior;
	}

	public void setPorcentualSuperior(int porcentualSuperior) {
		this.porcentualSuperior = porcentualSuperior;
	}

	public int getPorcentualInferior() {
		return porcentualInferior;
	}

	public void setPorcentualInferior(int porcentualInferior) {
		this.porcentualInferior = porcentualInferior;
	}
}

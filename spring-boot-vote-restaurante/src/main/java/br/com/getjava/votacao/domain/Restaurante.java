package br.com.getjava.votacao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "restaurante")
public class Restaurante extends AbstractPersistable<Integer> {

	private static final long	serialVersionUID	= 1L;	
	@Column(nullable = false, unique = true)
	private String				nome;
	
	@Column(nullable = false, unique = true)
	private String pathImagem;
	
	private Integer pontos;

	public Restaurante() {
	}

	public static Restaurante newInstance() {
		return new Restaurante();
	}

	public static Restaurante newInstance(String nome,String pathImagem) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(nome);
		restaurante.setPathImagem(pathImagem);
		return restaurante;
	}
	
	public static Restaurante newInstance(String nome) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(nome);		
		return restaurante;
	}


	@Override
	public void setId(Integer id) {	
		super.setId(id);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

	public String getPathImagem() {
		return pathImagem;
	}

	public void setPathImagem(String pathImagem) {
		this.pathImagem = pathImagem;
	}	
	
	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	@Override
	public String toString() {
		return this.nome;
	}	
}

package br.com.bluesoft.votacao.domain;

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
	
	@Column(name ="path_imagem", nullable = false, unique = true)
	private String pathImagem;

	Restaurante() {
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
	
	@Override
	public String toString() {
		return this.nome;
	}	
}

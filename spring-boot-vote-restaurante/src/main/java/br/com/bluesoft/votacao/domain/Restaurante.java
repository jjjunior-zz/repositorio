package br.com.bluesoft.votacao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "restaurante")
@NamedQueries({ 
		@NamedQuery(name = Restaurante.TUDO, query = "select r from Restaurante r"),
		@NamedQuery(name = Restaurante.SELECIONAR_POR_NOME, query = "select r from Restaurante r where r.nome = :nome")
})
public class Restaurante extends AbstractPersistable<Integer> {

	private static final long	serialVersionUID	= 1L;
	public static final String	TUDO				= "br.com.bluesoft.votacao.domain.Restaurante.todos";
	public static final String	SELECIONAR_POR_NOME	= "br.com.bluesoft.votacao.domain.Restaurante.selecionarPorNome";
	
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

package br.com.bluesoft.votacao.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "restaurante")
@NamedQueries({ 
		@NamedQuery(name = Restaurante.TUDO, query = "select r from Restaurante r"),
		@NamedQuery(name = Restaurante.SELECIONAR_POR_NOME, query = "select r from Restaurante r where r.nome = :nome")
})
public class Restaurante implements Serializable {

	private static final long	serialVersionUID	= 1L;
	public static final String	TUDO				= "br.com.bluesoft.votacao.domain.Restaurante.todos";
	public static final String	SELECIONAR_POR_NOME	= "br.com.bluesoft.votacao.domain.Restaurante.selecionarPorNome";

	@Id
	@GeneratedValue
	private Integer				id;
	private String				nome;

	Restaurante() {
	}

	public static Restaurante newInstance() {
		return new Restaurante();
	}

	public static Restaurante newInstance(String nome) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(nome);
		return restaurante;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Restaurante other = (Restaurante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", nome=" + nome + "]";
	}

}

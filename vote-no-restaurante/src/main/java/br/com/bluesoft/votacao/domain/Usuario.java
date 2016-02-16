package br.com.bluesoft.votacao.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.bluesoft.votacao.util.Util;

@Table(name = "usuario")
@Entity
@NamedQueries({ 
	@NamedQuery(name = Usuario.TUDO, query = "select u from Usuario u"),
	@NamedQuery(name = Usuario.SELECIONAR_POR_EMAIL, query = "select u from Usuario u where u.email = :email")
})
public class Usuario implements Serializable {

	private static final long	serialVersionUID		= 1L;
	public static final String	TUDO					= "br.com.bluesoft.votacao.domain.Usuario.tudo";
	public static final String	SELECIONAR_POR_EMAIL	= "br.com.bluesoft.votacao.domain.Usuario.selecionarPorEmail";	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer				id;

	@NotNull(message = "usuario.nome")
	@Column(nullable = false, unique = true)
	private String				nome;

	@NotNull(message = "usuario.email")
	@Column(nullable = false, unique = true)
	private String				email;

	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	@Column(name = "data_cadastro", nullable = false)
	private Calendar			dataCadastro;

	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	@Column(name = "data_alteracao")
	private Calendar			dataAlteracao;
	
	Usuario(){		
	}
	
	Usuario(String nome, String email){
		this.nome = nome;
		this.email = email;	
	}
	
	public static Usuario newInstance() {
		return new Usuario();
	}

	public static Usuario newInstance(String nome, String email) {		
		return new Usuario(nome,email);
	}	

	@PrePersist
	public void prePersist() {
		this.setDataCadastro(Calendar.getInstance());		
		Util.tratarAtributosString(this);
	}

	@PreUpdate
	public void preUpdate() {
		this.setDataAlteracao(Calendar.getInstance());
		Util.tratarAtributosString(this);
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
package br.com.getjava.cloudws.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import br.com.getjava.cloudws.enumeration.TipoUsuario;

@Entity(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String senha;
	
	@Column(name = "dt_cadastro")
	private Calendar dtCadastro;
	
	@Column(name = "dt_alteracao")
	private Calendar dtAlteracao;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;

	Usuario(){}
	
	Usuario(String email, String senha, Calendar dtCadastro, Calendar dtAlteracao,TipoUsuario tipoUsuario) {		
		this.email = email;
		this.senha = senha;
		this.dtCadastro = dtCadastro;
		this.dtAlteracao = dtAlteracao;
		this.tipoUsuario = tipoUsuario;
	}
	
	public static Usuario newInstance(String email, String senha, Calendar dtCadastro, Calendar dtAlteracao,TipoUsuario tipoUsuario) {
		return new Usuario(email,senha,dtCadastro,dtAlteracao,tipoUsuario);
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Calendar getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Calendar dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Calendar getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Calendar dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", dtCadastro=" + dtCadastro + ", dtAlteracao=" + dtAlteracao	+ ", tipoUsuario=" + tipoUsuario + "]";
	}

}

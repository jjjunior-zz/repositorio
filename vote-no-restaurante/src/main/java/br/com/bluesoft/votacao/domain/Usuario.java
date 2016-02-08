package br.com.bluesoft.votacao.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.bluesoft.votacao.util.Util;

@Table(name = "usuario")
@Entity
public class Usuario implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue
	private Long				id;

	@NotBlank
	private String				nome;

	@NotBlank
	private String				email;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_cadastro")
	private Calendar			dataCadastro;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_alteracao")
	private Calendar			dataAlteracao;

	@PrePersist
	public void prePersist() {
		this.dataCadastro = Calendar.getInstance();
		Util.tratarAtributosString(this);
	}

	@PreUpdate
	public void preUpdate() {
		this.dataAlteracao = Calendar.getInstance();
		Util.tratarAtributosString(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
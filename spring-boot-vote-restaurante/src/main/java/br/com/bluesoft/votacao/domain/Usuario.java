package br.com.bluesoft.votacao.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.bluesoft.votacao.util.Util;

@Table(name = "usuario")
@Entity
public class Usuario extends AbstractPersistable<Integer> {

	private static final long	serialVersionUID		= 1L;

	@Column(nullable = false, unique = true)
	private String				nome;

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
	
	@Override
	public void setId(Integer id) {	
		super.setId(id);
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
}
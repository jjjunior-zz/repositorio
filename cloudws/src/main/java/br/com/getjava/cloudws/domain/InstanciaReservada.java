package br.com.getjava.cloudws.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.getjava.cloudws.enumeration.Status;

@Entity
@Table(name = "instancia_reservada")
public class InstanciaReservada implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Instancia instancia;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Usuario usuario;
	
	@Enumerated(EnumType.STRING)
	private Status		status;
	
	@Column(name = "dt_cadastro")
	private Calendar dtCadastro;
	
	@Column(name = "dt_alteracao")
	private Calendar dtAlteracao;
	
	InstanciaReservada() {
	}
	
	InstanciaReservada(Instancia instancia, Usuario usuario, Status status, Calendar dtCadastro,Calendar dtAlteracao) {	
		this.instancia = instancia;
		this.usuario = usuario;
		this.status = status;
		this.dtCadastro = dtCadastro;
		this.dtAlteracao = dtAlteracao;
	}

	public static InstanciaReservada newInstance(Instancia instancia, Usuario usuario, Status status, Calendar dtCadastro,Calendar dtAlteracao) {
		return new InstanciaReservada(instancia, usuario, status, dtCadastro,	dtAlteracao) ;
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
		InstanciaReservada other = (InstanciaReservada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InstanciaReservada [id=" + id + ", instancia=" + instancia + ", usuario=" + usuario + ", status=" + status + ", dtCadastro=" + dtCadastro + ", dtAlteracao=" + dtAlteracao + "]";
	}
}

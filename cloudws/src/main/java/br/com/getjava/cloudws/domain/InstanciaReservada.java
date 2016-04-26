package br.com.getjava.cloudws.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.getjava.cloudws.enumeration.Status;

@Entity(name = "instancia_reservada")
public class InstanciaReservada implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@OneToOne
	private Instancia instancia;
	
	@OneToOne
	private Usuario usuario;
	
	@Enumerated(EnumType.STRING)
	private Status		status;
	
	@Column(name = "dt_cadastro")
	private Calendar dtCadastro;
	
	@Column(name = "dt_alteracao")
	private Calendar dtAlteracao;

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

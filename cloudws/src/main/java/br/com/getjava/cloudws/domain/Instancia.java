package br.com.getjava.cloudws.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.getjava.cloudws.domain.Cerveja.Tipo;
import br.com.getjava.cloudws.enumeration.Status;

@XmlRootElement(name = "instancia")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Instancia implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	private Integer				id;

	@Column(name = "qtd_processadores")
	private Integer				processadores;

	@Column(name = "qtd_memoria")
	private Integer				memoria;

	@Column(name = "qtd_armazenamento")
	private Integer				armazenamento;

	@Enumerated(EnumType.STRING)
	private Status		status;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	private Template template; 
	
	Instancia(){}		

	Instancia(Integer processadores, Integer memoria, Integer armazenamento, Status status, Tipo tipo, Template template) {		
		this.processadores = processadores;
		this.memoria = memoria;
		this.armazenamento = armazenamento;
		this.status = status;
		this.tipo = tipo;
		this.template = template;
	}
	
	public static Instancia newInstance(Integer processadores, Integer memoria, Integer armazenamento, Status status, Tipo tipo, Template template) {
		return new Instancia(processadores, memoria, armazenamento, status, tipo, template);
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProcessadores() {
		return processadores;
	}

	public void setProcessadores(Integer processadores) {
		this.processadores = processadores;
	}

	public Integer getMemoria() {
		return memoria;
	}

	public void setMemoria(Integer memoria) {
		this.memoria = memoria;
	}

	public Integer getArmazenamento() {
		return armazenamento;
	}

	public void setArmazenamento(Integer armazenamento) {
		this.armazenamento = armazenamento;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
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
		Instancia other = (Instancia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Instancia [processadores=" + processadores + ", memoria=" + memoria + ", armazenamento=" + armazenamento + ", status=" + status + ", tipo=" + tipo + ", template=" + template + "]";
	}
}

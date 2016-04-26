package br.com.getjava.cloudws.domain;

import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.getjava.cloudws.enumeration.TipoMaquina;

@XmlRootElement(name = "instancia")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "instancia")
public class Instancia implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 	
	private int				id;

	@Column(name = "qtd_processadores")
	private int				processador;

	@Column(name = "qtd_memoria")
	private int				memoria;

	@Column(name = "qtd_armazenamento")
	private int				armazenamento;

	@Enumerated(EnumType.STRING)
	private TipoMaquina			tipo;

	@OneToOne(cascade=CascadeType.ALL)
	private Template			template;

	Instancia() {
	}

	Instancia(Integer processador, Integer memoria, Integer armazenamento, TipoMaquina tipo, Template template) {
		this.processador = processador;
		this.memoria = memoria;
		this.armazenamento = armazenamento;
		this.tipo = tipo;
		this.template = template;
	}

	public static Instancia newInstance(Integer processador, Integer memoria, Integer armazenamento, TipoMaquina tipo, Template template) {
		return new Instancia(processador, memoria, armazenamento, tipo, template);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProcessador() {
		return processador;
	}

	public void setProcessador(int processador) {
		this.processador = processador;
	}

	public int getMemoria() {
		return memoria;
	}

	public void setMemoria(int memoria) {
		this.memoria = memoria;
	}

	public int getArmazenamento() {
		return armazenamento;
	}

	public void setArmazenamento(int armazenamento) {
		this.armazenamento = armazenamento;
	}

	public TipoMaquina getTipo() {
		return tipo;
	}

	public void setTipo(TipoMaquina tipo) {
		this.tipo = tipo;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}	

	

	@Override
	public String toString() {
		return "Instancia [id=" + id + ", processador=" + processador + ", memoria=" + memoria + ", armazenamento=" + armazenamento + ", tipo=" + tipo + ", template=" + template + "]";
	}
}

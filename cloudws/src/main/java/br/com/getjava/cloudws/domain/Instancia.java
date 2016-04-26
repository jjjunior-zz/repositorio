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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.getjava.cloudws.enumeration.Status;
import br.com.getjava.cloudws.enumeration.TipoMaquina;

@XmlRootElement(name = "instancia")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "instancia")
public class Instancia implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 	
	private int		id;	
	
	@Column
	private String			nome;

	@Column(name = "qtd_processadores")
	private int				processador;

	@Column(name = "qtd_memoria")
	private int				memoria;

	@Column(name = "qtd_armazenamento")
	private int				armazenamento;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_maquina")
	private TipoMaquina			tipo;
	
	@Enumerated(EnumType.STRING)
	private Status		status;

	@OneToOne(cascade=CascadeType.ALL)
	private Template			template;
	
	@OneToOne
	private Usuario usuario;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_cadastro")
	private Calendar dtCadastro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao")
	private Calendar dtAlteracao;	

	Instancia() {
	}

	Instancia(String nome,Integer processador, Integer memoria, Integer armazenamento, TipoMaquina tipo, Template template, Usuario usuario) {
		this.nome = nome;
		this.processador = processador;
		this.memoria = memoria;
		this.armazenamento = armazenamento;
		this.tipo = tipo;
		this.template = template;
		this.status = Status.PARADO;
		this.usuario = usuario;
	}

	public static Instancia newInstance(String nome,Integer processador, Integer memoria, Integer armazenamento, TipoMaquina tipo, Template template,Usuario usuario) {
		return new Instancia(nome,processador, memoria, armazenamento, tipo, template,usuario);
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getDtCadastro() {
		return dtCadastro;
	}	

	public Calendar getDtAlteracao() {
		return dtAlteracao;
	}	
	
	@Override
	public String toString() {
		return "Instancia [id=" + id + ", nome=" + nome + ", processador=" + processador + ", memoria=" + memoria + ", armazenamento=" + armazenamento + ", tipo=" + tipo + ", status=" + status
				+ ", template=" + template + ", usuario=" + usuario + ", dtCadastro=" + dtCadastro + ", dtAlteracao=" + dtAlteracao + "]";
	}

	@PrePersist
	public void prePersist() {
		this.dtCadastro = Calendar.getInstance();			
	}

	@PreUpdate
	public void preUpdate() {
		this.dtAlteracao = Calendar.getInstance();				
	}
}

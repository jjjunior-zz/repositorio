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
import br.com.getjava.cloudws.enumeration.CpuType;

@XmlRootElement(name = "instance")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "instance")
public class Instance implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int					id;

	@Column(name = "name")
	private String				name;

	@Column(name = "cpu")
	private int					cpu;

	@Column(name = "memory")
	private int					memory;

	@Column(name = "storage")
	private int					storage;

	@Enumerated(EnumType.STRING)
	@Column(name = "cpu_type")
	private CpuType				cpuType;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status				status;

	@OneToOne(cascade = CascadeType.ALL)	
	private Template			template;

	@OneToOne(cascade = CascadeType.ALL)
	private User				user;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_register")
	private Calendar			dtRegister;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_update")
	private Calendar			dtUpdate;

	Instance() {
	}

	public Instance(String name, int cpu, int memory, int storage, CpuType cpuType, Status status, Template template, User user) {
		this.name = name;
		this.cpu = cpu;
		this.memory = memory;
		this.storage = storage;
		this.cpuType = cpuType;
		this.status = status;
		this.template = template;
		this.user = user;
	}

	public static Instance newInstance(String name, int cpu, int memory, int storage, CpuType cpuType, Status status, Template template, User user) {
		return new Instance(name, cpu, memory, storage, cpuType, status, template, user);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public CpuType getCpuType() {
		return cpuType;
	}

	public void setCpuType(CpuType cpuType) {
		this.cpuType = cpuType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Calendar getDtRegister() {
		return dtRegister;
	}

	public Calendar getDtUpdate() {
		return dtUpdate;
	}

	@PrePersist
	public void prePersist() {
		this.dtRegister = Calendar.getInstance();
	}

	@PreUpdate
	public void preUpdate() {
		this.dtUpdate = Calendar.getInstance();
	}
}

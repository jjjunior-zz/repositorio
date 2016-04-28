package br.com.getjava.cloudws.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.getjava.cloudws.enumeration.ProcessorArchitecture;
import br.com.getjava.cloudws.enumeration.OperationalSystem;

@XmlRootElement(name = "template")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "template")
public class Template implements Serializable {

	private static final long		serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer					id;

	@Column(name = "description")
	private String					description;

	@Column(name = "application")
	private String					application;

	@Enumerated(EnumType.STRING)
	@Column(name = "operational_system")
	private OperationalSystem		operationalSystem;

	@Enumerated(EnumType.STRING)
	@Column(name = "processor_architecture")
	private ProcessorArchitecture	processorArchitecture;

	Template() {
	}

	Template(String description, String application, OperationalSystem operationalSystem, ProcessorArchitecture processorArchitecture) {
		this.description = description;
		this.application = application;
		this.operationalSystem = operationalSystem;
		this.processorArchitecture = processorArchitecture;
	}

	public static Template newInstance(String description, String application, OperationalSystem operationalSystem, ProcessorArchitecture processorArchitecture) {
		return new Template(description, application, operationalSystem, processorArchitecture);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public OperationalSystem getOperationalSystem() {
		return operationalSystem;
	}

	public void setOperationalSystem(OperationalSystem operationalSystem) {
		this.operationalSystem = operationalSystem;
	}

	public ProcessorArchitecture getProcessorArchitecture() {
		return processorArchitecture;
	}

	public void setProcessorArchitecture(ProcessorArchitecture processorArchitecture) {
		this.processorArchitecture = processorArchitecture;
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
		Template other = (Template) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Template [id=" + id + ", description=" + description + ", application=" + application + ", operationalSystem=" + operationalSystem + ", processorArchitecture=" + processorArchitecture
				+ "]";
	}
}

package br.com.getjava.cloudws.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.getjava.cloudws.enumeration.Bits;
import br.com.getjava.cloudws.enumeration.SistemaOperacional;

@XmlRootElement(name = "template")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Template implements Serializable {	

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;	
	
	private String descricao;
	
	private String aplicacoes;
	
	private SistemaOperacional sistemaOperacional;
	
	private Bits bits;		
	
	Template(){}

	Template(String descricao, String aplicacoes, SistemaOperacional sistemaOperacional, Bits bits) {		
		this.descricao = descricao;
		this.aplicacoes = aplicacoes;
		this.sistemaOperacional = sistemaOperacional;
		this.bits = bits;
	}
	
	public static Template newInstance(String descricao, String aplicacoes, SistemaOperacional sistemaOperacional, Bits bits){
		return new Template(descricao, aplicacoes, sistemaOperacional, bits);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAplicacoes() {
		return aplicacoes;
	}

	public void setAplicacoes(String aplicacoes) {
		this.aplicacoes = aplicacoes;
	}

	public SistemaOperacional getSo() {
		return sistemaOperacional;
	}

	public void setSo(SistemaOperacional sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public Bits getBits() {
		return bits;
	}

	public void setBits(Bits bits) {
		this.bits = bits;
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
		return "Template [id=" + id + ", descricao=" + descricao + ", aplicacoes=" + aplicacoes + ", sistemaOperacional=" + sistemaOperacional + ", bits=" + bits + "]";
	}	
}

package br.com.getjava.cloudws.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_grupo")
public class UserGrupo implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				id;

	@Column(name = "name")
	private String				name;	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_register")
	private Calendar			dtRegister;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_update")
	private Calendar			dtUpdate;

	@OneToMany(mappedBy = "userGrupo", targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<User>			users;

	UserGrupo() {
	}

	UserGrupo(String name, List<User> users) {
		this.name = name;
		this.users = users;
	}

	public static UserGrupo newInstance(String name, List<User> users) {
		return new UserGrupo(name, users);
	}

	public static UserGrupo newInstance(String name) {
		return new UserGrupo(name, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@PrePersist
	public void prePersist() {
		this.dtRegister = Calendar.getInstance();
	}

	@PreUpdate
	public void preUpdate() {
		this.dtUpdate = Calendar.getInstance();
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
		UserGrupo other = (UserGrupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", dtRegister=" + dtRegister + ", users=" + users + "]";
	}

}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.getjava.cloudws.enumeration.UserType;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				id;

	@Column(unique = true)
	private String				email;

	@Column(name = "password")
	private String				password;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_register")
	private Calendar			dtRegister;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_update")
	private Calendar			dtUpdate;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type")
	private UserType			userType;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private UserGrupo			userGrupo;

	User() {
	}

	User(String email, String password, UserType userType) {
		this.email = email;
		this.password = password;
		this.userType = userType;
	}

	public static User newInstance(String email, String password, UserType userType) {
		return new User(email, password, userType);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Calendar getDtRegister() {
		return dtRegister;
	}

	public Calendar getDtUpdate() {
		return dtUpdate;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserGrupo getUserGrupo() {
		return userGrupo;
	}

	public void setUserGrupo(UserGrupo userGrupo) {
		this.userGrupo = userGrupo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", dtRegister=" + dtRegister + ", dtUpdate=" + dtUpdate + ", userType=" + userType + "]";
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

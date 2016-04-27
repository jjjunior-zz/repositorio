package br.com.getjava.cloudws.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.getjava.cloudws.domain.User;

public class UserRepository extends JpaRepository<User, Integer> {

	private EntityManager entityManager;

	private UserRepository(EntityManager entityManager) {
		super(User.class);
		this.entityManager = entityManager;
	}

	public static UserRepository newInstance(EntityManager entityManager) {
		return new UserRepository(entityManager);
	}

	public void addUser(User user) {
		begin();
		persist(user);
		flush();
	}

	public User findUserByEmail(String email) {

		TypedQuery<User> query = this.entityManager.createQuery("select u from User u where u.email = :email", User.class);

		return querySingle(query, ParameterQuery.with("email", email).parameters());
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

}

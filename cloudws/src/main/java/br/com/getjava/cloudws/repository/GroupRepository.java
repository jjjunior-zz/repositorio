package br.com.getjava.cloudws.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.getjava.cloudws.domain.UserGrupo;

public class GroupRepository extends JpaRepository<UserGrupo, Integer> {

	private EntityManager entityManager;

	private GroupRepository(EntityManager entityManager) {
		super(UserGrupo.class);
		this.entityManager = entityManager;
	}

	public static GroupRepository newInstance(EntityManager entityManager) {
		return new GroupRepository(entityManager);
	}

	public void addUser(UserGrupo group) {
		begin();
		persist(group);
		flush();
	}
	
	public UserGrupo findGroupByName(String name) {

		TypedQuery<UserGrupo> query = this.entityManager.createQuery("select g from UserGrupo g where g.name = :name", UserGrupo.class);

		return querySingle(query, ParameterQuery.with("name", name).parameters());
	}


	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}

package br.com.getjava.cloudws.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.getjava.cloudws.domain.Instance;

public class InstanceRepository extends JpaRepository<Instance, Integer> {

	private EntityManager entityManager;	

	private InstanceRepository(EntityManager entityManager) {
		super(Instance.class);
		this.entityManager = entityManager;
	}

	public static InstanceRepository newInstance(EntityManager entityManager) {
		return new InstanceRepository(entityManager);
	}

	public void addInstance(Instance instancia) {
		begin();
		persist(instancia);
		flush();
	}
	
	public Instance FindInstanceByName(String name){		
		
		TypedQuery<Instance> query = this.entityManager.createQuery("select i from Instance i where i.name = :name", Instance.class) ;
		
		return querySingle(query, ParameterQuery.with("name", name).parameters());		
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}

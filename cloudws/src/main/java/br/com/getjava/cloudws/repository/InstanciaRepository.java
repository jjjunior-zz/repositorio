package br.com.getjava.cloudws.repository;

import javax.persistence.EntityManager;

import br.com.getjava.cloudws.domain.Instancia;

public class InstanciaRepository extends JpaRepository<Instancia, Integer> {	
	
	private EntityManager			entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	private InstanciaRepository(EntityManager entityManager) {
		super(Instancia.class);
		this.entityManager = entityManager;
	}
	
	public static InstanciaRepository newInstance(EntityManager entityManager) {		
		return new InstanciaRepository(entityManager);
	}
}

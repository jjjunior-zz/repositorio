package br.com.getjava.cloudws.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.getjava.cloudws.domain.Instancia;

public class InstanciaRepository extends JpaRepository<Instancia, Integer> {

	private EntityManager entityManager;	

	private InstanciaRepository(EntityManager entityManager) {
		super(Instancia.class);
		this.entityManager = entityManager;
	}

	public static InstanciaRepository newInstance(EntityManager entityManager) {
		return new InstanciaRepository(entityManager);
	}

	public void incluirInstancia(Instancia instancia) {
		begin();
		persist(instancia);
		flush();
	}
	
	public Instancia buscarIntanciaPorNome(String nome){		
		
		TypedQuery<Instancia> query = this.entityManager.createQuery("select i from Instancia i where i.nome = :nome", Instancia.class) ;
		
		return querySingle(query, ParametroQuery.with("nome", nome).parametros());		
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}

package br.com.getjava.cloudws.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaConnection {

	private static EntityManagerFactory emf = null;

	private JpaConnection() {}

	public static EntityManager getInstance(String persistenceUnit ) {
		if (emf == null) {
			synchronized (JpaConnection.class) {
				if (emf == null)
					emf = Persistence.createEntityManagerFactory(persistenceUnit);
			}
		}
		return emf.createEntityManager();
	}
}

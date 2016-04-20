package br.com.getjava.cloudws.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class SingletonEntityManager {

	private static EntityManagerFactory emf = null;

	private SingletonEntityManager() {}

	public static EntityManager getInstance() {
		if (emf == null) {
			synchronized (SingletonEntityManager.class) {
				if (emf == null)
					emf = Persistence.createEntityManagerFactory("cloudwsbd");
			}
		}
		return emf.createEntityManager();
	}
}

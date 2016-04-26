package br.com.getjava.cloudws.repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import br.com.getjava.cloudws.exception.ModeloException;

abstract class JpaRepository<T, ID> {

	private Class<T> entityClass;

	public JpaRepository(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public void persist(T entity) {
		try {
			this.getEntityManager().persist(entity);
		} catch (Exception e) {
			mensagemException("Problema ao persistir entidade :" + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
	}

	public void merge(T entity) {
		try {
			this.getEntityManager().merge(entity);
		} catch (Exception e) {
			mensagemException("Problema ao realizar merge entidade :" + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
	}

	public void remove(T entity) {
		try {
			this.getEntityManager().remove(this.getEntityManager().merge(entity));
		} catch (Exception e) {
			mensagemException("Problema ao remover entidade :" + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
	}

	public T find(ID id) {
		try {
			return getEntityManager().find(this.entityClass, id);
		} catch (Exception e) {
			mensagemException("Problema ao consultar:" + " find(ID id) da entidade: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
		return null;
	}

	public T querySingle(String query) {
		try {
			return this.querySingle(this.getEntityManager().createQuery(query, entityClass));
		} catch (Exception e) {
			mensagemException("Problema ao consultar:" + " querySingle(String query) da entidade: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
		return null;
	}

	public T querySingle(TypedQuery<T> query, Map<String, Object> parametros) {
		try {
			query = setParametros(parametros, query);
			return query.getSingleResult();
		} catch (Exception e) {
			mensagemException("Problema ao consultar:" + " querySingle(TypedQuery<T> query,Map<String, Object> parametros) da entidade: " + this.entityClass.getName() + ".", e);
		}
		return null;
	}

	public T querySingle(TypedQuery<T> query) {
		try {
			return querySingle(query, null);
		} catch (Exception e) {
			mensagemException("Problema ao consultar:" + " querySingle(TypedQuery<T> query) da entidade: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
		return null;
	}

	public List<T> queryList(String query) {
		try {
			return this.queryList(this.getEntityManager().createQuery(query, entityClass));
		} catch (Exception e) {
			mensagemException("Problema ao consultar:" + " queryList(String query) da entidade: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
		return null;
	}

	public List<T> queryList(TypedQuery<T> query) {
		List<T> lista = null;
		try {
			return queryList(query, null);
		} catch (Exception e) {
			mensagemException("Problema ao consultar:" + " queryList(TypedQuery<T> query) da entidade: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}

		if (lista == null)
			lista = Collections.emptyList();
		return lista;

	}

	public List<T> queryList(TypedQuery<T> query, Map<String, Object> parametros) {
		List<T> lista = null;
		try {
			query = setParametros(parametros, query);
			return query.getResultList();
		} catch (Exception e) {
			mensagemException("Problema ao consultar:" + " queryList(TypedQuery<T> query,Map<String, Object> parametros) da entidade: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}

		if (lista == null)
			lista = Collections.emptyList();
		return lista;

	}

	public void flush() {
		try {
			this.getEntityManager().flush();
		} catch (Exception e) {
			mensagemException("Problema ao realizar flush classe: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
	}

	public void clear() {
		try {
			this.getEntityManager().clear();
		} catch (Exception e) {
			mensagemException("Problema ao realizar clear classe: " + this.entityClass.getName() + ".", e);
		}
	}

	public void begin() {
		try {
			this.getEntityManager().getTransaction().begin();
		} catch (Exception e) {
			mensagemException("Problema ao realizar begin classe: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
	}

	public void commit() {
		try {
			this.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			mensagemException("Problema ao realizar commit classe: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
	}

	public void rollback() {
		try {
			this.getEntityManager().getTransaction().rollback();
		} catch (Exception e) {
			mensagemException("Problema ao realizar rollback classe: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
	}

	public List<T> findAll() {
		try {
			CriteriaQuery<T> criteriaQuery = this.getEntityManager().getCriteriaBuilder().createQuery(entityClass);
			criteriaQuery.select(criteriaQuery.from(this.entityClass));
			return this.getEntityManager().createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			mensagemException("Problema ao realizar findAll classe: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
		return null;
	}

	public List<T> findRange(int[] range) {
		try {
			CriteriaQuery<T> criteriaQuery = this.getEntityManager().getCriteriaBuilder().createQuery(entityClass);
			criteriaQuery.select(criteriaQuery.from(this.entityClass));
			TypedQuery<T> query = this.getEntityManager().createQuery(criteriaQuery);
			query.setMaxResults(range[1] - range[0]);
			query.setFirstResult(range[0]);
			return query.getResultList();
		} catch (Exception e) {
			mensagemException("Problema ao realizar findRange classe: " + this.entityClass.getName() + ".", e);
			e.printStackTrace();
		}
		return null;
	}

	private TypedQuery<T> setParametros(Map<String, Object> parametros, TypedQuery<T> typedQuery) {
		if (parametros != null) {
			Set<Entry<String, Object>> lParam = parametros.entrySet();
			for (Entry<String, Object> entry : lParam) {
				typedQuery.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return typedQuery;
	}

	private void mensagemException(String mensagem, Exception e) throws ModeloException {
		Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, mensagem, e);
		throw new ModeloException(mensagem, e);
	}
}

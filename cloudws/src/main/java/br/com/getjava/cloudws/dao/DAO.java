package br.com.getjava.cloudws.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import br.com.getjava.cloudws.exception.ModeloException;

public class DAO<T, K> {

	private final EntityManager	em;
	private final Class<T>		classe;

	private DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}

	public static <T, K> DAO<T, K> newInstance(EntityManager em, Class<T> classe) {
		return new DAO<T, K>(em, classe);
	}

	public T select(K indice) {
		T t = null;
		try {
			t = this.em.find(this.classe, indice);
		} catch (EntityNotFoundException e) {
			mensagemLog("Entidade: " + this.classe.getName() + " nao encontrada.", e);
		} catch (NonUniqueResultException e) {
			mensagemLog("Mais de um registro encontrado para a Entidade : " + this.classe.getName() + ".", e);
		} catch (NoResultException e) {
			return t;
		} catch (IllegalStateException e) {
			mensagemLog("Entidade em status inconsistente: " + this.classe.getName() + ".", e);
		} catch (QueryTimeoutException e) {
			mensagemLog("Tempo da consulta excedido para a Entidade : " + this.classe.getName() + ".", e);
		} catch (PersistenceException e) {
			mensagemLog("Problema na consulta da Entidade : " + this.classe.getName() + ".", e);
		} catch (Exception e) {
			mensagemLog("Erro inexperado ao realizar a consulta da Entidade : " + this.classe.getName() + ".", e);
		}
		return t;
	}

	public T selectUnicoResultado(String nomeQuery) {
		return selectUnicoResultado(nomeQuery, null);
	}

	@SuppressWarnings("unchecked")
	public T selectUnicoResultado(String nomeQuery, Map<String, Object> parametros) {
		T t = null;
		try {
			Query query = this.em.createNamedQuery(nomeQuery);
			query = setParametros(parametros, query);
			t = (T) query.getSingleResult();
		} catch (EntityNotFoundException e) {
			mensagemLog("Entidade: " + this.classe.getName() + " nao encontrada.", e);
		} catch (NonUniqueResultException e) {
			mensagemLog("Mais de um registro encontrado para a Entidade : " + this.classe.getName() + ".", e);
		} catch (NoResultException e) {
			return t;
		} catch (IllegalStateException e) {
			mensagemLog("Entidade em status inconsistente: " + this.classe.getName() + ".", e);
		} catch (QueryTimeoutException e) {
			mensagemLog("Tempo da consulta excedido para a Entidade : " + this.classe.getName() + ".", e);
		} catch (PersistenceException e) {
			mensagemLog("Problema na consulta da Entidade : " + this.classe.getName() + ".", e);
		} catch (Exception e) {
			mensagemLog("Erro inexperado ao realizar a consulta da Entidade : " + this.classe.getName() + ".", e);
		}
		return t;
	}

	public T selectNativoUnicoResultado(String nomeQuery) {
		return selectNativoUnicoResultado(nomeQuery, null);
	}

	@SuppressWarnings("unchecked")
	public T selectNativoUnicoResultado(String nomeQuery, Map<String, Object> parametros) {
		T t = null;
		try {
			Query query = this.em.createNativeQuery(nomeQuery);
			query = setParametros(parametros, query);
			t = (T) query.getSingleResult();
		} catch (EntityNotFoundException e) {
			mensagemLog("Entidade: " + this.classe.getName() + " nao encontrada.", e);
		} catch (NonUniqueResultException e) {
			mensagemLog("Mais de um registro encontrado para a Entidade : " + this.classe.getName() + ".", e);
		} catch (NoResultException e) {
			return t;
		} catch (IllegalStateException e) {
			mensagemLog("Entidade em status inconsistente: " + this.classe.getName() + ".", e);
		} catch (QueryTimeoutException e) {
			mensagemLog("Tempo da consulta excedido para a Entidade : " + this.classe.getName() + ".", e);
		} catch (PersistenceException e) {
			mensagemLog("Problema na consulta da Entidade : " + this.classe.getName() + ".", e);
		} catch (Exception e) {
			mensagemLog("Erro inexperado ao realizar a consulta da Entidade : " + this.classe.getName() + ".", e);
		}
		return t;
	}

	public List<T> select(String nomeQuery, int... maxLinhas) {
		return select(nomeQuery, null, maxLinhas);
	}

	@SuppressWarnings("unchecked")
	public List<T> select(String nomeQuery, Map<String, Object> parametros, int... maxLinhas) {
		List<T> lista = null;
		Query query = this.em.createNamedQuery(nomeQuery);
		query = setParametros(parametros, query);
		try {
			lista = maxLinhas.length > 0 ? query.setMaxResults(maxLinhas[0]).getResultList() : query.getResultList();
		} catch (EntityNotFoundException e) {
			mensagemLog("Entidade: " + this.classe.getName() + " nao encontrada.", e);
		} catch (NonUniqueResultException e) {
			mensagemLog("Mais de um registro encontrado para a Entidade : " + this.classe.getName() + ".", e);
		} catch (NoResultException e) {
			return Collections.emptyList();
		} catch (IllegalStateException e) {
			mensagemLog("Entidade em status inconsistente: " + this.classe.getName() + ".", e);
		} catch (QueryTimeoutException e) {
			mensagemLog("Tempo da consulta excedido para a Entidade : " + this.classe.getName() + ".", e);
		} catch (PersistenceException e) {
			mensagemLog("Problema na consulta da Entidade : " + this.classe.getName() + ".", e);
		} catch (Exception e) {
			mensagemLog("Erro inexperado ao realizar a consulta da Entidade : " + this.classe.getName() + ".", e);
		}

		if (lista == null)
			lista = Collections.emptyList();

		return lista;
	}

	public List<T> select(TypedQuery<T> typedQuery, Map<String, Object> parametros) {
		List<T> lista = null;
		typedQuery = setParametros(parametros, typedQuery);
		try {
			lista = typedQuery.getResultList();
		} catch (EntityNotFoundException e) {
			mensagemLog("Entidade: " + this.classe.getName() + " nao encontrada.", e);
		} catch (NonUniqueResultException e) {
			mensagemLog("Mais de um registro encontrado para a Entidade : " + this.classe.getName() + ".", e);
		} catch (NoResultException e) {
			return Collections.emptyList();
		} catch (IllegalStateException e) {
			mensagemLog("Entidade em status inconsistente: " + this.classe.getName() + ".", e);
		} catch (QueryTimeoutException e) {
			mensagemLog("Tempo da consulta excedido para a Entidade : " + this.classe.getName() + ".", e);
		} catch (PersistenceException e) {
			mensagemLog("Problema na consulta da Entidade : " + this.classe.getName() + ".", e);
		} catch (Exception e) {
			mensagemLog("Erro inexperado ao realizar a consulta da Entidade : " + this.classe.getName() + ".", e);
		}

		if (lista == null)
			lista = Collections.emptyList();
		return lista;
	}

	public void alterar(String nomeQuery, Map<String, Object> parametros) throws ModeloException {
		try {
			Query query = this.em.createQuery(nomeQuery);
			query = setParametros(parametros, query);
			query.executeUpdate();
		} catch (IllegalStateException e) {
			mensagemException("Entidade em status inconsistente ao relizar o update da entidade: " + this.classe.getName() + ".", e);
		} catch (TransactionRequiredException e) {
			mensagemException("Problema transional ao relizar o update na entidade: " + this.classe.getName() + ".", e);
		} catch (PersistenceException e) {
			mensagemException("Problema ao atualizar a entidade : " + this.classe.getName() + ".", e);
		} catch (Exception e) {
			mensagemException("Erro inexperado ao tentar atualizar a entidade : " + this.classe.getName() + ".", e);
		}
	}

	public void alterar(T t) throws ModeloException {
		try {
			this.em.merge(t);
		} catch (IllegalStateException e) {
			mensagemException("Entidade em status inconsistente ao realizar o merge: " + this.classe.getName() + ".", e);
		} catch (TransactionRequiredException e) {
			mensagemException("Problema transional na entidade ao realizar o merge: " + this.classe.getName() + ".", e);
		} catch (PersistenceException e) {
			mensagemException("Problema ao realizar o merge da entidade : " + this.classe.getName() + ".", e);
		} catch (Exception e) {
			mensagemException("Erro inexperado ao tentar realizar o merge da entidade : " + this.classe.getName() + ".", e);
		}
	}

	public void inserir(T t) throws ModeloException {
		try {
			this.em.persist(t);
		} catch (EntityExistsException e) {
			mensagemException("Viola��o da Chave primaria para a entidade :" + this.classe.getName() + ".", e);
		} catch (PersistenceException e) {
			mensagemException("Problema ao tentar persistir entidade :" + this.classe.getName() + ".", e);
		} catch (Exception e) {
			mensagemException("Erro inexperado ao tentar persistir entidade : " + this.classe.getName() + ".", e);
		}
	}

	public void deletar(T t) throws ModeloException {
		try {
			t = this.em.merge(t);
			this.em.remove(t);
		} catch (PersistenceException e) {
			mensagemException("Problema ao tentar deletar entidade :" + this.classe.getName() + ".", e);
		} catch (Exception e) {
			mensagemException("Erro inexperado ao tentar deletar entidade : " + this.classe.getName() + ".", e);
		}
	}

	public void executarProcedure(String nomeProcedure, Map<String, Object> parametros) throws ModeloException {
		Set<Entry<String, Object>> lParam = parametros.entrySet();

		Query query = this.em.createNamedQuery(nomeProcedure);

		for (Entry<String, Object> entry : lParam) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		query.executeUpdate();
	}

	public List<?> executarProcedureComResultadoGenerico(String nomeProcedure, Map<String, Object> parametros) throws ModeloException {
		Set<Entry<String, Object>> lParam = parametros.entrySet();

		Query query = this.em.createNamedQuery(nomeProcedure);

		for (Entry<String, Object> entry : lParam) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.getResultList();
	}

	public Object selectUnicoResultadoGenerico(String nomeQuery, Map<String, Object> parametros) {
		Query query = this.em.createNamedQuery(nomeQuery);
		query = setParametros(parametros, query);
		return query.getSingleResult();
	}

	public List<?> selectRetornoListaGenerica(String nomeQuery, Map<String, Object> parametros, int... maxLinhas) {
		Query query = this.em.createNamedQuery(nomeQuery);
		query = setParametros(parametros, query);
		return maxLinhas.length > 0 ? query.setMaxResults(maxLinhas[0]).getResultList() : query.getResultList();
	}

	private void mensagemException(String mensagem, Exception e) throws ModeloException {
		Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, mensagem, e);
		throw new ModeloException(mensagem, e);
	}

	private void mensagemLog(String mensagem, Exception e) {
		Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, mensagem, e);
	}

	private Query setParametros(Map<String, Object> parametros, Query query) {
		if (parametros != null) {
			Set<Entry<String, Object>> lParam = parametros.entrySet();
			for (Entry<String, Object> entry : lParam) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
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
}
package br.com.bluesoft.votacao.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

import org.apache.log4j.Logger;

import br.com.bluesoft.votacao.exception.ModeloException;

public class DAO<T, K> {

	final static Logger logger = Logger.getLogger(DAO.class);
	private final EntityManager entityManager;
	private final Class<T> classe;

	private DAO(EntityManager entityManager, Class<T> classe) {
		this.entityManager = entityManager;
		this.classe = classe;
	}

	public static <T, K> DAO<T, K> newInstance(EntityManager entityManager, Class<T> classe) {
		return new DAO<T, K>(entityManager, classe);
	}

	public T selecionarPeloIndice(K indice) {
		T t = null;
		try {
			t = this.entityManager.find(this.classe, indice);
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

	public T selecionarUnicoResultado(String nomeQuery) {
		return selecionarUnicoResultadoComParametro(nomeQuery, null);
	}

	public T selecionarUnicoResultadoComParametro(String nomeQuery, Map<String, Object> parametros) {
		T t = null;
		try {
			TypedQuery<T> query = this.entityManager.createNamedQuery(nomeQuery,classe);
			query = setParametros(parametros, query);
			t = query.getSingleResult();
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

	public T selecionarComQueryNativa(String query) {
		return selecionarComQueryNativaEParametro(query, null);
	}

	public T selecionarComQueryNativaEParametro(String query, Map<String, Object> parametros) {
		T t = null;
		try {
			@SuppressWarnings("unchecked")
			TypedQuery<T> typedQuery  = (TypedQuery<T>) this.entityManager.createNativeQuery(query,classe);
			typedQuery = setParametros(parametros, typedQuery);
			t = typedQuery.getSingleResult();
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

	public List<T> selecionar(String nomeQuery, int... maxLinhas) {
		return selecionarComParametro(nomeQuery, null, maxLinhas);
	}

	public List<T> selecionarComParametro(String nomeQuery, Map<String, Object> parametros, int... maxLinhas) {
		List<T> lista = null;
		TypedQuery<T> query = this.entityManager.createNamedQuery(nomeQuery,classe);
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

		if(lista ==null) lista = Collections.emptyList();

		return lista;
	}	

	public void alterar(String update, Map<String, Object> parametros) throws ModeloException {
		try {
			Query query = this.entityManager.createQuery(update);
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
			this.entityManager.merge(t);			
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
			this.entityManager.persist(t);	
			this.entityManager.flush();
		} catch (EntityExistsException e) {
			mensagemException("Violação da Chave primaria para a entidade :" + this.classe.getName() + ".", e);
		} catch (PersistenceException e) {
			mensagemException("Problema ao tentar persistir entidade :" + this.classe.getName() + ".", e);
		} catch (Exception e) {
			mensagemException("Erro inexperado ao tentar persistir entidade : " + this.classe.getName() + ".", e);
		}
	}

	public void deletar(T t) throws ModeloException {
		try {
			t = this.entityManager.merge(t);
			this.entityManager.remove(t);
			this.entityManager.flush();
		} catch (PersistenceException e) {
			mensagemException("Problema ao tentar deletar entidade :" + this.classe.getName() + ".", e);
		} catch (Exception e) {
			mensagemException("Erro inexperado ao tentar deletar entidade : " + this.classe.getName() + ".", e);
		}
	}

	private void mensagemException(String mensagem, Exception e) throws ModeloException {
		logger.error(mensagem, e);
		throw new ModeloException(mensagem, e);
	}

	private void mensagemLog(String mensagem, Exception e) {
		logger.error(mensagem, e);		
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
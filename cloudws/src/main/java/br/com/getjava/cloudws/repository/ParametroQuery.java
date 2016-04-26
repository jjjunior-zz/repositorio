package br.com.getjava.cloudws.repository;

import java.util.HashMap;
import java.util.Map;

public class ParametroQuery {

	private Map<String, Object>	parametros	= null;

	private ParametroQuery(String nome, Object valor) {
		this.parametros = new HashMap<String, Object>();
		this.parametros.put(nome, valor);
	}

	public static ParametroQuery with(String nome, Object valor) {
		return new ParametroQuery(nome, valor);
	}

	public ParametroQuery and(String nome, Object valor) {
		this.parametros.put(nome, valor);
		return this;
	}

	public Map<String, Object> parametros() {
		return this.parametros;
	}
}
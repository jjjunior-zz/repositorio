package br.com.getjava.cloudws.dao;

import java.util.HashMap;
import java.util.Map;

public class ParametroDaConsulta {

	private Map<String, Object>	parametros	= null;

	private ParametroDaConsulta(String nome, Object valor) {
		this.parametros = new HashMap<String, Object>();
		this.parametros.put(nome, valor);
	}

	public static ParametroDaConsulta with(String nome, Object valor) {
		return new ParametroDaConsulta(nome, valor);
	}

	public ParametroDaConsulta and(String nome, Object valor) {
		this.parametros.put(nome, valor);
		return this;
	}

	public Map<String, Object> parametros() {
		return this.parametros;
	}
}
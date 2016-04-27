package br.com.getjava.cloudws.repository;

import java.util.HashMap;
import java.util.Map;

public class ParameterQuery {

	private Map<String, Object>	parameters	= null;

	private ParameterQuery(String name, Object value) {
		this.parameters = new HashMap<String, Object>();
		this.parameters.put(name, value);
	}

	public static ParameterQuery with(String name, Object value) {
		return new ParameterQuery(name, value);
	}

	public ParameterQuery and(String name, Object value) {
		this.parameters.put(name, value);
		return this;
	}

	public Map<String, Object> parameters() {
		return this.parameters;
	}
}
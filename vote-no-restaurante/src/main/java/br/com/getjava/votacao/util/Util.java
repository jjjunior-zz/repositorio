package br.com.getjava.votacao.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import br.com.getjava.votacao.exception.UtilException;

public class Util {
	
	final static Logger logger = Logger.getLogger(Util.class);

	static int quantidadeDeVotacao(final int quantidadeTotalRestaurantes, final int quantidadeDeRestaurantesPorVotacao) {

		if (quantidadeTotalRestaurantes < 2) {
			throw new UtilException("Não é possível determinar quantidade de votações, com a quantidade de total de restaurantes menor que 2.");
		} else if (quantidadeTotalRestaurantes > 5) {
			throw new UtilException("Quantidade total maxima de restaurantes é 5.");
		} else if (quantidadeDeRestaurantesPorVotacao > 2) {
			throw new UtilException("Quantidade maxima de restaurantes por votação é 2.");
		}

		int resultado = 1;
		for (int k = 0; k < quantidadeDeRestaurantesPorVotacao; k++) {
			resultado = resultado * (quantidadeTotalRestaurantes - k) / (k + 1);
		}
		return resultado;
	}

	@SuppressWarnings("rawtypes")
	public static void tratarAtributosString(Object obj) {
		try {
			Method[] method = obj.getClass().getMethods();
			Class<? extends Object> classObj = obj.getClass();
			Object[] varNull = null;
			Class[] classNull = null;

			for (Method element : method) {

				if ((element.getName().startsWith("get") && element.getName().indexOf("getClass") == -1) && !element.getName().equals("getId")) {

					Class[] type = { element.getReturnType() };
					Object[] param = { element.invoke(obj, varNull) };

					if (param[0] != null && param[0] instanceof String) {

						param[0] = param[0].toString();
						param[0] = retirarAcentuacao(param[0].toString().trim());

						type[0] = classObj.getMethod(element.getName(), classNull).getReturnType();
						Method m = classObj.getMethod(element.getName().replaceAll("get", "set"), type);

						if (m != null) {
							m.invoke(obj, param);
						}
					}
				}
			}
		} catch (IllegalAccessException e) {
			logger.error("Erro ao tratar string.IllegalAccessException: ", e);
			throw new UtilException(e);
		} catch (InvocationTargetException e) {
			logger.error("Erro ao tratar string.InvocationTargetException: ", e);
			throw new UtilException(e);
		} catch (NoSuchMethodException e) {
			logger.error("Erro ao tratar string.NoSuchMethodException: ", e);
			throw new UtilException(e);
		}
	}

	public static String retirarAcentuacao(String s) {
		String resultado = "";
		if (s != null) {
			s = Normalizer.normalize(s, Normalizer.Form.NFD);
			s = s.replaceAll("[^\\p{ASCII}]", "");
			Pattern pattern = Pattern.compile("[@ . a-zA-Z0-9\\s]*");
			Matcher matcher = pattern.matcher(s);
			while (matcher.find()) {
				resultado += matcher.group();
			}
		}
		return resultado;
	}

}

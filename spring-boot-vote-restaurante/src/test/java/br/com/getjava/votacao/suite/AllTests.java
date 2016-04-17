package br.com.getjava.votacao.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.getjava.votacao.classificacao.CalculadoraDeClassificacaoTest;
import br.com.getjava.votacao.repository.ClassificacaoRestauranteIntegracaoTest;
import br.com.getjava.votacao.repository.PossivelEscolhaIntegracaoTest;
import br.com.getjava.votacao.repository.RestauranteIntegracaoTest;
import br.com.getjava.votacao.repository.UsuarioIntegracaoTest;
import br.com.getjava.votacao.util.UtilTest;

@RunWith(Suite.class)
@SuiteClasses({ PossivelEscolhaIntegracaoTest.class,
				RestauranteIntegracaoTest.class,
				UsuarioIntegracaoTest.class,
				CalculadoraDeClassificacaoTest.class,
				ClassificacaoRestauranteIntegracaoTest.class,
				UtilTest.class
			 })
public class AllTests {

}

package br.com.bluesoft.votacao.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.bluesoft.votacao.classificacao.CalculadoraDeClassificacaoTest;
import br.com.bluesoft.votacao.repository.ClassificacaoRestauranteIntegracaoTest;
import br.com.bluesoft.votacao.repository.PossivelEscolhaIntegracaoTest;
import br.com.bluesoft.votacao.repository.RestauranteIntegracaoTest;
import br.com.bluesoft.votacao.repository.UsuarioIntegracaoTest;
import br.com.bluesoft.votacao.util.UtilTest;

@RunWith(Suite.class)
@SuiteClasses({ PossivelEscolhaIntegracaoTest.class,RestauranteIntegracaoTest.class, UsuarioIntegracaoTest.class,CalculadoraDeClassificacaoTest.class,ClassificacaoRestauranteIntegracaoTest.class, UtilTest.class })
public class AllTests {

}

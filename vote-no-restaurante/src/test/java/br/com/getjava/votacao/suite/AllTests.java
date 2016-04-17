package br.com.getjava.votacao.suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.getava.votacao.repository.RestauranteRepositoryTest;
import br.com.getava.votacao.repository.UsuarioRepositoryTest;
import br.com.getjava.votacao.util.UtilTest;

@RunWith(Suite.class)
@SuiteClasses({RestauranteRepositoryTest.class, UsuarioRepositoryTest.class, UtilTest.class})
public class AllTests {

}

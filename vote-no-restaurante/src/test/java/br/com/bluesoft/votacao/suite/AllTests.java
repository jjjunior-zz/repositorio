package br.com.bluesoft.votacao.suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.bluesoft.votacao.repository.RestauranteRepositoryTest;
import br.com.bluesoft.votacao.repository.UsuarioRepositoryTest;
import br.com.bluesoft.votacao.util.UtilTest;

@RunWith(Suite.class)
@SuiteClasses({RestauranteRepositoryTest.class, UsuarioRepositoryTest.class, UtilTest.class})
public class AllTests {

}

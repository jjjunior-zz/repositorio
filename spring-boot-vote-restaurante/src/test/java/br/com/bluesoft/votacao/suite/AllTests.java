package br.com.bluesoft.votacao.suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.bluesoft.votacao.service.PossivelEscolhaServiceTest;
import br.com.bluesoft.votacao.service.RestauranteServiceTest;
import br.com.bluesoft.votacao.service.UsuarioServiceTest;
import br.com.bluesoft.votacao.util.UtilTest;

@RunWith(Suite.class)
@SuiteClasses({RestauranteServiceTest.class, UsuarioServiceTest.class,PossivelEscolhaServiceTest.class, UtilTest.class})
public class AllTests {

}

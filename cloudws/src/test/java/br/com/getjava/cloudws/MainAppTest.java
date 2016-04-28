package br.com.getjava.cloudws;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.getjava.cloudws.repository.InstanceRepositoryTest;
import br.com.getjava.cloudws.repository.UserRepositoryTest;

@RunWith(Suite.class)
@SuiteClasses({UserRepositoryTest.class,InstanceRepositoryTest.class})
public class MainAppTest{
}

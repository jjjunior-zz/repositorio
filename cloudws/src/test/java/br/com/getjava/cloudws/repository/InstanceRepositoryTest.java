package br.com.getjava.cloudws.repository;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.getjava.cloudws.domain.Instance;
import br.com.getjava.cloudws.domain.Template;
import br.com.getjava.cloudws.domain.User;
import br.com.getjava.cloudws.enumeration.ProcessorArchitecture;
import br.com.getjava.cloudws.enumeration.Status;
import br.com.getjava.cloudws.enumeration.OperationalSystem;
import br.com.getjava.cloudws.enumeration.CpuType;
import br.com.getjava.cloudws.enumeration.UserType;

public class InstanceRepositoryTest {

	private InstanceRepository	instanceRepository;
	private UserRepository		userRepository;

	@Before
	public void before() {
		instanceRepository = InstanceRepository.newInstance(JpaConnection.getInstance("cloudwstest"));
		userRepository = UserRepository.newInstance(JpaConnection.getInstance("cloudwstest"));
	}

	@After
	public void after() {		
		instanceRepository.getEntityManager().close();
		userRepository.getEntityManager().close();		
	}

	@Test
	public void validateAddInstanceWhenFindUserByEmail() {
		User user = User.newInstance("jjjunior@gmail.com", "123456", UserType.ROOT);
		userRepository.addUser(user);
		userRepository.commit();
		
		Template template = Template.newInstance("teste", "test2", OperationalSystem.LINUX, ProcessorArchitecture.bit64);
		User userFind = userRepository.findUserByEmail(user.getEmail());
		Instance instance = Instance.newInstance("teste", 2, 2, 2, CpuType.MICRO, Status.STOP, template, userFind);
		
		instanceRepository.addInstance(instance);
		instanceRepository.commit();
		
		Instance instance1 = instanceRepository.FindInstanceByName("teste");

		assertEquals(instance.getId(), instance1.getId());
		assertEquals(instance.getName(), instance1.getName());
		assertEquals(template.getId(), instance1.getTemplate().getId());

	}

}

package br.com.getjava.cloudws.repository;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.getjava.cloudws.domain.User;
import br.com.getjava.cloudws.enumeration.UserType;

public class UserRepositoryTest {	
	
	private UserRepository userRepository;
	
	@Before
	public void before() {				
		userRepository = UserRepository.newInstance(JpaConnection.getInstance("cloudwstest"));		
	}

	@After
	public void after() {		
		userRepository.rollback();
		userRepository.getEntityManager().close();
	}

	@Test
	public void validateAddUser() {	
		User usuario = User.newInstance("jjjunior@gmail.com", "123456", UserType.ROOT);
		userRepository.addUser(usuario);	
		
		User usuario1 = userRepository.findUserByEmail(usuario.getEmail());		
				
		assertEquals(usuario.getId(), usuario1.getId());
		assertEquals(usuario.getEmail(), usuario1.getEmail());		
	}

}

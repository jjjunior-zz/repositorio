package br.com.getjava.cloudws.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.getjava.cloudws.domain.User;
import br.com.getjava.cloudws.domain.UserGrupo;
import br.com.getjava.cloudws.enumeration.UserType;

public class UserRepositoryTest {	
	
	private UserRepository userRepository;
	private GroupRepository groupRepository;
	
	@Before
	public void before() {				
		userRepository = UserRepository.newInstance(JpaConnection.getInstance("cloudwstest"));		
		groupRepository = GroupRepository.newInstance(JpaConnection.getInstance("cloudwstest"));		
	}

	@After
	public void after() {
		userRepository.getEntityManager().close();
		groupRepository.getEntityManager().close();
	}

	@Test
	public void validateAddUser() {	
		User user1 = User.newInstance("jjjunior@gmail.com", "123456", UserType.ROOT);
		userRepository.begin();
		userRepository.addUser(user1);
		userRepository.commit();
		
		User user2 = userRepository.findUserByEmail(user1.getEmail());
				
		assertEquals(user1.getId(), user2.getId());
		assertEquals(user1.getEmail(), user2.getEmail());		
	}
	
	@Test
	public void validateAddUserAndGroup() {	
		User user1 = User.newInstance("jjjunior10@gmail.com", "123456", UserType.ROOT);
		User user2 = User.newInstance("jjjunior11@gmail.com", "123456", UserType.ROOT);
		userRepository.begin();
		userRepository.persist(user1);
		userRepository.persist(user2);
		userRepository.commit();		
		
		user1 = userRepository.findUserByEmail(user1.getEmail());
		user2 = userRepository.findUserByEmail(user2.getEmail());
		
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		
		UserGrupo group = UserGrupo.newInstance("test3");
		group.setUsers(users);
		
		groupRepository.begin();		
		groupRepository.persist(group);
		groupRepository.commit();
		
		UserGrupo groupConsultado = groupRepository.findGroupByName(group.getName());				
		
		assertEquals(group.getName(), groupConsultado.getName());
		assertEquals(group.getId(), groupConsultado.getId());
	}

}

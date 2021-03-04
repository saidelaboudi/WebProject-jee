package ensias.teams.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ensias.teams.buzinessLayer.ChatPersoUser;
import ensias.teams.buzinessLayer.Message;
import ensias.teams.buzinessLayer.User;
import ensias.teams.dao.DAOFactory;

public class UserDaoImplTest {
	DAOFactory daof;
	@Before
	public void setUp() throws Exception { 
		daof = DAOFactory.getInstanceTest();
	}

	@Test
	public void testBringUserById() {
		User user =daof.getUserDao().bringUser(1);
		assertEquals("falehyasser@gmail.com", user.email);
		assertEquals("Yasser", user.firstName);
		assertEquals("Faleh", user.lastName);
		assertEquals("yasser", user.password);
		
	}
	@Test
	public void testBringMessagerie() {
		User user =daof.getUserDao().bringUser(1);
		List<User> users =daof.getUserDao().bringMessagerie(user);
		assertEquals("Said", users.get(0).firstName);
		assertEquals("Amine", users.get(1).firstName);
		
	}
	@Test
	public void testBringAllMessagerie() {
		User user =daof.getUserDao().bringUser(1);
		List<ChatPersoUser> users = daof.getUserDao().bringAllMessagerie(user);
		assertEquals(users.size() , 2);
		
	}
	

}

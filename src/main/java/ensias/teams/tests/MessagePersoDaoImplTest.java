package ensias.teams.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ensias.teams.buzinessLayer.User;
import ensias.teams.dao.DAOFactory;

public class MessagePersoDaoImplTest {
	DAOFactory daof;
	@Before
	public void setUp() throws Exception { 
		daof = DAOFactory.getInstanceTest();
	}

	@Test
	public void testInsertMessagePerso() {
		try {
			daof.getMessagePersoDao().insertMessagePerso(1l, 3l, "SSSS");
		} catch (Exception e) {
			fail();
		}
		
	}
}

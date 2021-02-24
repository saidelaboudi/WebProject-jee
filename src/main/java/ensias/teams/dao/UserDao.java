package ensias.teams.dao;

import java.util.ArrayList;

import ensias.teams.buzinessLayer.User;

public interface UserDao {

	ArrayList<User> bringAllUsers();
	void addUser(User user);
	User bringUser(String email);

}

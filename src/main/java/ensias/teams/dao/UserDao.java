package ensias.teams.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ensias.teams.buzinessLayer.User;

public interface UserDao {
	public ArrayList<User> bringAllUsers();
	public User bringUser(String email, String pass);
	public void addUser(User user);
	public List bringMessagerie(User user);
	User bringUser(int id);
	List bringAllMessagerie(User user);
	public List bringAllMessagerie(User user, Long id);
	public List<User> bringUsersHavingValue(User user, String value);
	public User bringUser(String email);
}

package ensias.teams.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import ensias.teams.buzinessLayer.User;

public interface UserDao {
	public ArrayList<User> bringAllUsers();
	public User bringUser(String email);
	public void addUser(User user);
	public User getUserByID(int UserId) throws SQLException;
}
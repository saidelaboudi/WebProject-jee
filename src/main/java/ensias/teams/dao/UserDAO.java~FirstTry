package ensias.teams.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import ensias.teams.buzinessLayer.User;

public interface UserDao {
	public ArrayList<User> bringAllUsers();
	public User bringUser(String email, String pass);
	public void addUser(User user);
	public User getUserByID(int UserId,DAOFactory db) throws SQLException;
	public User bringUser(String parameter);
}

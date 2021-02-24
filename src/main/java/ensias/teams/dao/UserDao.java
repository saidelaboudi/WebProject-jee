package ensias.teams.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.User;

public interface UserDao {
	public ArrayList<User> bringAllUsers();
	public User bringUser(String email, String pass) ;
	public void addUser(User user);
	public void addUser(User user,DAOFactory db) throws SQLException;
	public ArrayList<User> addExcell2Depart(String excelFilePath) throws IOException, SQLException;
	public ArrayList<User> addExcell2Depart(InputStream inputStream) throws IOException, SQLException;
	public ArrayList<User> getUsersByTag(Tag tag1,DAOFactory db) throws SQLException;
	public int getUserID(User user,DAOFactory db) throws SQLException;
	public User getUserByID(int UserId,DAOFactory db) throws SQLException;
	
}

package ensias.teams.dao;

import java.sql.*;
import java.util.ArrayList;

import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.User;

/**
 * TagDAO
 */
public interface TagDAO {

<<<<<<< HEAD
    public void addTag_User(Tag t,DataBase db) throws SQLException{
		int UserID;
		String sql="INSERT INTO alias(Alias,StudentID) VALUES (?,?)";
        PreparedStatement statement = db.connection.prepareStatement(sql);
        
        for(User user:t.tagged) {
        	ResultSet set = db.Select("Users","Email='"+user.email+"'");
        	set.next();
        	UserID=set.getInt("ID");
        	statement.setString(1,t.tagName);   
        	statement.setInt(2, UserID);        	
        	statement.execute();
        }
	}
=======
	public void addTag(Tag t, DataBase db) throws SQLException;
	public void removeTag(Tag t, DataBase db) throws SQLException;
    public void addTag_User(Tag t,DataBase db) throws SQLException;
    public ArrayList<Tag> getTagList(DataBase db) throws SQLException;
    public ArrayList<User> getUsersTagged(String TagName, DataBase db) throws SQLException;

>>>>>>> main
    
}
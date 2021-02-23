package ensias.teams.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.User;

/**
 * TeamDAO
 */
public class TagDAOImp implements TagDAO{

	@Override
	public void addTag(Tag t, DataBase db) throws SQLException {
		int UserID;
		String sql="INSERT INTO tag (tag) VALUES (?)";
	    PreparedStatement statement = db.connection.prepareStatement(sql);
	    statement.setString(1,t.tagName);      	
    	statement.execute();
	}
	
	@Override
	public void removeTag(Tag t, DataBase db) throws SQLException {
		int UserID;
		String sql="DELETE FROM tag  WHERE tag = ?";
	    PreparedStatement statement = db.connection.prepareStatement(sql);
	    statement.setString(1,t.tagName);      	
    	statement.execute();
	}
	@Override
	public void addTag_User(Tag t, DataBase db) throws SQLException {
		int UserID;
		String sql="INSERT INTO tag_users (TagID,UsersID) VALUES (?,?)";
	    PreparedStatement statement = db.connection.prepareStatement(sql);
	    
	    for(User user:t.tagged) {
	    	ResultSet set = db.Select("Users","Email='"+user.email+"'");
	    	set.next();
	    	UserID=set.getInt("ID");
	    	set = db.Select("Tag","tag='"+t.tagName+"'");
	    	set.next();
	    	int TagID=set.getInt("ID");
	    	statement.setInt(1,TagID);   
	    	statement.setInt(2, UserID);        	
	    	statement.execute();
	    }
	}
	
	@Override
	public ArrayList<Tag> getTagList(DataBase db) throws SQLException {
		ArrayList<Tag> list = new ArrayList<Tag>();
		try{
			ResultSet set = db.Select("Tag","1");
			while(set.next()) {
				list.add(new Tag(set.getString(2), null));
			}
		}catch(Exception e) {
			
		}
		return list;
	}
	
	
	@Override
	public ArrayList<User> getUsersTagged(String TagName, DataBase db) throws SQLException {
		int UserID;
		UserDaoImpl addUser = new UserDaoImpl(null);
		ArrayList<User> users = new ArrayList<User>();
    	ResultSet set = db.Select("tag","tag='"+TagName+"'");
    	set.next();
    	int TagID=set.getInt("ID");
    	set = db.Select("tag_users","TagID='"+TagID+"'");
    	
    	while(set.next()) {
    		UserID=set.getInt("UsersID");
    		System.out.println("------------");
    		users.add(addUser.getUserByID(UserID, db));
    	}
		return users;
	}
	
	

}
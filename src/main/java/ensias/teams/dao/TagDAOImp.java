package ensias.teams.dao;

import java.sql.Connection;
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
	public void addTag(Tag t, DAOFactory db) throws SQLException {
		String sql="INSERT INTO tag (tag) VALUES (?)";
	    PreparedStatement statement = db.getConnection().prepareStatement(sql);
	    statement.setString(1,t.tagName);      	
    	statement.execute();
	}


	public void removeTag(Tag t, DataBase db) throws SQLException {
		int UserID;
		String sql="DELETE FROM tag  WHERE tag = ?";
	    PreparedStatement statement = db.connection.prepareStatement(sql);
	    statement.setString(1,t.tagName);      	
    	statement.execute();
	}
	@Override
	public void addTag_User(Tag t, DAOFactory db) throws SQLException {
		int UserID;
		String sql="INSERT INTO tag_users (TagID,UsersID) VALUES (?,?)";
	    PreparedStatement statement = db.getConnection().prepareStatement(sql);
	    
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
	public void removeTag_User(Tag t, String email) throws SQLException {
		DAOFactory db = DAOFactory.getInstance();
		Long UserID;
		String sql="REMOVE FROM tag_users WHERE TagID = ? and UsersID = ? ";
	    PreparedStatement statement = db.getConnection().prepareStatement(sql);
	    
	    for(User user:t.tagged) {
	    	if (user.email.equals(email)) {
		    	UserID = user.id;
		    	ResultSet set = db.Select("Tag","tag='"+t.tagName+"'");
		    	set.next();
		    	int TagID=set.getInt("ID");
		    	statement.setInt(1,TagID);   
		    	statement.setLong(2, UserID);        	
		    	statement.execute();
		    }
	    }
	}
	
	@Override
	public ArrayList<Tag> getTagList(DAOFactory db) throws SQLException {
		//db.getConnection().createStatement().executeQuery("use ensiasteams");
		ArrayList<Tag> list = new ArrayList<Tag>();
		try{
			ResultSet set = db.Select("Tag","1");
			while(set.next()) {
				list.add(new Tag(set.getString(2), null));
			}
		}catch(Exception e) {
			
		}
		System.out.println("Get tag List Done !");
		return list;
	}
	
	public ArrayList<Tag> getTagList() throws SQLException {
		DAOFactory db = DAOFactory.getInstance();
		Connection connection = db.getConnection();
		String qr = "USE ensiasteams";
		PreparedStatement st = connection.prepareStatement(qr);
		st.executeQuery(qr);
		st.close();
		qr = "Select * from tag";
		st = connection.prepareStatement(qr);
		ResultSet set = st.executeQuery(qr);
		ArrayList<Tag> list = new ArrayList<Tag>();
		try{
			while(set.next()) {
				list.add(new Tag(set.getString(2), null));
			}
		}catch(Exception e) {
			
		}
		System.out.println("Get tag List Done !");
		return list;
	}
	
	@Override
	public ArrayList<User> getUsersTagged(String TagName,DAOFactory db) throws SQLException {
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
    		users.add(addUser.getUserByID(UserID));
    	}
		return users;
	}
	@Override
	public void removeTag(Tag t, DAOFactory db) {
		String sql="REMOVE TROM tag WHERE tag = ? ";
	    PreparedStatement statement;
		try {
			statement = db.getConnection().prepareStatement(sql);
			statement.setString(1,t.tagName);      	
		    statement.execute();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
	

}
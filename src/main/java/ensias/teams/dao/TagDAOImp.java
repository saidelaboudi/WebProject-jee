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
	public int getTagId(String tagName, DAOFactory db) throws SQLException{
		int id = -1;
		Connection connection = db.getConnection();
		String sql = "USE " + db.getSchema();
		PreparedStatement st = connection.prepareStatement(sql);
		st.execute();
		st.close();
		
		sql = "SELECT ID FROM tag WHERE tag = ?";
		st = connection.prepareStatement(sql);
		st.setString(1, tagName);
		ResultSet rs = st.executeQuery();
		if (rs.next())
			id = rs.getInt(1);
		st.close();
		
		return id;
	}

	@Override
	public void addTag(Tag t, DAOFactory db) throws SQLException {
		Connection connection = db.getConnection();
		String sql = "USE " + db.getSchema();
		PreparedStatement st = connection.prepareStatement(sql);
		st.execute();
		st.close();
		
		sql="INSERT INTO tag (tag, owner) VALUES (?, ?)";
	    st = connection.prepareStatement(sql);
	    st.setString(1,t.tagName);
	    st.setLong(2, t.owner.id);
    	st.execute();
    	st.close();
	}

	@Override
	public void removeTag(Tag t, DAOFactory db) throws SQLException {
		Connection connection = db.getConnection();
		String sql = "USE " + db.getSchema();
		PreparedStatement st = connection.prepareStatement(sql);
		st.execute();
		st.close();
		
		sql="delete FROM tag where tag = ?";
	    st = connection.prepareStatement(sql);
	    st.setString(1,t.tagName);      	
    	st.execute();
    	st.close();
	}
	@Override
	public void addTag_User(Tag t, DAOFactory db, String mail) throws SQLException {
		// connect to database
		Connection connection = db.getConnection();
		String sql = "USE " + db.getSchema();
		PreparedStatement st = connection.prepareStatement(sql);
		st.execute();
		st.close();
		
		// get mail user id
		sql = "select * from users where Email = ?";
		st = connection.prepareStatement(sql);
		st.setString(1, mail);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			int UserID = rs.getInt(1);
			sql="INSERT INTO tag_users (TagID,UsersID) VALUES (?,?)";
			st = connection.prepareStatement(sql);
			st.setInt(1, t.tagId);
			st.setInt(2, UserID);
			st.execute();
			st.close();
		}
	}
		
	    
	
	@Override
	public ArrayList<Tag> getTagList(DAOFactory db, User owner) throws SQLException {
		Connection connection = db.getConnection();
		String sql = "USE " + db.getSchema();
		PreparedStatement st = connection.prepareStatement(sql);
		st.execute();
		st.close();
		sql = "select * from tag ";
		st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		ArrayList<Tag> at = new ArrayList<Tag>();
		while (rs.next()) {
			at.add(new Tag(rs.getString(2), owner));
		}
		
		return at;
	}
	
	
	@Override
	public ArrayList<User> getUsersTagged(String tagName,DAOFactory db) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		Connection connection = db.getConnection();
		String sql = "USE " + db.getSchema();
		PreparedStatement st = connection.prepareStatement(sql);
		st.execute();
		st.close();
		
		int tagID = getTagId(tagName, db);
		sql = "SELECT * FROM users Where ID IN (SELECT UsersID from tag_users where TagID = ?)";
		st = connection.prepareStatement(sql);
		st.setInt(1, tagID);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			users.add(new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getString(5), rs.getString(7)));
		}
		
		return users;
	}
	
	

}
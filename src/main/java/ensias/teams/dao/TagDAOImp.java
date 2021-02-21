package ensias.teams.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;

/**
 * TeamDAO
 */
public class TagDAOImp implements TagDAO{

	@Override
	public void addTag_User(Tag t, DataBase db) throws SQLException {
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
}
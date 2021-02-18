package ensias.teams.dao;

import java.sql.*;

import ensias.teams.buzinessLayer.Group;

/**
 * GroupDAO
 */
public class GroupDAO {

    public void addGroup(Group g,DataBase db) throws SQLException {
		int OwnerID;
		ResultSet set = db.Select("Users","Email='"+g.owner.email+"'");
		if(set.next()) {
			OwnerID=set.getInt("ID");			
		}else {
			set = db.Select("Users","Email='"+g.owner.email+"'");
			set.next();
			OwnerID=set.getInt("ID");			
		}
		String sql="INSERT INTO Groups (NAME,OwnerID,description) VALUES (?,?,?)";
        PreparedStatement statement = db.connection.prepareStatement(sql);
        statement.setString(1,g.name);   
        statement.setInt(2, OwnerID);  
        statement.setString(3,g.description);  
        System.out.print(statement.toString());
        statement.execute();
	}

    
    
}
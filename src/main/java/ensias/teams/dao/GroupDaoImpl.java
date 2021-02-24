package ensias.teams.dao;

import java.util.ArrayList;
import java.sql.*;

import ensias.teams.buzinessLayer.Group;

public class GroupDaoImpl implements GroupDao{
	public DAOFactory daoFactory;
	public GroupDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	@Override
	public ArrayList<Group> findUserGroups(int idUser) {
		ArrayList<Group> list = new ArrayList<>(); 
		return list;
	}
	public void addGroup(Group g,DAOFactory db) throws SQLException {
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
        PreparedStatement statement = db.getConnection().prepareStatement(sql);
        statement.setString(1,g.name);   
        statement.setInt(2, OwnerID);  
        statement.setString(3,g.description);  
        System.out.print(statement.toString());
        statement.execute();
	}
}

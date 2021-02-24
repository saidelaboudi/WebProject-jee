package ensias.teams.dao;

import java.util.ArrayList;
import java.sql.*;

import ensias.teams.buzinessLayer.Group;
import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;

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
	
	public ArrayList<Group> getGroupList(DAOFactory db) throws SQLException {
		ArrayList<Group> list = new ArrayList<Group>();
		try{
			ResultSet set = db.Select("Groups","1");
			while(set.next()) {
				list.add(new Group(set.getString(2), null));
			}
		}catch(Exception e) {
			
		}
		System.out.println("Get tag List Done !");
		return list;
	}
	
	
	public int getGroupID(Group group,DAOFactory db) throws SQLException {
		ResultSet set = db.Select("groups","NAME='"+group.name+"'");
		set.next();
		return set.getInt("ID");
	}
	
	public ArrayList<Team> getTeamsByGroup(Group group, DAOFactory daoF) throws SQLException  {
		// TODO Auto-generated method stub
		ArrayList<Team> list = new ArrayList<Team>();
		TeamDAOImp addTeam = new TeamDAOImp();
		try{
			ResultSet set = daoF.Select("groups_team","GroupsID = "+getGroupID(group, daoF));
			while(set.next()) {
				int TeamID = set.getInt(2);
				list.add(addTeam.getTeamByID(TeamID,daoF));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Get tag List Done !");
		return list;
	}
	
	
}

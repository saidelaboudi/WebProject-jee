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
			e.getMessage();
		}
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
		return list;
	}

	
	
	public void addTeamsGroup(Group groupe, ArrayList<Team> teamsList, DAOFactory daoF)  throws SQLException {
		for(Team team : teamsList) {
			addTeamGroup(groupe, team, daoF);
		}
	}
	
	public void addTeamGroup(Group groupe, Team team, DAOFactory daoF)  throws SQLException {
		GroupDaoImpl addGroup = new GroupDaoImpl(daoF);
		TeamDAOImp addTeam = new TeamDAOImp();
		int GroupID=addGroup.getGroupID(groupe, daoF);
		int TeamID= addTeam.getTeamID(team, daoF) ;
		String sql="INSERT INTO groups_team (GroupsID,TeamID) VALUES (?,?)";
        PreparedStatement statement = daoF.getConnection().prepareStatement(sql);
        statement.setInt(1,GroupID);
        statement.setInt(2, TeamID);
        statement.execute();
	}
	public Group getGroupByName(String groupName,DAOFactory daoF) {
		Group group = null;
		try{
			ResultSet set = daoF.Select("Groups","Name='"+groupName+"'");
			set.next();
			group = new Group(set.getString(2), null);
		}catch(Exception e) {
			System.out.println(e.getMessage());
	}
		return group;
	
}
	}

package ensias.teams.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;

/**
 * TeamDAOImp
 */
public class TeamDAOImp implements TeamDAO{
	
	
    public void addTeam(Team t,DAOFactory db) throws SQLException {
		int OwnerID;
		ResultSet set = db.Select("Users","Email='"+t.owner.email+"'");
		if(set.next()) {
			OwnerID=set.getInt("ID");			
		}else {
			set = db.Select("Users","Email='"+t.owner.email+"'");
			set.next();
			OwnerID=set.getInt("ID");			
		}
		String sql="INSERT INTO team(Name,OwnerID) VALUES (?,?)";
        PreparedStatement statement = db.getConnection().prepareStatement(sql);
        statement.setString(1,t.name);   
        statement.setInt(2, OwnerID);
        statement.execute();
	}

    
    public int getTeamID(Team team,DAOFactory db) throws SQLException {
		ResultSet set = db.Select("Team","Name='"+team.name+"'");
		set.next();
		return set.getInt("ID");
	}

    public void addTeam_Member(int TeamID , int UserID,DAOFactory db) throws SQLException {
		String sql="INSERT INTO Team_Users (TeamID,UsersID) VALUES (?,?)";
        PreparedStatement statement = db.getConnection().prepareStatement(sql);
        statement.setInt(1,TeamID);
        statement.setInt(2, UserID);
        statement.execute();
	}
    
    public void addTeam_Member(Team team,User user,DAOFactory db)throws SQLException {
    	int TeamID = this.getTeamID(team, db);
    	UserDaoImpl userdao = new UserDaoImpl();
    	int UserID =userdao.getUserID(user,db) ;
    	addTeam_Member(TeamID, UserID, db);
    	
    }

    public ArrayList<User> getUsersByTeamName(String name,DAOFactory db) throws SQLException{
    	int UserID;
		UserDaoImpl addUser = new UserDaoImpl();
		ArrayList<User> users = new ArrayList<User>();
    	ResultSet set = db.Select("team","Name='"+name+"'");
    	set.next();
    	int TeamID=set.getInt("ID");
    	set = db.Select("team_users","TeamID='"+TeamID+"'");
    	
    	while(set.next()) {
    		UserID=set.getInt("UsersID");
    		users.add(addUser.getUserByID(UserID,db));
    	}
    	return users;
    }
    
    public Team getTeamByID(int ID,DAOFactory db) throws SQLException{
    	Team team = null;
    	UserDaoImpl addUser = new UserDaoImpl();
    	ResultSet set = db.Select("team","ID="+ID);
    	while(set.next()) {
    		team= new Team(set.getString(2),addUser.getUserByID(set.getInt(4), db));
    	}
    	return team;
    }
    
    public ArrayList<Team> getTeamList(DAOFactory db) throws SQLException {
		ArrayList<Team> list = new ArrayList<Team>();
		try{
			ResultSet set = db.Select("team","1");
			while(set.next()) {
				list.add(new Team(set.getString(2), null));
			}
		}catch(Exception e) {
			
		}
		return list;
	}
    
}
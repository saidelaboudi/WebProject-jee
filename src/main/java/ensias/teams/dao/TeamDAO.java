package ensias.teams.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;

/**
 * TeamDAO
 */
public class TeamDAO {


    public void addTeam(Team t,DataBase db) throws SQLException {
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
        PreparedStatement statement = db.connection.prepareStatement(sql);
        statement.setString(1,t.name);   
        statement.setInt(2, OwnerID);  
        statement.execute();
	}

    
    public int getTeamID(Team team,DataBase db) throws SQLException {
		ResultSet set = db.Select("Team","Name='"+team.name+"'");
		set.next();
		return set.getInt("ID");
	}

    public void addTeam_Member(int TeamID , int UserID,DataBase db) throws SQLException {
		String sql="INSERT INTO Team_Users (TeamID,UsersID) VALUES (?,?)";
        PreparedStatement statement = db.connection.prepareStatement(sql);
        statement.setInt(1,TeamID);
        statement.setInt(2, UserID);
        statement.execute();
	}
    
    public void addTeam_Member(Team team,User user,DataBase db)throws SQLException {
    	int TeamID = this.getTeamID(team, db);
    	UserDaoImpl userdao = new UserDaoImpl(null);
    	int UserID =userdao.getUserID(user, db) ;
    	addTeam_Member(TeamID, UserID, db);
    	
    }


}
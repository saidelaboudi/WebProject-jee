package ensias.teams.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;

/**
 * TeamDAO
 */
public interface TeamDAO {


    public void addTeam(Team t,DataBase db) throws SQLException ;

    public int getTeamID(Team team,DataBase db) throws SQLException ;

    public void addTeam_Member(int TeamID , int UserID,DataBase db) throws SQLException ;
    
    public void addTeam_Member(Team team,User user,DataBase db)throws SQLException ;


}
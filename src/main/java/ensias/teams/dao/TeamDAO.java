package ensias.teams.dao;

import java.sql.SQLException;

import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;

/**
 * TeamDAO
 */
public interface TeamDAO {


    public void addTeam(Team t,DAOFactory db) throws SQLException ;

    public int getTeamID(Team team,DAOFactory db) throws SQLException ;

    public void addTeam_Member(int TeamID , int UserID,DAOFactory db) throws SQLException ;
    
    public void addTeam_Member(Team team,User user,DAOFactory db)throws SQLException ;


}
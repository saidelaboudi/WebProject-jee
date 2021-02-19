
package ensias.teams.dao;

import java.sql.SQLException;

import ensias.teams.buzinessLayer.Group;
import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;

public class Test {
	public static void main(String[] args) throws SQLException {
		DataBase db = new DataBase("localhost","3306","teams","root","root");
		User owner = new User("James2", "Bandel2", "23, rue des java2 , jvm, oracle","12-19-20","java2@jee.oracle");
		Team team = new Team("test2", owner);
		Group groupe = new Group("Teams ", "Groupe pour rassembler des equipes en un seul grps");
		groupe.owner= owner;
		Tag tag = new Tag("Gl1", owner);
		
		
		TeamDAO addTeam = new TeamDAO();
		UserDAO addUser = new UserDAO();
		GroupDAO addGroup = new GroupDAO();
		TagDAO addtag = new TagDAO();
		
		addUser.addUser(owner, db);
		addTeam.addTeam(team, db);
		addGroup.addGroup(groupe, db);
		addtag.addTag_User(tag, db);
	}
}

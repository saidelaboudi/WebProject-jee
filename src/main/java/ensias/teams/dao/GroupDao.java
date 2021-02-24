package ensias.teams.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import ensias.teams.buzinessLayer.Group;

public interface GroupDao {
	public ArrayList<Group> findUserGroups( int idUser );
	public void addGroup(Group g,DAOFactory db) throws SQLException;
	//pas encore complet

}

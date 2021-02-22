package ensias.teams.dao;

import java.util.ArrayList;

import ensias.teams.buzinessLayer.Group;

public interface GroupDao {
	public ArrayList<Group> findUserGroups( int idUser );
	//pas encore complet

}

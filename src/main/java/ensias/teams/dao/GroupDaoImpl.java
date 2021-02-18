package ensias.teams.dao;

import java.util.ArrayList;

import ensias.teams.buzinessLayer.Group;

public class GroupDaoImpl implements GroupDao{
	private DAOFactory daoFactory;
	public GroupDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	@Override
	public ArrayList<Group> findUserGroups(int idUser) {
		
		
		
		ArrayList<Group> list = new ArrayList<>(); 
		return list;
	}
	
}

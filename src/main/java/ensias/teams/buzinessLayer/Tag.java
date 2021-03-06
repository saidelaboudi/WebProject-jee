package ensias.teams.buzinessLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import ensias.teams.dao.DAOFactory;
import ensias.teams.dao.TagDAO;
import ensias.teams.dao.TagDAOImp;

public class Tag {
	public int tagId;
	public String tagName;
	public User owner;
	public ArrayList<User> tagged ;
	
	
	public Tag(String tagName, User owner) throws SQLException {
		super();
		this.tagName = tagName;
		this.owner = owner;
		this.tagId = _getTagId(tagName);
		tagged = _getTagged();
	}
	
	public void addMember(User person) {
		this.tagged.add(person);
	}
	
	public void removeMember(User person) {
		this.tagged.remove(person);
	}
	
	public ArrayList<User> _getTagged() throws SQLException {
		ArrayList<User> tg = new ArrayList<User>();
		TagDAO dao = new TagDAOImp();
		tg = dao.getUsersTagged(tagName, DAOFactory.getInstance());
		//tg.add(owner);
		return tg;
	}
	
	public int _getTagId(String tName) throws SQLException {
		TagDAO dao = new TagDAOImp();
		int id = dao.getTagId(tName, DAOFactory.getInstance());
		return id;
	}

}

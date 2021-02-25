package ensias.teams.dao;

import java.sql.*;
import java.util.ArrayList;

import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.User;

/**
 * TagDAO
 */
public interface TagDAO {


	public void addTag(Tag t, DAOFactory db) throws SQLException;
    public void addTag_User(Tag t,DAOFactory db, String mail) throws SQLException;
    public ArrayList<Tag> getTagList(DAOFactory db, User owner) throws SQLException;
    public ArrayList<User> getUsersTagged(String TagName, DAOFactory db) throws SQLException;
	void removeTag(Tag t, DAOFactory db) throws SQLException;
	int getTagId(String tagName, DAOFactory db) throws SQLException;



    
}
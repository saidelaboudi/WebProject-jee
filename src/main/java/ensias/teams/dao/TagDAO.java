package ensias.teams.dao;

import java.sql.*;

import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.User;

/**
 * TagDAO
 */
public interface TagDAO {

    public void addTag_User(Tag t,DataBase db) throws SQLException;

    
}
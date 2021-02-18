package ensias.teams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ensias.teams.buzinessLayer.User;

/**
 * @author Yasser
 *
 */
public class UserDaoImpl implements UserDao {
	private DAOFactory daoFactory;
	
	public UserDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	@Override
	public ArrayList<User> bringAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		
		Connection connection=null;
		Statement st=null;
		ResultSet rs=null;
		
		
		try {
			connection = this.daoFactory.getConnection();
			st=connection.createStatement();
			st.executeQuery("USE "+ this.daoFactory.getSchema());
			
			rs=st.executeQuery("SELECT * FROM user");
			while( rs.next()) {
				users.add( new User(rs.getLong(1) , rs.getString(2) ,rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(6) ));
			}
		}catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( rs ,st, connection );
	    }

	
		
		
		return users;
	}
	
	

	public static void fermetureSilencieuse( ResultSet resultSet ) {
	    if ( resultSet != null ) {
	        try {
	            resultSet.close();
	        } catch ( SQLException e ) {
	            System.out.println( "�chec de la fermeture du ResultSet : " + e.getMessage() );
	        }
	    }
	}

	public static void fermetureSilencieuse( Statement statement ) {
	    if ( statement != null ) {
	        try {
	            statement.close();
	        } catch ( SQLException e ) {
	            System.out.println( "�chec de la fermeture du Statement : " + e.getMessage() );
	        }
	    }
	}
	public static void fermetureSilencieuse( Connection connexion ) {
	    if ( connexion != null ) {
	        try {
	            connexion.close();
	        } catch ( SQLException e ) {
	            System.out.println( "�chec de la fermeture de la connexion : " + e.getMessage() );
	        }
	    }
	}
	public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
	    fermetureSilencieuse( statement );
	    fermetureSilencieuse( connexion );
	}

	public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
	    fermetureSilencieuse( resultSet );
	    fermetureSilencieuse( statement );
	    fermetureSilencieuse( connexion );
	}
	
	
	
	
}
package ensias.teams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ensias.teams.buzinessLayer.User;

public class MessagePersoDaoImpl implements MessagePersoDao{
	private DAOFactory daoFactory;
	public MessagePersoDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public boolean insertMessagePerso(Long idSender, Long idReceiver, String content) {
		Connection connection=null;
		PreparedStatement preparedStmt=null;
		Statement st = null;
		
		try {
		  connection = this.daoFactory.getConnection();
		  st=connection.createStatement();
		  st.executeQuery("USE "+ this.daoFactory.getSchema());
	      String query = " insert into Message_perso (message, SenderId, ReceiverId)"
	        + " values ( ?, ?, ?)";	     
	     
	      preparedStmt = connection.prepareStatement(query);
	      preparedStmt.setLong(2,idSender );
	      preparedStmt.setString (1,content );
	      preparedStmt.setLong (3, idReceiver);
	      preparedStmt.execute();
	      
		}catch ( SQLException e ) {
	        return false;
	    } finally {
	        fermeturesSilencieuses( preparedStmt, connection );
	        
	    }

	
		return true;
	}
	public static void fermetureSilencieuse( ResultSet resultSet ) {
	    if ( resultSet != null ) {
	        try {
	            resultSet.close();
	        } catch ( SQLException e ) {
	            System.out.println( "échec de la fermeture du ResultSet : " + e.getMessage() );
	        }
	    }
	}

	public static void fermetureSilencieuse( Statement statement ) {
	    if ( statement != null ) {
	        try {
	            statement.close();
	        } catch ( SQLException e ) {
	            System.out.println( "échec de la fermeture du Statement : " + e.getMessage() );
	        }
	    }
	}
	public static void fermetureSilencieuse( Connection connexion ) {
	    if ( connexion != null ) {
	        try {
	            connexion.close();
	        } catch ( SQLException e ) {
	            System.out.println( "échec de la fermeture de la connexion : " + e.getMessage() );
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

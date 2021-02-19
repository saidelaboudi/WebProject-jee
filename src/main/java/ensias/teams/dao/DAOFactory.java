package ensias.teams.dao;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author Yasser
 *
 */
public class DAOFactory {
	private static final String URL="jdbc.url";
	private static final String DRIVER="jdbc.driverClass";
	private static final String LOGIN="jdbc.login";
	private static final String PASSWORD="jdbc.password";
	private static final String FICHIER_PROPERTIES = "/dao.properties";
	private static final String SCHEMA = "jdbc.schema";
	 private String url;
	 private String username;
	 private String password;
	 private String schema;
	 DAOFactory( String url, String username, String password ,String schema ) {
	        this.url = url;
	        this.username = username;
	        this.password = password;
	        this.schema=schema;
	 }
     public static DAOFactory getInstance()   {
	        String url;
	        String driver;
	        String nomUtilisateur;
	        String motDePasse;
	        String schema;
	        
	        Properties properties = new Properties();
	        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
	        
	        try {
				properties.load(fichierProperties);
	        } catch (IOException e) {
				System.out.println("Loading the properties file failed !!");
				e.printStackTrace();
			}
	       
            url = properties.getProperty( URL );
           
            driver = properties.getProperty( DRIVER );
            nomUtilisateur = properties.getProperty( LOGIN );
            schema = properties.getProperty(SCHEMA);
            motDePasse = properties.getProperty( PASSWORD );
	        try {
				Class.forName( driver );
			} catch (ClassNotFoundException e) {
				System.out.println("Loading the class Driver failed !!");
			}
	        DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse,schema );
	        
	        return instance;
	    }

	 public String getSchema() {
		return schema;
	}
	
	Connection getConnection()  {
        try {
			return DriverManager.getConnection( url, username, password );
		} catch (SQLException e) {
			System.out.println("Loading the connexion failed !!");
		}
        return null;
	  } 
	 
	 public GroupDao getGroupDao() {
	        return new GroupDaoImpl( this );
	    }
	 public UserDao getUserDao() {
	        return new UserDaoImpl( this );
	   }
}

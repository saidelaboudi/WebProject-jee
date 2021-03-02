package ensias.teams.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
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
	private static final String URL_TEST = "jdbc:mysql://localhost:3306?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	 private String url;
	 private String username;
	 private String password;
	 private String schema;

	public DAOFactory( String url, String username, String password ,String schema ) {
	        this.url = url;
	        this.username = username;
	        this.password = password;
	        this.schema=schema;
	 }

	 public static DAOFactory getInstanceTest()   {
		 DAOFactory instance = new DAOFactory( URL_TEST, "root", "root","ensiasteams" );  
		 return instance;
	 
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
	
	 public Connection getConnection()  {
	        try {
				return DriverManager.getConnection( url, username, password );
			} catch (SQLException e) {
				System.out.println("Loading the connexion failed !!");
			}
	        return null;
		  } 
	 

	 public UserDaoImpl getUserDao() {
	        return new UserDaoImpl();
	 }
	 public MessagePersoDao getMessagePersoDao() {
	        return new MessagePersoDaoImpl( this );
	    }
	/* public GroupDaoImpl getUserDao() {
	        return new UserDaoImpl( this );
	    }
*/
		
	public void Query(String query) throws SQLException {
		Statement statement=getConnection().createStatement();
		statement.executeUpdate(query);
	}
	
	public ResultSet Select(String TableName,String condition){		
		ResultSet result = null;
		try {
			String query="SELECT * FROM "+TableName+" WHERE "+condition;
			//System.out.println(query);
			Statement statement =this.getConnection().createStatement();
			result=statement.executeQuery(query);		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public void Insert(String value,String TableName) throws SQLException{
		String sql="INSERT INTO "+TableName+" VALUES("+value+");";

		System.out.println(sql);
		PreparedStatement statement=getConnection().prepareStatement(sql);
		//INSERT STAEMENT 
		/**
		 * INSERT INTO TABLENAME VALUES(--O-*-*)
		 */
		//System.out.println(statement.toString());
		try{
			statement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public void Delete(String condition,String TableName)  throws SQLException  {
		
		String sql="DELETE FROM "+TableName+" WHERE "+condition;
		Statement statement=getConnection().createStatement();
		/**
		 * DELLET FROM TABLENAME WHERE CONDITION(-- -*-*o)
		 */
		//System.out.println(sql);
		statement.executeUpdate(sql);

		//System.out.println(sql);
	}
	
	public void DropTable(String TableName) throws SQLException {
		String sql="DROP TABLE "+TableName;
		Statement statement=getConnection().createStatement();
		//System.out.println(sql);
		statement.execute(sql);
	}

	
}

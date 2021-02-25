package ensias.teams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.sql.*;
import java.util.*;

import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.User;

/**
 * @author Yasser
 * 
 * Some methods was added by Said 
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
		PreparedStatement st=null;
		ResultSet rs=null;
		
		
		try {
			String setDb = "USE "+ this.daoFactory.getSchema();
			connection = this.daoFactory.getConnection();
			st=connection.prepareStatement(setDb);
			st.executeQuery(setDb);
			st.close();
			
			String query = "SELECT * FROM users";
			st=connection.prepareStatement(setDb);
			rs=st.executeQuery(query);
			while( rs.next()) {
				users.add( new User(rs.getLong(1) , rs.getString(2) ,rs.getString(3) , rs.getString(6) , rs.getString(5) , rs.getString(7) ));
			}
			st.close();
		}catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( rs ,st, connection );
	    }

	
		
		
		return users;
	}
	
	@Override
	public User bringUser(String email) {
		User users = null;
		
		Connection connection=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		
		try {
			String setDb = "USE "+ this.daoFactory.getSchema();
			String query = "SELECT * FROM Users WHERE Email = ?";
			connection = this.daoFactory.getConnection();
			st=connection.prepareStatement(setDb);
			st.executeQuery(setDb);
			st.close();
			st=connection.prepareStatement(query);
			st.setString(1, email);
			
			rs=st.executeQuery(query);
			if (rs.next())
				users = new User(rs.getLong(1) , rs.getString(2) ,rs.getString(3) , rs.getString(6) , rs.getString(5) , rs.getString(7));
			st.close();
		}catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( rs ,st, connection );
	    }

		return users;
	}

	@Override
	public User bringUser(String email, String pass) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addUser(User user) {
		Connection connection=null;
		PreparedStatement st=null;		
		ResultSet rs=null;

		try {
			String setDb = "USE "+ this.daoFactory.getSchema();
			String query = "INSERT INTO Users (FirstName,LastName,Address,Password,Email) VALUES (?, ?, ?, ?, ?)";
			connection = this.daoFactory.getConnection();
			st=connection.prepareStatement(setDb);
			st.executeQuery(setDb);
			st.close();
			st=connection.prepareStatement(query);
			st.setString(1, user.firstName);
			st.setString(2, user.lastName );
			st.setString(3, user.address);
			st.setString(4, user.password);
			st.setString(5, user.email);
			st.executeQuery(query);
			st.close();
			
		}catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( rs ,st, connection );
	    }
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
	
	public void addUser(User user,DAOFactory db) throws SQLException {
		String sql="INSERT INTO Users (FirstName,LastName,Address,Password,Email) VALUES (?,?,?,?,?)";
        PreparedStatement statement = db.getConnection().prepareStatement(sql);
        statement.setString(1,user.firstName);
        statement.setString(2,user.lastName);
        statement.setString(3,user.address);
        statement.setString(4,user.password);
        statement.setString(5,user.email);
        statement.execute();
	}

	
	public ArrayList<User> addExcell2Depart(String excelFilePath) throws IOException, SQLException {
			
			ArrayList<User> Users = new ArrayList<User>();
	
			FileInputStream inputStream = new FileInputStream(excelFilePath);
	
	        Workbook workbook = new XSSFWorkbook(inputStream);
	
	        Sheet firstSheet = (Sheet) workbook.getSheetAt(0);
	        Iterator<Row> rowIterator = firstSheet.iterator();
	
	        rowIterator.next(); // skip the header row
	        
	        String FirstName ="Not Specified" ;
	        String LastName="Not Specified" ;
	        String Address="Not Specified" ;
	        String Password="Not Specified" ;
	        String Email="Not Specified" ;
	        
	        while (rowIterator.hasNext()) {
	            Row nextRow = rowIterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	            
	            while (cellIterator.hasNext()) {
	            	
	                Cell nextCell = cellIterator.next();
	                int columnIndex = nextCell.getColumnIndex();
	                
	                switch (columnIndex) {
	                case 0:
	                    FirstName = nextCell.getStringCellValue();
	                    //statement.setString(1 , FirstName);
	                    break;
	                case 1:
	                	LastName = (String) nextCell.getStringCellValue();
	                	//statement.setString(2 , LastName);            
	                	break;
	                case 2:
	                	Address = (String) nextCell.getStringCellValue();
	                	//statement.setString(3 , Address);            
	                	break;
	                case 3:
	                	Password = (String) nextCell.getStringCellValue();
	                	//statement.setString(4 , Password);            
	                	break;
	                case 4:
	                	Email = (String) nextCell.getStringCellValue();
	                	//statement.setString(5 , Email);            
	                	break;
	                }
	            }
	            
	            Users.add(new User(FirstName, LastName, Address, Password, Email));
	        }
	        workbook.close();
	        return Users;
		}
	
	
	public ArrayList<User> addExcell2Depart(InputStream inputStream) throws IOException, SQLException {
	
		ArrayList<User> Users = new ArrayList<User>();
	
		//FileInputStream inputStream = new FileInputStream(excelFilePath);
	
	    Workbook workbook = new XSSFWorkbook(inputStream);
	
	    Sheet firstSheet = (Sheet) workbook.getSheetAt(0);
	    Iterator<Row> rowIterator = firstSheet.iterator();
	
	    rowIterator.next(); // skip the header row
	    
	    String FirstName ="Not Specified" ;
	    String LastName="Not Specified" ;
	    String Address="Not Specified" ;
	    String Password="Not Specified" ;
	    String Email="Not Specified" ;
	    if(workbook!=null)
		    while (rowIterator.hasNext()) {
		        Row nextRow = rowIterator.next();
		        Iterator<Cell> cellIterator = nextRow.cellIterator();
		        
		        while (cellIterator.hasNext()) {
		        	
		            Cell nextCell = cellIterator.next();
		            int columnIndex = nextCell.getColumnIndex();
		            
		            switch (columnIndex) {
		            case 0:
		                FirstName = nextCell.getStringCellValue();
		                //statement.setString(1 , FirstName);
		                break;
		            case 1:
		            	LastName = (String) nextCell.getStringCellValue();
		            	//statement.setString(2 , LastName);            
		            	break;
		            case 2:
		            	Address = (String) nextCell.getStringCellValue();
		            	//statement.setString(3 , Address);            
		            	break;
		            case 3:
		            	Password = (String) nextCell.getStringCellValue();
		            	//statement.setString(4 , Password);            
		            	break;
		            case 4:
		            	Email = (String) nextCell.getStringCellValue();
		            	//statement.setString(5 , Email);            
		            	break;
		            }
		        }
		        
		        Users.add(new User(FirstName, LastName, Address, Password, Email));
		    }
	    workbook.close();
	    return Users;
}

public ArrayList<User> getUsersByTag(Tag tag1,DAOFactory db) throws SQLException{
	ArrayList<User> users = new ArrayList<User>();
	try{
		String tag=tag1.tagName;
		ResultSet set = db.Select("Tag","tag='"+tag+"'");
		set.next();
		int TagID=set.getInt("ID");
		set = db.Select("Tag_Users","TagID='"+TagID+"'");
		while(set.next()) {
			int UserID= (int)set.getInt(2);
			
			users.add(getUserByID(UserID,db));
		}
	}catch(Exception e) {
		
	}
	System.out.println("Get users by Tag done !");
	return users;
}


	public ArrayList<User> getUsersByTag(Tag tag1) throws SQLException{
		ArrayList<User> users = new ArrayList<>();
		
		Connection connection=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try {
			String setDb = "USE "+ this.daoFactory.getSchema();
			connection = this.daoFactory.getConnection();
			st=connection.prepareStatement(setDb);
			st.executeQuery(setDb);
			st.close();
			
			String query = "SELECT * FROM tag WHERE tag = ?";
			st=connection.prepareStatement(query);
			st.setString(1, tag1.tagName);
			rs = st.executeQuery(query);
			rs.next();
			st.close();
			
			int TagID=rs.getInt("ID");
			query = "SELECT * FROM tag_users WHERE TagID = ?";
			st=connection.prepareStatement(query);
			st.setLong(1, TagID);
			rs = st.executeQuery(query);
			while(rs.next()) {
				int UserID= (int)rs.getInt(2);
				users.add(getUserByID(UserID));
			}
			st.close();
		}catch(Exception e) {
			
		}
		return users;
	}


	public int getUserID(User user,DAOFactory db) throws SQLException {
		ResultSet set = db.Select("Users","Email = '"+user.email+"'");
		if(set.next()) {
			return set.getInt("ID");
		}
		return 0;
	}
	
	public User getUserByID(int UserId,DAOFactory db) throws SQLException{
		User user = null ;
		ResultSet set;
		try {
			set = db.Select("Users","ID = '"+UserId+"'");
			if(set.next()) {
				return new User(set.getString(2),set.getString(2),set.getString(4),set.getString(5),set.getString(6));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public int getUserID(User user) throws SQLException {
		int id = 0;

		Connection connection=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		
		try {
			String setDb = "USE "+ this.daoFactory.getSchema();
			connection = this.daoFactory.getConnection();
			st=connection.prepareStatement(setDb);
			st.executeQuery(setDb);
			st.close();
			
			String query = "SELECT * FROM Users WHERE Email = ?";
			st=connection.prepareStatement(query);
			st.setString(1, user.email);
			
			rs=st.executeQuery(query);
			if (rs.next())
				id = (int) rs.getLong(1);
		}catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( rs ,st, connection );
	    }

		return (int) id;
	}
	
	public User getUserByID(int UserId) throws SQLException{
			User users = null;
			
			Connection connection=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			
			
			try {
				String setDb = "USE "+ this.daoFactory.getSchema();
				String query = "SELECT * FROM Users WHERE ID = ?";
				connection = this.daoFactory.getConnection();
				st=connection.prepareStatement(setDb);
				st.executeQuery(setDb);
				st.close();
				st=connection.prepareStatement(query);
				st.setLong(1, UserId);
				
				rs=st.executeQuery(query);
				if (rs.next())
					users = new User(rs.getLong(1) , rs.getString(2) ,rs.getString(3) , rs.getString(6) , rs.getString(5) , rs.getString(7));
				st.close();
			}catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses( rs ,st, connection );
		    }

			return users;
		}
	
}

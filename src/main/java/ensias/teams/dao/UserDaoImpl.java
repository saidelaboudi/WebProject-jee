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
		Statement st=null;
		ResultSet rs=null;
		
		
		try {
			connection = this.daoFactory.getConnection();
			st=connection.createStatement();
			st.executeQuery("USE "+ this.daoFactory.getSchema());
			
			rs=st.executeQuery("SELECT * FROM users");
			while( rs.next()) {
				users.add( new User(rs.getLong(1) , rs.getString(2) ,rs.getString(3) , rs.getString(6) , rs.getString(5) , rs.getString(7) ));
			}
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
		Statement st=null;
		ResultSet rs=null;
		
		
		try {
			connection = this.daoFactory.getConnection();
			st=connection.createStatement();
			st.executeQuery("USE "+ this.daoFactory.getSchema());
			
			rs=st.executeQuery("SELECT * FROM Users WHERE Email = '" + email + "'");
			if (rs.next())
				users = new User(rs.getLong(1) , rs.getString(2) ,rs.getString(3) , rs.getString(6) , rs.getString(5) , rs.getString(7));

		}catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( rs ,st, connection );
	    }

		return users;
	}

	
	@Override
	public void addUser(User user) {
		Connection connection=null;
		Statement st=null;		
		ResultSet rs=null;

		try {
			connection = this.daoFactory.getConnection();
			st=connection.createStatement();
			st.executeQuery("USE "+ this.daoFactory.getSchema());
			String query = "INSERT INTO Users (FirstName,LastName,Address,Password,Email) VALUES (";
			query = query.concat("'" + user.firstName + "',");
			query = query.concat("'" + user.lastName + "',");
			query = query.concat("'" + user.address + "',");
			query = query.concat("'" + user.password + "',");
			query = query.concat("'" + user.email + "')");
			st.executeUpdate(query);
			
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
	
	public void addUser(User user,DataBase db) throws SQLException {
		String sql="INSERT INTO Users (FirstName,LastName,Address,Password,Email) VALUES (?,?,?,?,?)";
        PreparedStatement statement = db.connection.prepareStatement(sql);
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


	public ArrayList<User> getUsersByTag(Tag tag1,DataBase db) throws SQLException{
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
		return users;
	}

	public int getUserID(User user,DataBase db) throws SQLException {
		ResultSet set = db.Select("Users","Email = '"+user.email+"'");
		if(set.next()) {
			return set.getInt("ID");
		}
		return 0;
	}
	
	public User getUserByID(int UserId,DataBase db) throws SQLException{
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
}

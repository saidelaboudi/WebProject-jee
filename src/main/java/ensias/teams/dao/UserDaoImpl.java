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

// has not ended yet
	public void addExcell2Depart(String excelFilePath , DataBase db) throws IOException, SQLException {

		FileInputStream inputStream = new FileInputStream(excelFilePath);

        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet firstSheet = (Sheet) workbook.getSheetAt(0);
        Iterator<Row> rowIterator = firstSheet.iterator();


        String sql = "INSERT INTO Users (FirstName,LastName,Address,Password,Email) VALUES (?,?,?,?,?)";
        PreparedStatement statement = db.connection.prepareStatement(sql);    

        rowIterator.next(); // skip the header row

        while (rowIterator.hasNext()) {
            Row nextRow = rowIterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                case 0:
                    String FirstName = nextCell.getStringCellValue();
                    statement.setString(1 , FirstName);
                    break;
                case 1:
                	String LastName = (String) nextCell.getStringCellValue();
                	statement.setString(2 , LastName);            
                	break;
                case 2:
                	String Address = (String) nextCell.getStringCellValue();
                	statement.setString(3 , Address);            
                	break;
                case 3:
                	String Password = (String) nextCell.getStringCellValue();
                	statement.setString(4 , Password);            
                	break;
                case 4:
                	String Email = (String) nextCell.getStringCellValue();
                	statement.setString(5 , Email);            
                	break;
                }
                System.out.print(statement.toString());
                statement.execute();
            }
        }
        workbook.close();

        // execute the remaining queries

	}

	public ArrayList<User> getUsersByTag(DataBase db) throws SQLException{
		ArrayList<User> users = new ArrayList<User>();
		System.out.print("Saisie L'etiquette");
		try (Scanner scanner = new Scanner(System.in)) {
			String tag= scanner.nextLine();
			ResultSet set = db.Select("Tag","tag='"+tag+"'");
			set.next();
			int TagID=set.getInt("ID");
			set = db.Select("Tag_User","TagID='"+TagID+"'");
			while(set.next()) {
				users.add(null);/////////
			}
		}
		return users;
	}

	public int getUserID(User user,DataBase db) throws SQLException {
		ResultSet set = db.Select("Users","LastName='"+user.lastName+"' AND Email = '"+user.email+"'");
		if(set.next()) {
			return set.getInt("ID");
		}
		return 0;
	}

}

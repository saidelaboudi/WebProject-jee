package ensias.teams.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ensias.teams.buzinessLayer.User;

/**
 * UserDAO
 */
public class UserDAO {

    
	public void addUser(User user,DataBase db) throws SQLException {
		String sql="INSERT INTO Users (FirstName,LastName,Address,Birthday,Email) VALUES (?,?,?,?,?)";
        PreparedStatement statement = db.connection.prepareStatement(sql);
        statement.setString(1,user.firstName);
        statement.setString(2,user.lastName);
        statement.setString(3,user.address);
        statement.setString(4,user.birthday);
        statement.setString(5,user.email);
        statement.execute();
	}
	
// has not ended yet
	public void addExcell2Depart(String excelFilePath , DataBase db) throws IOException, SQLException {
		
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		 
        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet firstSheet = (Sheet) workbook.getSheetAt(0);
        Iterator<Row> rowIterator = firstSheet.iterator();

        String sql = "INSERT INTO User (NAME,CheifID) VALUES (?, ?)";
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
                    String name = nextCell.getStringCellValue();
                    statement.setString(1, name);
                    break;
                case 1:
                	int chiefID = (int) nextCell.getNumericCellValue();
                	statement.setInt(2, chiefID);            
                	break;
                }
            }
        }
        workbook.close();
         
        // execute the remaining queries
        statement.executeBatch();
		
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
		ResultSet set = db.Select("User","LastName='"+user.lastName+"' AND Email = '"+user.email+"'");
		set.next();
		return set.getInt("ID");
	}

}
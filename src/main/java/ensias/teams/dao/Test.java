package ensias.teams.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ensias.teams.buzinessLayer.Group;
import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;

public class Test {
	public static void main(String[] args) throws SQLException, IOException {
		DataBase db = new DataBase("localhost","3306","teams","root","root");
		User owner = new User("James3", "Bandel3", "23, rue des keyboard , clavier, Pc ","12-19-20","java2@jee.oracle");
		Team team = new Team("test4", owner);
		Group groupe = new Group("Teams ", "Groupe pour rassembler des equipes en un seul grps");
		groupe.owner= owner;
		Tag tag = new Tag("Gl1", owner);
		
		
		TeamDAO addTeam = new TeamDAO();
		UserDaoImpl addUser = new UserDaoImpl(null);
		GroupDaoImpl addGroup = new GroupDaoImpl(null);
		TagDAO addtag = new TagDAO();
		
		
		/*
		addUser.addUser(owner, db);
		addTeam.addTeam(team, db);
		addGroup.addGroup(groupe, db);
		addtag.addTag_User(tag, db);
		int teamID = addTeam.getTeamID(team, db);
		int UserID = addUser.getUserID(owner, db);
		addTeam.addTeam_Member(teamID, UserID, db);
		*/
		
		String ExcelPath ="C:/Users/Said/Desktop/Users.xlsx ";
		addUser.addExcell2Depart(ExcelPath, db);
	}
}

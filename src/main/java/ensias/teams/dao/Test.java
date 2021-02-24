package ensias.teams.dao;

import java.io.*;
import java.sql.*;
import java.util.*;


import ensias.teams.buzinessLayer.Group;
import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;

public class Test {
	
	private static final String URL="jdbc.url";
	private static final String DRIVER="jdbc.driverClass";
	private static final String LOGIN="jdbc.login";
	private static final String PASSWORD="jdbc.password";
	private static final String FICHIER_PROPERTIES = "/dao.properties";
	private static final String SCHEMA = "jdbc.schema";
	private static final String CONF_DAO_FACTORY = "daofactory";
	private static DAOFactory db =  new DAOFactory(URL, LOGIN, PASSWORD, SCHEMA);
	
	public static void main(String[] args) throws SQLException, IOException {

		User owner = new User("James3", "Bandel3", "23, rue des keyboard , clavier, Pc ","12-19-20","java2@jee.oracle");
		Team team = new Team("test4", owner);
		Group groupe = new Group("Teams ", "Groupe pour rassembler des equipes en un seul grps");
		groupe.owner= owner;
		Tag tag = new Tag("GL1", owner);
		
		

		TeamDAOImp addTeam = new TeamDAOImp();
		UserDaoImpl addUser = new UserDaoImpl(null);
		GroupDaoImpl addGroup = new GroupDaoImpl(null);
		TagDAOImp addtag = new TagDAOImp();
		
		
		for(Tag tag2 :addtag.getTagList(db)) {
			System.out.println(tag2.tagName);
			for(User user : addUser.getUsersByTag(tag2)) {
				System.out.println(user.toString());
			}
		}
		
		
		/*
		String ExcelPath ="C:/Users/Said/Desktop/Users.xlsx ";
		
		ArrayList<User> users = addUser.addExcell2Depart(ExcelPath);
		/*
		 // On verifie la liste et on ajout les nouveau memebre a la bvase de donnee
		for(User user:users) {
			if(addUser.getUserID(user, db)==0) { // l utilisation n'est pas inscrit dans le system
				addUser.addUser(user, db);
			}else {
				System.out.println(user.toString());
			}
		}
		// Ajjouter les memebre au team
		for(User user:users) {
			//addTeam.addTeam_Member(NewTeam, user, db);
			System.out.println(user.toString());
		}
		
		*/
		/*
		Tag tag1 = new Tag("Test", owner);
		
		addtag.addTag(tag1, db);
		/*
		for(User user : users) {
			tag1.addMember(user);			
		}*/
		/*
		for(int i =130;i<140;i++) {
			tag1.addMember(addUser.getUserByID(i));
		}
		addtag.addTag_User(tag1, db);
		
		*/
		
		
		
		/*
		ArrayList<User> users=addUser.getUsersByTag(tag1, db);
		
		for(User user: users) {
			System.out.println(user.toString());
		}
		*/
		
		/*
		addTeam.addTeam(team, db);
		addGroup.addGroup(groupe, db);
		addtag.addTag_User(tag, db);
		int teamID = addTeam.getTeamID(team, db);
		int UserID = addUser.getUserID(owner, db);
		addTeam.addTeam_Member(teamID, UserID, db);
		*/
		
		//addUser.addUser(owner, db); 
		
		//Scanner sc = new Scanner(System.in);
		//Creation du team
		//User owner = new User("James3", "Bandel3", "23, rue des keyboard , clavier, Pc ","12-19-20","java2@jee.oracle");
		//String TeamName = sc.nextLine();
		//String Descrption = sc.nextLine();
		//Team NewTeam = new Team(TeamName, owner);
		//addTeam.addTeam(NewTeam, db);
	
	}
}

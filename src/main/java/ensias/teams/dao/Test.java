package ensias.teams.dao;

import java.io.*;
import java.sql.*;
import java.util.*;


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
		addTeam.addTeam(team, db);
		addGroup.addGroup(groupe, db);
		addtag.addTag_User(tag, db);
		int teamID = addTeam.getTeamID(team, db);
		int UserID = addUser.getUserID(owner, db);
		addTeam.addTeam_Member(teamID, UserID, db);
		*/
		
		addUser.addUser(owner, db); 
		
		Scanner sc = new Scanner(System.in);
		//Creation du team
		//User owner = new User("James3", "Bandel3", "23, rue des keyboard , clavier, Pc ","12-19-20","java2@jee.oracle");
		//String TeamName = sc.nextLine();
		//String Descrption = sc.nextLine();
		//Team NewTeam = new Team(TeamName, owner);
		//addTeam.addTeam(NewTeam, db);
		
		String ExcelPath ="C:/Users/Said/Desktop/Users.xlsx ";
		
		ArrayList<User> users = addUser.addExcell2Depart(ExcelPath);
		
		 // On verifie la liste et on ajout les nouveau memebre a la bvase de donnee
		/*for(User user:users) {
			if(addUser.getUserID(user, db)==0) { // l utilisation n'est pas inscrit dans le system
				addUser.addUser(user, db);
			}else {
				System.out.println(user.toString());
			}
			NewTeam.addMember(user);
		}*/
		// Ajjouter les memebre au team
		for(User user:users) {
			//addTeam.addTeam_Member(NewTeam, user, db);
			System.out.println(user.toString());
		}
		
		
	}
}

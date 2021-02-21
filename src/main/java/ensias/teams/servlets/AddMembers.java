package ensias.teams.servlets;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.*;
import ensias.teams.dao.DataBase;
import ensias.teams.dao.GroupDaoImpl;
import ensias.teams.dao.TagDAO;
import ensias.teams.dao.TeamDAO;
import ensias.teams.dao.UserDaoImpl;

/**
 * Servlet implementation class AddMembers
 */
@WebServlet("/AddMembers")
@MultipartConfig
public class AddMembers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User owner = new User("James3", "Bandel3", "23, rue des keyboard , clavier, Pc ","12-19-20","java2@jee.oracle");
	Team NewTeam = new Team("Team test 2", owner);
	TeamDAO addTeam = new TeamDAO();
	
	UserDaoImpl addUser = new UserDaoImpl(null);
	GroupDaoImpl addGroup = new GroupDaoImpl(null);
	TagDAO addtag = new TagDAO();
	DataBase db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMembers() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
        this.getServletContext().getRequestDispatcher("/WEB-INF/team.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		// Add By Excell 
		
		
		
		try {
			
			db = new DataBase("localhost","3306","teams","root","root");
			
			
			
			//addUser.addUser(owner, db);
			//addTeam.addTeam(NewTeam, db);    
			
			Part filePart = request.getPart("Excellpath");
			
			if(filePart!=null) {
				InputStream inputStream =filePart.getInputStream();
				ArrayList<User> users = addUser.addExcell2Depart(inputStream);
				
				if(users.size()>0) {
					// On verifie la liste et on ajout les nouveau memebre a la bvase de donnee
					for(User user:users) {
						if(addUser.getUserID(user, db)==0) { // l utilisation n'est pas inscrit dans le system
							addUser.addUser(user, db);
							NewTeam.addMember(user);
						}else {
							System.out.println(user.toString());
						}
						NewTeam.addMember(user);
					}
					// Ajjouter les memebre au team
					for(User user:users) {
						addTeam.addTeam_Member(NewTeam, user, db);
						System.out.println("----"+user.toString());
					}
					
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Part filePart = request.getPart("Excellpath");
			if(filePart!=null) {
				InputStream inputStream =filePart.getInputStream();
				ArrayList<User> users;
				users = addUser.addExcell2Depart(inputStream);
				request.setAttribute("user", users);				
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Classical Added 
		
		String email = (String) request.getAttribute("addByEmail");
		if(email!=null) {
			User user = new User(null,null,null,null,email);
			System.out.println(email);
			try {
				addTeam.addTeam_Member(NewTeam, user, db);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}
}

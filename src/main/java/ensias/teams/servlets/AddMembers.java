package ensias.teams.servlets;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.*;
import ensias.teams.dao.*;

/**
 * Servlet implementation class AddMembers
 */
@WebServlet("/AddMembers")
@MultipartConfig
public class AddMembers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory daoF =  DAOFactory.getInstance();
	
	
	
	// Team session
	Team NewTeam ;
	
	
	TeamDAOImp addTeam = new TeamDAOImp();
	
	UserDaoImpl addUser = new UserDaoImpl();
	GroupDaoImpl addGroup = new GroupDaoImpl(daoF);
	TagDAOImp addtag = new TagDAOImp();
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
		

		HttpSession session = request.getSession(true);
		User owner = (User)session.getAttribute("CurrentUser");
		
		NewTeam =(Team)session.getAttribute("TeamName");
		ArrayList<User> users = new ArrayList<User>();
		
		
		
		
		try {
			
			addUser.addUser(owner, daoF);
			//addTeam.addTeam(NewTeam, daoF);   
			
			// Add By Excell 
			Part filePart = request.getPart("Excellpath");
			
			//Added by tag
			String[] tagName = request.getParameterValues("TagSelected");
			
			// Remplir la liste des memebres selon la methode choisie
			
			if(tagName!=null) {
				ArrayList<User> userList = new ArrayList<User>();
				/*if(session.getAttribute("TagList")==null)
					session.setAttribute("TagList", tagName );*/
				for(int i=0;i<tagName.length;i++) {
					////system.out.println(tagName[i]);
					userList = addtag.getUsersTagged(tagName[i], daoF);
					users.addAll(userList);
				}
			}else if(filePart!=null) {// Si l'utilisateur a choisie de creere par une etiquette
				InputStream inputStream =filePart.getInputStream();
				users = addUser.addExcell2Depart(inputStream);
			}
			
			
			
			// Ajouter les membres selectionne a l''equipe
			if(users!=null) {
				// On verifie la liste et on ajout les nouveau memebre a la bvase de donnee
				for(User user:users) {
					if(addUser.getUserID(user, daoF)==0) { // l utilisation n'est pas inscrit dans le //system
						addUser.addUser(user, daoF);
						NewTeam.addMember(user);
					}else {
						////system.out.println(user.toString());
					}
					NewTeam.addMember(user);
				}
				// Ajjouter les memebre au team
				for(User user:users) {
					addTeam.addTeam_Member(NewTeam, user, daoF);
					//system.out.println("----"+user.toString());
				}
			}
			
			/*
			for(User user: users) {
				//system.out.println(user.toString());
				//system.out.println(addUser.getUserID(user, daoF));
			}
			*/
			//ArrayList<User> users = (ArrayList<User>) session.getAttribute("Users");
			// Send users List !! 
			
			
			
			session.setAttribute("usersListTag", users);
			
			/*
		String[] tagName=(String[])request.getParameterValues("SelectOption");
		if(tagName!=null)
		for(int i=0;i<tagName.length;i++) {
			//system.out.println(tagName[i]);
		}
			 */
			/*
		if(tagName!=null) {
			ArrayList<User> users;
			users = addUser.getUsersByTag(new Tag(tagName,null), daoF);
			request.setAttribute("user", users);
		}*/
			
			
			// Classical Added 
			
			String email = (String) request.getAttribute("addaoFyEmail");
			if(email!=null) {
				User user = new User(null,null,null,null,email);
				////system.out.println(email);
				addTeam.addTeam_Member(NewTeam, user, daoF);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
}
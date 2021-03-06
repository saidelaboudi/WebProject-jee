package ensias.teams.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;
import ensias.teams.dao.DAOFactory;
import ensias.teams.dao.GroupDaoImpl;
import ensias.teams.dao.TagDAOImp;
import ensias.teams.dao.TeamDAOImp;
import ensias.teams.dao.UserDaoImpl;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOFactory daoF =  DAOFactory.getInstance();
	
	
	TeamDAOImp addTeam = new TeamDAOImp();
	
	UserDaoImpl addUser = new UserDaoImpl();
	GroupDaoImpl addGroup = new GroupDaoImpl(daoF);
	TagDAOImp addtag = new TagDAOImp();
    /**
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		User user  = (User)session.getAttribute("CurrentUser");
		ArrayList<Team> teams =new ArrayList<Team>();
		try {
			teams=addUser.getTeamsByUser(user, daoF);
			//System.out.println("---------------------"+teams.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("MyTeams",teams);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

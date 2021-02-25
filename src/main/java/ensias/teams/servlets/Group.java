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
 * Servlet implementation class Group
 */
@WebServlet("/Group")
public class Group extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONF_DAO_FACTORY = "daofactory";
	private DAOFactory daoF =  DAOFactory.getInstance();
	
	
	TeamDAOImp addTeam = new TeamDAOImp();
	
	UserDaoImpl addUser = new UserDaoImpl(daoF);
	GroupDaoImpl addGroup = new GroupDaoImpl(daoF);
	TagDAOImp addtag = new TagDAOImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Group() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			HttpSession session = request.getSession(true);
			
			User owner = (User)session.getAttribute("CurrentUser");
			String GroupName = (String) request.getParameter("GroupSelected");
			//System.out.println("From Group"+GroupName);
			ensias.teams.buzinessLayer.Group group = addGroup.getGroupByName(GroupName, daoF);
			//ensias.teams.buzinessLayer.Group group = (ensias.teams.buzinessLayer.Group) session.getAttribute("GroupSelected");
			//session.setAttribute("GroupSelected", group);
			//(ensias.teams.buzinessLayer.Group) session.getAttribute("GroupSelected");
			ArrayList<Team> teamsList = new ArrayList<Team>();
			teamsList=addGroup.getTeamsByGroup(group,daoF);
			//System.out.println(teamsList.size());
			session.setAttribute("TeamList", teamsList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        this.getServletContext().getRequestDispatcher("/WEB-INF/Group.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}

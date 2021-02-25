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

import ensias.teams.buzinessLayer.User;
import ensias.teams.buzinessLayer.Team;
import ensias.teams.dao.DAOFactory;
import ensias.teams.dao.GroupDaoImpl;
import ensias.teams.dao.TagDAOImp;
import ensias.teams.dao.TeamDAOImp;
import ensias.teams.dao.UserDaoImpl;
import ensias.teams.buzinessLayer.Group;

/**
 * Servlet implementation class GroupServlet
 */
@WebServlet("/GroupServlet")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONF_DAO_FACTORY = "daofactory";
	public DAOFactory daoF;
	
	public TeamDAOImp addTeam = new TeamDAOImp();
	public UserDaoImpl addUser = new UserDaoImpl(null);
	public GroupDaoImpl addGroup = new GroupDaoImpl(null);
	public TagDAOImp addtag = new TagDAOImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//
		
		try {

			HttpSession session = request.getSession(true);
			
			ArrayList<Team> teams = addTeam.getTeamList(daoF);
			
			session.setAttribute("teamsList", teams);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        this.getServletContext().getRequestDispatcher("/WEB-INF/addTeams.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		daoF =  (DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY);
		
		String GroupName = (String)request.getParameter("groupName");
		
		System.out.println(GroupName);
		HttpSession session = request.getSession(true);
		try {
			User owner = (User) session.getAttribute("CurrentUser");
			Group group = new Group(GroupName, null);
			group.owner=owner;
			addGroup.addGroup(group, daoF);
			session.setAttribute("CurrentGroup", group);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}

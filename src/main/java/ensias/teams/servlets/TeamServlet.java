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
import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.User;
import ensias.teams.dao.DAOFactory;
import ensias.teams.dao.GroupDaoImpl;
import ensias.teams.dao.TagDAOImp;
import ensias.teams.dao.TeamDAO;
import ensias.teams.dao.TeamDAOImp;
import ensias.teams.dao.UserDaoImpl;

/**
 * Servlet implementation class TeamServlet
 */
@WebServlet("/TeamServlet")
public class TeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	public DAOFactory daoF;
	
	public TeamDAOImp addTeam = new TeamDAOImp();
	public UserDaoImpl addUser = new UserDaoImpl();
	public GroupDaoImpl addGroup = new GroupDaoImpl(null);
	public TagDAOImp addtag = new TagDAOImp();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public TeamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		daoF =  (DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY);
        this.getServletContext().getRequestDispatcher("/WEB-INF/team.jsp").forward( request, response );
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		daoF =  (DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY);
		String TeamName = (String)request.getParameter("teamName");

		HttpSession session = request.getSession(true);
		
		User owner = (User)session.getAttribute("CurrentUser");
		
		Team NewTeam = new Team(TeamName, owner);
		
		TeamDAO addTeam = new TeamDAOImp();
		


		try {
			
			if(TeamName!=null)
				addTeam.addTeam(NewTeam, daoF);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Tag> TagList = null;
		
		try {
			TagList = addtag.getTagList(daoF, owner);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("TeamName", NewTeam );
		session.setAttribute("TagList", TagList);
		
		doGet(request, response);
	}
}

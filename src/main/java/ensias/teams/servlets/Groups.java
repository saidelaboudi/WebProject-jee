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

import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;
import ensias.teams.dao.DAOFactory;
import ensias.teams.dao.GroupDaoImpl;
import ensias.teams.dao.TagDAOImp;
import ensias.teams.dao.TeamDAO;
import ensias.teams.dao.TeamDAOImp;
import ensias.teams.dao.UserDaoImpl;

/**
 * Servlet implementation class Groups
 */
@WebServlet("/Groups")
public class Groups extends HttpServlet {
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
    public Groups() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		daoF =  (DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY);
		HttpSession session = request.getSession(true);
		
		ArrayList<ensias.teams.buzinessLayer.Group> GroupList = null;
		
		try {
			GroupList = addGroup.getGroupList(daoF);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("GroupList", GroupList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/Groups.jsp").forward( request, response );
       }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		daoF =  (DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY);
		
		String GroupName = (String)request.getParameter("GroupName");

		System.out.println("From Groups"+GroupName);
		HttpSession session = request.getSession(true);
		
		User owner = (User)session.getAttribute("CurrentUser");
		
		ensias.teams.buzinessLayer.Group group = null ;

		try {
			
			if(GroupName!=null)
				group = new ensias.teams.buzinessLayer.Group(GroupName, GroupName);
				group.owner=owner;
				session.setAttribute("GroupSelected", group);
				addGroup.addGroup(group, daoF);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<ensias.teams.buzinessLayer.Group> GroupList = null;
		
		try {
			GroupList = addGroup.getGroupList(daoF);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("GroupName", group );
		session.setAttribute("GroupList", GroupList);
		doGet(request, response);
	}

}

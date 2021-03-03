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
 * Servlet implementation class ShowTeam
 */
@WebServlet("/ShowTeam")
public class ShowTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory daoF = DAOFactory.getInstance();
	TeamDAOImp addTeam = new TeamDAOImp();
		
	UserDaoImpl addUser = new UserDaoImpl();
	GroupDaoImpl addGroup = new GroupDaoImpl(daoF);
	TagDAOImp addtag = new TagDAOImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTeam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		Team team = null ;
			try {
				if((String)request.getParameter("TeamName")!=null){
					String Name= (String)request.getParameter("TeamName");
					team=addTeam.getTeamByID(addTeam.getTeamID(new Team(Name, null), daoF), daoF);
					session.setAttribute("TeamName", team);
				}else {
					team=(Team) session.getAttribute("TeamName");			
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//session.setAttribute("TeamName",Name);
		try {
			TeamDAOImp addTeam = new TeamDAOImp();
			ArrayList<User> users =addTeam.getUsersByTeamName(team.name,daoF);
			session.setAttribute("TeamMembers", users );
			/*for(User user: users) {
				System.out.println(user.toString());
			}
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.getServletContext().getRequestDispatcher("/WEB-INF/ShowTeam.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			String email = (String)request.getParameter("email");
			int UserID = addUser.getUserID(new User(null,null,null,null, email), daoF);
			//System.out.println(UserID);
			User user = addUser.getUserByID(UserID, daoF);
			HttpSession session = request.getSession(true);
			//Team team = (Team) session.getAttribute("TeamName");
			Team team = (Team) session.getAttribute("TeamName");
			
			addTeam.addTeam_Member(new Team(team.name, user), user, daoF);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}

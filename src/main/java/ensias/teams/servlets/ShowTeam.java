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
import ensias.teams.dao.TeamDAOImp;

/**
 * Servlet implementation class ShowTeam
 */
@WebServlet("/ShowTeam")
public class ShowTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONF_DAO_FACTORY = "daofactory";
	private DAOFactory daoF = DAOFactory.getInstance();
       
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
		Team team = (Team) session.getAttribute("TeamName");
		String Name=team.name;
		try {
			TeamDAOImp addTeam = new TeamDAOImp();
			ArrayList<User> users =addTeam.getUsersByTeamName(Name,daoF);
			
			session.setAttribute("TeamMembers", users );
			for(User user: users) {
				System.out.println(user.toString());
			}
			
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

		
		doGet(request, response);
	}

}

package ensias.teams.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;
import ensias.teams.dao.DataBase;
import ensias.teams.dao.TeamDAO;
import ensias.teams.dao.TeamDAOImp;
import ensias.teams.dao.UserDaoImpl;

/**
 * Servlet implementation class TeamServlet
 */
@WebServlet("/TeamServlet")
public class TeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/team.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TeamName = (String)request.getParameter("teamName");
		
		User owner = new User("James3", "Bandel3", "23, rue des keyboard , clavier, Pc ","12-19-20","java2@jee.oracle");
		
		Team NewTeam = new Team(TeamName, owner);
		
		TeamDAO addTeam = new TeamDAOImp();
		DataBase db;
		try {
			db = new DataBase("localhost","3306","teams","root","root");
			
			if(TeamName!=null)
				addTeam.addTeam(NewTeam, db);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("TeamName", NewTeam );
		
		doGet(request, response);
	}
}

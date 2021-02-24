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
import ensias.teams.dao.DataBase;
import ensias.teams.dao.TeamDAOImp;

/**
 * Servlet implementation class ShowTeam
 */
@WebServlet("/ShowTeam")
public class ShowTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTeam() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Team team = (Team) session.getAttribute("TeamName");
		String Name=team.name;
		DataBase db;
		try {
			TeamDAOImp addTeam = new TeamDAOImp();
			db = new DataBase("localhost","3306","teams","root","root");
			ArrayList<User> users =addTeam.getUsersByTeamName(Name,db);
			
			session.setAttribute("TeamMembers", users );
			for(User user: users) {
				System.out.println(user.toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        this.getServletContext().getRequestDispatcher("/WEB-INF/ShowTeam.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

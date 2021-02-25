package ensias.teams.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import ensias.teams.buzinessLayer.Team;
import ensias.teams.buzinessLayer.User;
import ensias.teams.buzinessLayer.Group;
import ensias.teams.dao.DAOFactory;
import ensias.teams.dao.GroupDaoImpl;
import ensias.teams.dao.TagDAOImp;
import ensias.teams.dao.TeamDAOImp;
import ensias.teams.dao.UserDaoImpl;

/**
 * Servlet implementation class AddTeams
 */
@WebServlet("/AddTeams")
public class AddTeams extends HttpServlet {
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
    public AddTeams() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
        this.getServletContext().getRequestDispatcher("/WEB-INF/Groups.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String[] TeamName = request.getParameterValues("TeamsSelected");
		
		
		HttpSession session = request.getSession(true);
		Group groupe = (Group) session.getAttribute("GroupSelected");
		
		try {
			
			System.out.println("---------------------------");
			for(int i = 0; i< TeamName.length ; i++) {
				System.out.println(TeamName[i]);
			}
			ArrayList<Team> teamsList = new ArrayList<Team>();
			if(TeamName!=null) {
				for(int i=0;i<TeamName.length;i++) {
					System.out.println(TeamName[i]);
					int teamID = addTeam.getTeamID(new Team(TeamName[i], null), daoF);
					System.out.println(teamID);
					if(teamID>0)
						teamsList.add(addTeam.getTeamByID(teamID, daoF));
				}
			}
			addGroup.addTeamsGroup(groupe,teamsList,daoF);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}

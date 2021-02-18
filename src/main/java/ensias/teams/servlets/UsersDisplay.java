package ensias.teams.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ensias.teams.buzinessLayer.User;
import ensias.teams.dao.DAOFactory;

/**
 * @author Yasser
 *
 */
@WebServlet("/UsersDisplay")
public class UsersDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONF_DAO_FACTORY = "daofactory";
	private static final String VUE = "/WEB-INF/usersDisplay.jsp";
	
	
    public UsersDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoF =  (DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY);
		
		ArrayList<User> users = daoF.getUserDao().bringAllUsers();
		request.setAttribute("users",users);
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

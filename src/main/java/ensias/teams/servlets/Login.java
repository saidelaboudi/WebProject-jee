
package ensias.teams.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ensias.teams.buzinessLayer.User;


/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONF_DAO_FACTORY = "daofactory";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("CurrentUser");
    	if (user != null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward( request, response );
		}
		else {
	        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward( request, response );
	}
    	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass = (String)request.getParameter("pass");
		String email = (String)request.getParameter("email");
		
		ensias.teams.dao.DAOFactory daoF =  (ensias.teams.dao.DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY);
		ensias.teams.buzinessLayer.User user = daoF.getUserDao().bringUser(email);
		
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("CurrentUser", user);
			session.setAttribute("_SESSION", user);
			request.setAttribute("user", user);

			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward( request, response );
		}
		else {
	        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward( request, response );
		}
	}

}

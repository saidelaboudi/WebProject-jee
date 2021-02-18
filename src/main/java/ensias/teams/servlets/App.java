package ensias.teams.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ensias.teams.buzinessLayer.User;
import ensias.teams.dao.DAOFactory;

/**
 * Servlet implementation class test
 */
@WebServlet("/App")
public class App extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONF_DAO_FACTORY = "daofactory";
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public App() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/signIn.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass = (String)request.getParameter("pass");	String cpass = (String)request.getParameter("cpass");
		String email = (String)request.getParameter("email");
		
		DAOFactory daoF =  (DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY);
		User user = daoF.getUserDao().bringUser(email, pass);
		
		if (pass.equals(cpass) && user == null) {
			ensias.teams.buzinessLayer.User user1 = new ensias.teams.buzinessLayer.User(
					(String)request.getParameter("firstname"), (String)request.getParameter("lastname"),
					(String)request.getParameter("adresse"), email, pass)
					;
			daoF.getUserDao().addUser(user1);
			request.setAttribute("user", user1);
			this.getServletContext().getRequestDispatcher("/WEB-INF/hello.jsp").forward( request, response );
		}
		else {
	        this.getServletContext().getRequestDispatcher("/WEB-INF/signIn.jsp").forward( request, response );
		}
	}

}

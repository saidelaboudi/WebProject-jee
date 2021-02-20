package ensias.teams.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class test
 */
@WebServlet("/inscription")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONF_DAO_FACTORY = "daofactory";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass = (String)request.getParameter("pass");	String cpass = (String)request.getParameter("cpass");
		String email = (String)request.getParameter("email");
		
		ensias.teams.dao.DAOFactory daoF =  (ensias.teams.dao.DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY);
		ensias.teams.buzinessLayer.User user = daoF.getUserDao().bringUser(email, pass);
		
		if (pass.equals(cpass) && pass != null && user == null) {
			ensias.teams.buzinessLayer.User user1 = new ensias.teams.buzinessLayer.User(
					(String)request.getParameter("firstname"), (String)request.getParameter("lastname"),
					(String)request.getParameter("adresse"), pass, email)
					;
			daoF.getUserDao().addUser(user1);
			request.setAttribute("user", user1);
			this.getServletContext().getRequestDispatcher("/WEB-INF/hello.jsp").forward( request, response );
		}
		else {
	        this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward( request, response );
		}
	}

}

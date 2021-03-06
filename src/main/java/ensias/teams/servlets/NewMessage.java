package ensias.teams.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ensias.teams.buzinessLayer.ChatPersoUser;
import ensias.teams.buzinessLayer.User;

/**
 * Servlet implementation class NewMessage
 */
@WebServlet("/NewMessage")
public class NewMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("NewMessage.doPost()");
		Long id = Long.parseLong(request.getParameter("id"));
		String contenu = request.getParameter("content");
		
		if ( id != null && contenu != null && id > 0l && contenu.length() != 0 ) {
			//System.out.println("jai recu " + id + contenu);
			User user = (User)  request.getSession().getAttribute("_SESSION");	
			ensias.teams.dao.DAOFactory daoF =  (ensias.teams.dao.DAOFactory)getServletContext().getAttribute("daofactory");
			//insert the new message
			daoF.getMessagePersoDao().insertMessagePerso(user.id, id, contenu);
		}
		
	}

}

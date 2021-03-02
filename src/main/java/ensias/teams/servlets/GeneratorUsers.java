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
import ensias.teams.buzinessLayer.Message;
import ensias.teams.buzinessLayer.User;

@WebServlet("/GeneratorUsers")
public class GeneratorUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GeneratorUsers.doPost()");
		String value = request.getParameter("value");
		if ( value != null ) {
			User user = (User)  request.getSession().getAttribute("_SESSION");
			List<User> messageriesUsers = new ArrayList<User>();
			ensias.teams.dao.DAOFactory daoF =  (ensias.teams.dao.DAOFactory)getServletContext().getAttribute("daofactory");
			messageriesUsers = daoF.getUserDao().bringUsersHavingValue(user,value);
			String str ="<users>";
			for ( User u : messageriesUsers ) {
				str += "<user id='" +u.id +"' firstname = '"+u.firstName+"' lastname='"+u.lastName+"' email='"+u.email+"'   >";
				str += "</user>";
			}
			str += "</users>";
			
			System.out.println(str);
			response.getWriter().print(str);
		}
		
		
	}
}

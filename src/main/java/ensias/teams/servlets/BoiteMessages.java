package ensias.teams.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ensias.teams.buzinessLayer.ChatPersoUser;
import ensias.teams.buzinessLayer.Message;
import ensias.teams.buzinessLayer.User;

/**
 * Servlet implementation class BoiteMessages
 */
@WebServlet("/BoiteMessages")
public class BoiteMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//system.out.println("BoiteMessages.doGet()");
		User user = (User)  request.getSession().getAttribute("_SESSION");
		if ( user == null ) {
			request.getRequestDispatcher("/login").forward(request, response);
		}
		else {
			List messageriesUsers = new ArrayList<ChatPersoUser>();
			ensias.teams.dao.DAOFactory daoF =  (ensias.teams.dao.DAOFactory)getServletContext().getAttribute("daofactory");
			
			messageriesUsers = daoF.getUserDao().bringAllMessagerie(user);
			request.setAttribute("usersMessagerie",messageriesUsers );
			
			request.getRequestDispatcher("WEB-INF/BoiteMessages.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//system.out.println("BoiteMessages.doPost()");
		Long id = Long.decode(request.getParameter("maxid"));
		//system.out.println("max id =" + id);
		//
		User user = (User)  request.getSession().getAttribute("_SESSION");
		List<ChatPersoUser> messageriesUsers = new ArrayList<ChatPersoUser>();
		ensias.teams.dao.DAOFactory daoF =  (ensias.teams.dao.DAOFactory)getServletContext().getAttribute("daofactory");
		messageriesUsers = daoF.getUserDao().bringAllMessagerie(user,id);
		String str ="<users>";
		for ( ChatPersoUser u : messageriesUsers ) {
			str += "<user id='" +u.getOtherUser().id +"' firstname = '"+u.getOtherUser().firstName+"' lastname='"+u.getOtherUser().lastName+"' email='"+u.getOtherUser().email+"'   >";
			for ( Message m : u.getMessages()) {
				str += "<message id='"+m.id+"' date=\""+m.date+"\"";
				if ( user.id == m.idSender) {
					str += " side = 'me'";
				}else {
					str += " side = 'him'";
				}
			    str+=  ">" + m.content+" </message>";	
			}
			str += "</user>";
		}
		str += "</users>";
		//system.out.println(str);
		response.getWriter().print(str);
	
	
		
		
		
		
		
	}

}

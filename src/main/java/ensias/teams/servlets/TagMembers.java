package ensias.teams.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ensias.teams.buzinessLayer.Tag;
import ensias.teams.dao.DAOFactory;
import ensias.teams.dao.TagDAO;
import ensias.teams.dao.TagDAOImp;

/**
 * Servlet implementation class TagMembers
 */
@WebServlet("/TagMembers")
public class TagMembers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONF_DAO_FACTORY = "daofactory";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagMembers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/createTag.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// if we use modifyTag form in createTag.jsp, MtagName is send with request
		if (request.getParameterMap().containsKey("MtagName")) {
			request.setAttribute("tagName", request.getParameter("MtagName"));
			request.setAttribute("firstTime", true);
		}else {
			// refresh tagName attribut
						request.setAttribute("tagName", request.getParameter("tagName"));
			// after we come from createTag.jsp
			// if we use addMember form, Nmember is send with request
			if (request.getParameterMap().containsKey("Nmember")) {
				// recovering the user if he exists
				ensias.teams.dao.DAOFactory daoF =  (ensias.teams.dao.DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY);
				ensias.teams.buzinessLayer.User user = daoF.getUserDao().bringUser(request.getParameter("Nmember"));
				request.setAttribute("member", user);
				if (user != null) {
					// adding the user to tag_users
					DAOFactory db;
					TagDAO dao = new TagDAOImp();					
					try {
						db = DAOFactory.getInstance();
							Tag t = new Tag((String)request.getAttribute("tagName"), user);
							t.addMember(user);
							System.out.println("user" + user.toString());
							System.out.println("tagMem" + t.tagged.toString());
							dao.addTag_User(t, db);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//
			}
			
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifyTag.jsp").forward(request, response);
	}

}

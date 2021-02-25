package ensias.teams.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.User;
import ensias.teams.dao.DAOFactory;
import ensias.teams.dao.TagDAO;
import ensias.teams.dao.TagDAOImp;

/**
 * Servlet implementation class TagMembers
 */
@WebServlet("/ModifyTags")
public class ModifyTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final String CONF_DAO_FACTORY = "daofactory";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyTags() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/createTag.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tagName = request.getParameter("tagName");
		System.out.println(tagName);
		//User owner = (User) request.getSession().getAttribute("_SESSION");
		User owner = new User("a", "b", "ab", "ab", "a@b.c");
		request.getSession().setAttribute("_SESSION", owner);
		try {
			Tag tag = new Tag(tagName, owner);
			TagDAO dao = new TagDAOImp();
			request.setAttribute("_TAG", tag);
			System.out.println(!request.getParameterMap().containsKey("marker"));

			if (!request.getParameterMap().containsKey("marker")) {
				// if request does not have key marker then this is not first time visiting from create jsp, so members are added 	
				String mail = (String) request.getParameter("Nmember");
				System.out.println(mail);

				DAOFactory db = DAOFactory.getInstance();
				User added = db.getUserDao().bringUser(mail);
				
					if (added != null) {
						dao.addTag_User(tag, db, mail);
						tag.addMember(added);
					}
					
				request.setAttribute("member", added);
			}
			else {
				request.setAttribute("marker", true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifyTag.jsp").forward(request, response);
	}

}

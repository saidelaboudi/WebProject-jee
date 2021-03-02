package ensias.teams.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ensias.teams.buzinessLayer.Tag;
import ensias.teams.buzinessLayer.User;
import ensias.teams.dao.DAOFactory;
import ensias.teams.dao.TagDAO;
import ensias.teams.dao.TagDAOImp;

/**
 * Servlet implementation class CreateTags
 */
@WebServlet("/CreateTags")
public class CreateTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTags() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession(true);
		
		User owner = (User)session.getAttribute("CurrentUser");
		
		request.getSession().setAttribute("_SESSION", owner);

        this.getServletContext().getRequestDispatcher("/WEB-INF/createTag.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tagName = request.getParameter("tagName");
		//User owner = (User) request.getSession().getAttribute("_SESSION");
		User owner = new User("a", "b", "ab", "ab", "a@b.c");
		System.out.println(owner.toString());
		request.getSession().setAttribute("_SESSION", owner);
		Tag t = null;
		try {
			t = new Tag(tagName, owner);
			
			TagDAO dao = new TagDAOImp();			
			try {
				dao.addTag(t, DAOFactory.getInstance());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/createTag.jsp").forward( request, response );

	}

}

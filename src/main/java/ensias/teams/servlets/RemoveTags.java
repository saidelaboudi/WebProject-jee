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
 * Servlet implementation class RemoveTags
 */
@WebServlet("/RemoveTags")
public class RemoveTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveTags() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/createTag.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tagName = request.getParameter("tagName");
		//User owner = (User) request.getSession().getAttribute("_SESSION");
		User owner = new User("a", "b", "ab", "ab", "a@b.c");
		request.getSession().setAttribute("_SESSION", owner);
		Tag t = null;
		try {
			t = new Tag(tagName, owner);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TagDAO dao = new TagDAOImp();
		
		try {
			dao.removeTag(t, DAOFactory.getInstance());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/createTag.jsp").forward( request, response );

	}

}

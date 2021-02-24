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
import ensias.teams.dao.DataBase;
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
        this.getServletContext().getRequestDispatcher("/WEB-INF/createTag.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataBase db;
		TagDAO dao = new TagDAOImp();
		User o = new User("a", "b", "c", "d", "e");
		
		try {
			db = new DataBase("localhost","3306","ensiasteams","root","root");
			System.out.println(request.getParameter("DtagName")+ "  11111");
			if (request.getParameterMap().containsKey("DtagName")) {
				Tag t = new Tag(request.getParameter("DtagName"), o);
				dao.removeTag(t, db);
			}else {
				Tag t = new Tag(request.getParameter("tagName"), o);
				dao.addTag(t, db);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/createTag.jsp").forward( request, response );

	}

}

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
import ensias.teams.dao.TagDAOImp;
/**
 * Servlet implementation class Tags
 */
@WebServlet("/CreateTags")
public class CreateTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTags() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        this.getServletContext().getRequestDispatcher("/WEB-INF/createTag.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User owner = new User("a", "b", "c", "d", "e");
		Tag tag = new Tag((String)request.getParameter("tagName"), owner);
		TagDAOImp dao = new TagDAOImp();
		DataBase db;
		try {
			db = new DataBase("localhost","3306","ensiasteams","root","root");
			dao.addTag(tag, db);
			System.out.println("bkabk\nananna\nkfkfkf");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1\n2\n3");

        this.getServletContext().getRequestDispatcher("/WEB-INF/createTag.jsp").forward( request, response );

	}

}
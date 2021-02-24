<%@page
import = "java.util.ArrayList"
import = "ensias.teams.buzinessLayer.Tag"
import = "ensias.teams.dao.TagDAOImp"
import = "ensias.teams.dao.DataBase"

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header>
		<form method="post" action="CreateTags">
			<label id="tag"> Tag Name : </label>
			<input type="text" name="tagName" placeholder="input Tag name" id="tag"/>
			<input type="submit" value="Create Tag"/>
		</form>	
	</header>
	
	<main>
		<%
			DataBase db;
			try{
					db = new DataBase("localhost","3306","ensiasteams","root","root");
				ArrayList<Tag> tags = new TagDAOImp().getTagList(db);
				for (Tag t : tags){
<<<<<<< HEAD
					out.println("<div>" + t.tagName + "</div>");
=======
					out.println("<form>" + t.tagName + "</div>");
>>>>>>> main
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		%>
	</main>
	
</body>
</html>
<%@page
import = "java.util.ArrayList"
import = "ensias.teams.buzinessLayer.Tag"
import = "ensias.teams.dao.TagDAOImp"
<<<<<<< HEAD
import = "ensias.teams.dao.DataBase"
=======
import = "ensias.teams.dao.DAOFactory"
>>>>>>> FirstTry

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
		<form method="post" action="CreateTags">
			<label id="tag"> Tag Name : </label>
			<input type="text" name="DtagName" placeholder="input Tag name" id="tag"/>
			<input type="submit" value="Delete Tag"/>
		</form>	
		<form method="post" action="TagMembers">
			<label id="tag"> Tag Name : </label>
			<input type="text" name="MtagName" placeholder="input Tag name" id="tag"/>
			<input type="submit" value="Modify Members"/>
		</form>	
	</header>
	
	<main>
		<%
<<<<<<< HEAD
			DataBase db;
			try{
					db = new DataBase("localhost","3306","ensiasteams","root","root");
=======
			DAOFactory db;
			try{
					db = DAOFactory.getInstance();
>>>>>>> FirstTry
				ArrayList<Tag> tags = new TagDAOImp().getTagList(db);
				for (Tag t : tags){
		%>
			<div>
				<input type="text" value="<% out.println(t.tagName);%>" disabled>
			</div>
		<%
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		%>
	</main>
	
</body>
</html>
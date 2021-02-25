<%@page
import = "java.util.ArrayList"
import = "ensias.teams.buzinessLayer.Tag"
import = "ensias.teams.buzinessLayer.User"
import = "ensias.teams.dao.TagDAOImp"
import = "ensias.teams.dao.DAOFactory"

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
		<form method="post" action="RemoveTags">
			<label id="tag"> Tag Name : </label>
			<input type="text" name="tagName" placeholder="input Tag name" id="tag"/>
			<input type="submit" value="Delete Tag"/>
		</form>	
		<form method="post" action="ModifyTags">
			<label id="tag"> Tag Name : </label>
			<input type="text" name="tagName" placeholder="input Tag name" id="tag"/>
			<input type="text" name="marker" value="1" style="display:none;">
			<input type="submit" value="Modify Members"/>
		</form>	
	</header>
	
	<main>
		<%
			DAOFactory db = DAOFactory.getInstance();
			try{
				ArrayList<Tag> tags = new TagDAOImp().getTagList(db, (User) request.getSession().getAttribute("_SESSION"));
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
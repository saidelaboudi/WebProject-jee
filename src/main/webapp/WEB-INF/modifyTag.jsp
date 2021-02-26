<%@page
import="ensias.teams.buzinessLayer.User" 
import="ensias.teams.buzinessLayer.Tag" 
import="ensias.teams.dao.DAOFactory"
import = "ensias.teams.dao.TagDAOImp"
import = "java.util.ArrayList"

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<%
			if (request.getAttribute("ownerDeleteError") != null){
		%>
			<script>
				alert("vous ne pouvez pas supprimer votre compte des utilisateur de ce Tag");
			</script>
		<%
			}
			else{
				User member = (User)request.getAttribute("member");
				if (member == null && request.getAttribute("marker") == null){
		%>
				<script>
					alert("le membre que vous avez saisit n'existe pas!");
				</script>
		<%
				}
			}
		
	%>
	<h2> tag name : 
	<%
	Tag tg = (Tag) request.getAttribute("_TAG");
	out.println(tg.tagName +" " + tg.tagId); %> </h2>
	<section>
		<header>
			<div>
				Add Members from Excel
			</div>
			<div>
				Add Members from another Tag
			</div>
			<div>
				<form method='post' action="ModifyTags">
					<input type="text" name="tagName" value='<% out.println(tg.tagName); %>' style='display:none;'>
					<input type="text" name="Nmember" placeholder="Email du membre a ajouter">
					<input type="submit" value="Ajouter">
				</form>
			</div>
		</header>
		
		<main>
			<%
			DAOFactory db = DAOFactory.getInstance();
			try{
				ArrayList<User> members = tg.tagged;
				for (User t : members){
		%>
			<form method="post" action="ModifyTags" style='flex'>
				<input type="text" name="tagName" value='<% out.println(tg.tagName); %>' style='display:none;'>			
				<input type="text" name="Demail" value="<% out.println(t.email);%>" style='display:none;'/>
				<input type="text" value="<% out.println(t.email);%>" disabled>
				<input type="submit" value="Delete" >
			</form>
		<%
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		%>
		</main>
	</section>
</body>
</html>
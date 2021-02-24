<%@page
import="ensias.teams.buzinessLayer.User" 
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
			User member = (User)request.getAttribute("member");
			if (member == null && request.getAttribute("firstTime") == null){
		%>
			<script>
				alert("le membre que vous essayez d'ajouter n'existe pas!");
			</script>
		<%
			}
		
	%>
	<h2> tag name : <% out.println((String)request.getAttribute("tagName")); %> </h2>
	<section>
		<header>
			<div>
				Add Members from Excel
			</div>
			<div>
				Add Members from another Tag
			</div>
			<div>
				<form method='post' action="TagMembers">
					<input type="text" name="tagName" value='<% out.println(request.getAttribute("tagName")); %>' style='display:none;'>
					<input type="text" name="Nmember" placeholder="Email du membre a ajouter">
					<input type="submit" value="Ajouter">
				</form>
			</div>
		</header>
		
		<main>
			<%
			DAOFactory db;
			try{
				db = DAOFactory.getInstance();
				ArrayList<User> members = new TagDAOImp().getUsersTagged((String)request.getAttribute("tagName"), db);
				for (User t : members){
		%>
			<form method="post" action="#tagMembers" style='flex'>
				<input type="text" name="tagName" value='<% out.println(request.getAttribute("tagName")); %>' style='display:none;'>			
				<input type="text" name="Demail" value="<% out.println(t.email);%>" style='display:none;'/>
				<input type="text" value="<% out.println(t.email);%>" disabled>
				<input type="submit" value="Delete" disabled>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Affichage des Users </title>
</head>
<body>

<table border="1">
		<tr>
			<td>id User</td>
			<td>Name</td>
			<td>Last Name </td>
			<td> Login </td>
			<td> Password </td>	
		</tr>
		<%
			java.util.List<ensias.teams.buzinessLayer.User> lists = new java.util.ArrayList<>();
			lists = (java.util.List<ensias.teams.buzinessLayer.User>) request.getAttribute("users");
			for ( ensias.teams.buzinessLayer.User user : lists){
				out.println("<tr>");
				out.println("<td>" + user.id +"</td>");
				out.println("<td>" + user.firstName +"</td>");
				out.println("<td>" + user.lastName +"</td>");
				out.println("<td>" + user.email +"</td>");
				out.println("<td>" + user.password +"</td>");
				out.println("</tr>");
			}
		
		%>


</table>

</body>
</html>

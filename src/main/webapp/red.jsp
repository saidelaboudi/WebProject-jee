<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>HELLO 
	<% 
		HttpSession sess = request.getSession();
		ensias.teams.buzinessLayer.User user = (ensias.teams.buzinessLayer.User)sess.getAttribute("_SESSION");
		out.println(user.firstName +" "+ user.lastName);
	%></h1>

</body>
</html>
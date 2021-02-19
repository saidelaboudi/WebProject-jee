<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="dragome/dragome.js"></script>
</head>

<body>
	Message:
	<b data-template="message">...</b>
	<br>
	<p>
	 your name : 
	 <% 
	 	ensias.teams.buzinessLayer.User user = (ensias.teams.buzinessLayer.User)request.getAttribute("user");
	 	out.println(user.firstName);
	 %>
		
	</p>
</body>

</html>

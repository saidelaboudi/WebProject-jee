<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link href="../css/signIn.css" type="text/css" rel="stylesheet">
</head>
<body>
  <form id="msform" action="/teams/inscription" method="post">
    <h2 class="fs-title">Create your account</h2>
    <input type="text" name="firstname" placeholder="FirstName" required/>
    <input type="text" name="lastname" placeholder="LastName" required/>
    <input type="text" name="email" placeholder="Email" required/>
    <input type="text" name="adresse" placeholder="Adresse" required/>
    <input type="password" name="pass" placeholder="Password" required/>
    <input type="password" name="cpass" placeholder="Confirm Password" required/>
    <input type="submit" name="submit" class="submit action-button" value="Submit" />
</form>
</body>
</html>
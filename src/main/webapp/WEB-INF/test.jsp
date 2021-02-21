<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%> 
<%@page import="ensias.teams.buzinessLayer.*"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
                                    <table class="table table-striped table-hover">
                                            <% 
                                            ArrayList<User> user =(ArrayList<User>) request.getAttribute("user");
                                            for(int i=0;i<user.size();i++){
                                            %>
									            <tr>  
										            <td>
	                                                    <span class="custom-checkbox">
	                                                        <input type="checkbox" id="checkbox2" name="options[]" value="1">
	                                                        <label for="checkbox2"></label>
	                                                    </span>
	                                                </td>    
									                <td><%= user.get(i).firstName  %></td>
									                <td><%user.get(i).toString(); %></td> 
									                 <td>
                                                    	<a href="#deleteMemberModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                                	</td>
									            </tr>
									            <%} %>
								</table>
</body>
</html>
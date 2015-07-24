
<jsp:useBean id="myUtil" class="RowingProj.RowingDBUtil" scope="session"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log In Page</title>
</head>

<body>


	<h1>Log In</h1>
	<h1>Form data From index.html</h1>
	The user is: <%= request.getParameter("user") %><br>
	The password is: <%= request.getParameter("pw") %><br>
	
	The current value of connection is: <%= myUtil.conn %><br>
	
	<% String user = request.getParameter("user");
	   String password = request.getParameter("pw");
	%>

</body>
</html>
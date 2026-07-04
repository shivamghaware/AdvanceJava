<%@page import="java.time.LocalDate"%>
<%@page import="com.cdac.pojos.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!//JSP declaration block
	HashMap<String, User> users;

	public void jspInit() {
		System.out.println("in jsp init");
		//create empty Map & populate it
		users = new HashMap<>();
		users.put("rama@gmail.com",
				new User("Rama Patil", "rama@gmail.com", "rama#123", LocalDate.parse("1999-01-10")));
		users.put("mihir@gmail.com",
				new User("Mihir Kulkarni", "mihir@gmail.com", "mihir#123", LocalDate.parse("2003-01-10")));
		users.put("shivam@gmail.com",
				new User("shivam", "shivam@gmail.com", "shivam", LocalDate.parse("2003-01-10")));

	}%>
<body>
	<%
    //1. get request params
    String email=request.getParameter("em");
	 String pwd=request.getParameter("pass");
	 //2. Get user by email
	 User user=users.get(email);
	 if(user != null)
	 {
		 //email is valid , chk password
		 if(user.getPassword().equals(pwd)) {
			 //valid user - store validated user details under session scope
			 session.setAttribute("user_details", user);
			 //redirect + URL rewriting
			 response.sendRedirect(response.encodeRedirectURL("details.jsp"));
			 
		 } else {
			 out.write("Invalid Password , Please <a href='login.jsp'>Retry</a>");
		 }
		 
	 } else {
		 out.write("Invalid Email , Please <a href='login.jsp'>Retry</a>");
	 }
	%>
</body>
</html>
<%@page import="java.net.http.HttpRequest"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.catalog.entities.User"
    import="java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>validate</title>
</head>
<%!
    public HashMap<String,User> users;

	public void jspInit() {
        System.out.println("JSP initialized!");
        users=new HashMap<>();
        users.put("abc@gmail.com",new User("shivam","abc@gmail.com","123",LocalDate.parse("2025-07-07")));
        users.put("xyz@gmail.com",new User("anni","xyz@gmail.com","456",LocalDate.parse("2025-08-07")));
    }
	
%>
<body>

	<% 
	//RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
	//RequestDispatcher rd2=request.getRequestDispatcher("valid_login.jsp");
	String email=request.getParameter("em");
	String pass=request.getParameter("pass");
	
	User user=users.get(email);
	
	if(user!=null){
		if(user.getPassword().equals(pass)){
			session.setAttribute("user_details", user);
			response.sendRedirect("details.jsp");
		}
	}else{
		out.write("login again<a href='login.jsp'>login</a>");
	}
	%>
</body>
</html>


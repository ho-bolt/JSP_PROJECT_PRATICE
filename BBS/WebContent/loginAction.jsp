<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="user.UserDAO" %>
	<%@ page import="java.io.PrintWriter" %><!-- 자바스크립트 문장을 작성하기 위한 것 -->
	<% request.setCharacterEncoding("UTF-8"); %>
	<jsp:useBean id="user" class="user.User" scope="page"/><!-- 현재 페이지에서만 beans 사용 -->
	<jsp:setProperty property="userID" name="user"/>	
	<jsp:setProperty property="userPassword" name="user"/>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
<%
		
	String userID=null;
if(session.getAttribute("userID")!=null){
	userID=(String)session.getAttribute("userID");
}
if(session.getAttribute("userID")!=null){
	PrintWriter script=response.getWriter();
	script.println("<script>");
	script.println("alert('이미 로그인 되어 있습니다. ')");		
	script.println("location.href='main.jsp'");
	script.println("history.back()");
	script.println("</script>");
}
	UserDAO userDAO=new UserDAO();
	int result=userDAO.login(user.getUserID(), user.getUserPassword());
	if(result==1){
		PrintWriter script=response.getWriter();
		session.setAttribute("userID", user.getUserID());
		script.println("<script>");
		script.println("alert('로그인 성공')");		
		script.println("location.href='main.jsp'");
		script.println("</script>");
	}else if(result==0){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 틀립니다')");
		script.println("history.back()");
		script.println("</script>");
	}
	else if(result==-1){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('아이디가 없습니다')");
		script.println("history.back()");
		script.println("</script>");
	}else if(result==-2){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('데이터베이스 오류가 발생했습니다.')");
		script.println("history.back()");
		script.println("</script>");
	}
%>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="user.UserDAO" %>
	<%@ page import="java.io.PrintWriter" %><!-- 자바스크립트 문장을 작성하기 위한 것 -->
	<% request.setCharacterEncoding("UTF-8"); %>
	<jsp:useBean id="user" class="user.User" scope="page"/><!-- 현재 페이지에서만 beans 사용 -->
	<jsp:setProperty property="userID" name="user"/>	
	<jsp:setProperty property="userPassword" name="user"/>	
	<jsp:setProperty property="userName" name="user"/>	
	<jsp:setProperty property="userGender" name="user"/>	
	<jsp:setProperty property="userEmail" name="user"/>	
	
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
	if(user.getUserID()==null ||user.getUserPassword()==null || user.getUserName()==null || user.getUserGender()==null||user.getUserEmail()==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안된 사항이 있습니다. ')");
		script.println("history.back()");
		script.println("</script>");
	}
	else{
	UserDAO userDAO=new UserDAO();
	int result=userDAO.join(user);
	if(result==-1){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('이미 존재하는 아이디입니다')");		
		script.println("history.back()");
		script.println("</script>");
	}else {
		PrintWriter script=response.getWriter();
		session.setAttribute("userID", user.getUserID());
		script.println("<script>");
		script.println("location.href='main.jsp'");
		script.println("alert('회원가입 성공')");		
		script.println("</script>");
	}
		
	}
	
%>


</body>
</html>
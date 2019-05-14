<%@ page import="java.sql.Connection"%>
<%@ page import="com.saeyan.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커넥션 객체 테스트</title>
</head>
<body>
	<%
		// 스크립트릿
		MemberDAO memDao = MemberDAO.getInstance();
		Connection conn = memDao.getConnection();
		out.println("DBCP 연동 성공");
	%>
</body>
</html>
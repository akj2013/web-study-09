<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<script type = "text/javascript" src = "script/member.js"></script>
</head>
<body>
	<h2> 아이디 중복확인 </h2>
	<form action = "idCheck.do" method = "get" name = "frm">
		아이디 <input type = "text" name = "userid" value = "${userid }">
		<input type = "submit" value = "중복 체크"> <!-- [중복 체크] 버튼을 클릭하면 idCheck.do가 요청되어 또다시 아이디 중복 체크를 진행합니다. -->  
		<br>
		<c:if test = "${result == 1 }">
			<script type = "text/javascript">
				opener.document.frm.userid.value=""; // 회원 가입 폼에서 입력받은 아이디를 지웁니다.
			</script>
			${userid }는 이미 사용 중인 아이디입니다.
		</c:if>
		<!-- 서블릿에서 넘겨준 result 값이 -1 이면 등록되지 않은 아이디이기에 사용 가능하다는 메시지를 출력하고 <사용> 버튼이 나타납니다. <사용> 버튼이 클릭되면
		자바스크립트 함수 idok()가 호출됩니다. -->
		<c:if test = "${result == -1 }">
			${userid }는 사용 가능한 아이디입니다.
			<input type = "button" value = "사용" class = "cancel" onclick = "idok()">
		</c:if>
	</form>
</body>
</html>
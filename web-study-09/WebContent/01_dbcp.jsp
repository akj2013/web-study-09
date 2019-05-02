<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB 연동</title>
</head>
<body>
	<h4>DB 연동</h4>
	<%
		// 스크립트릿
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle"); // lookup 메소드의 매개변수가 바로 server.xml 파일의 Resource 태그의 name 속성을 설명할 때 언급했다.
		Connection conn = ds.getConnection();
		out.println("DBCP 연동 성공");
	%>
	<hr>
	<h4>데이터베이스 커넥션 풀</h4><br>
	자바에서 오라클로 쿼리문을 실행하기 위해서 첫 번째로 해야 할 작업은 오라클 접속 권한을 얻기 위한 환경 설정입니다. <br>
	하지만 웹페이지에 접속자의 수가 많게 되면 커넥션을 그만큼 걸어주어야 하기 때문에 서버에 부하가 발생하여 서버가 다운되는 현상까지 발생합니다. <br>
	DBCP(DataBase Connection Pool)은 접속 인원이 많은 웹 페이지에서 데이터베이스의 효율성과 속도를 높이기 위해서 사용합니다. <br>
	여러 접속자들에 대해서 하나의 커넥션 객체로 데이터베이스를 처리할 경우 이에 대한 처리가 벅찰 것입니다. <br>
	DBCP 기법은 DBCP 매니저가 어느 정도의 연결을 확보해 놓고 있다가 클라이언트의 요청이 들어오면 연결해 주고, 클라이언트 작업이 다 끝나면 연결을 다시 DBCP 매니저한테 반환하게 만드는 것입니다. <br>
	1단계 : WEB-INF 폴더의 lib 폴더에 오라클 드라이버인 ojdbcX.jar와 JSTL을 위한 jar 파일을 복사합니다. <br>
	2단계 : DBCP 커넥션 풀은 컨텍스트 패스의 서브 태그로 추가합니다. 컨텍스트 패스(Context Path)는 여러 개의 웹 애플리케이션이 WAS에서 동작할 경우 이를 구분하기 위한 것으로 <br>
	톰캣 서버의 환경설정을 위한 server.xml 파일에 Context 태그를 추가해야 컨텍스트 패스가 설정됩니다. <br>
	3단계 : 아파치 홈페이지에서 Resource 태그를 Context 태그 내부에 기술해야 합니다. 이때 Context / 형태를 Context와 /Context 형태로 변경해야 합니다. <br>
	Resource 태그를 Context / 태그의 자식 앨리먼트로 추가하려면 시작 태그와 끝 태그가 모두 있어야 그 사이에 코드를 추가할 수 있기 때문입니다. <br>
	4단계 : 아파치 홈페이지에서 제공하는 샘플로 스크립트릿을 추가하여 DBCP 연동 여부를 확인합니다.
</body>
</html>
// 이클립스에서 회원 테이블을 액세스하는 DAO 클래스 만들기


/*
 *	DAO의 주된 역할은 데이터베이스 데이터를 VO 객체로 얻어오거나 VO 객체에 저장된 값을 데이터베이스에 추가합니다.
 *그렇기 때문에 매번 이런 작업을 위해서 객체를 생성하기 보다는 싱글톤 패턴(Singleton Pattern)으로 클래스를 설계합니다.
 *싱글톤 패턴은 인스턴스가 오로지 단 하나만 존재할 수 있도록 클래스를 설계하는 것을 말합니다. 싱글톤은 객체를 메모리에 단 한 번만 존재할 수 있도록 클래스를 설계하는 것을 말합니다.
 *싱글톤은 객체를 메모리에 단 한번만 올려놓고 시스템 전반에 걸쳐서 특정한 자원(Object, Module, Component)을 공유할 때 사용합니다.
 *
 *1. 생성자가 private이어야 합니다. 그래서 다른 클래스는 절대로 인스턴스를 생성하지 못하고 자기 자신만 인스턴스를 생성할 수 있습니다.
 *2. 생성된 인스턴스는 외부에서 접근할 수 없도록 private 필드로 선언했습니다.
 *3. 이렇게 생성된 인스턴스는 외부에서 수정은 못 하고 값을 얻을 수만 있도록 read only property로 만들기 위해서 setter는 정의하지 않고 getter만 만듭니다.
 *
 *이러한 싱글톤 패턴 클래스는 메모리 낭비를 막기 위해서 만드는 것입니다. new 연산자를 클래스 앞에 기술하여 객체를 생성하면 메모리를 할당받게 됩니다.
 *new 연산자를 사용할 때마다 동일한 형태의 객체가 계속 만들어집니다. 이를 막기 위해서 생성자를 외부에서 호출하지 못하도록 private 접근 제한자로 선언하고
 *대신 클래스 선언부 내부에서 자신이 객체를 생성한 후 이렇게 생성된 오로지 한 개의 객체를 getInstance() 메소드로 접근해서 사용할 수 있도록 합니다.  
 */

package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement; // 회원 인증
import java.sql.ResultSet; // 회원 인증
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.saeyan.dto.MemberVO; // 회원 인증

public class MemberDAO {
	private MemberDAO() {
		
	}
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		Connection conn = null;
		// InitialContext 객체를 생성합니다.
		Context initContext = new InitialContext();
		// 컨텍스트 객체의 lookup 메소드로 DBCP에서 지정한 이름을 찾습니다. lookup 메소드의 매개 변수인 "jdbc/myoracle"이 바로 server.xml 파일의 Resource 태그의 name 속성을 설명할 때 언급했던 내용입니다.
		Context envContext = (Context) initContext.lookup("java:/comp/env"); 
		// lookup 메소드가 리턴하는 값을 저장하는 변수 역시 server.xml 파일의 Resource 태그의 type 속성을 설명할 때 언급했던 내용입니다. jdbc/myoracle이라는 이름을 찾으면 이를 DataSource 형으로 리턴하므로 이를 받아서 DataSource 객체에 저장합니다.
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		// 컨텍스트 객체의 lookup 메소드로 얻어낸 DataSource 객체로 getConnection 메소드를 호출하여 커넥션 객체를 얻어냅니다. 
		conn = ds.getConnection();
		return conn;
	}
	
	// 사용자 인증시 사용하는 메소드
	public int userCheck(String userid, String pwd) {
		int result = -1;
		String sql = "SELECT pwd FROM member WHERE userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pwd") != null && rs.getString("pwd").contentEquals(pwd)) {
					result = 1; // 아이디와 비밀번호가 모두 일치할 때
				} else {
					result = 0; // 아이디가 일치하지만 암호가 불일치할 때
				}
			}else {
				result = -1; // 해당 아이디가 존재하지 않을 때
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 아이디로 회원 정보 가져오는 메소드
	public MemberVO getMember(String userid) {
		MemberVO mVo = null;
		String sql = "SELECT * FROM member WHERE userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mVo = new MemberVO();
				mVo.setName(rs.getString("name"));
				mVo.setUserid(rs.getString("userid"));
				mVo.setPwd(rs.getString("pwd"));
				mVo.setEmail(rs.getString("email"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setAdmin(rs.getInt("admin"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mVo;
	}
	
	public int confirmID(String userid) {
		int result = -1;
		String sql  = "SELECT userid FROM member WHERE userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}else {
				result = -1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	// 회원 정보를 DB에 추가하기 위한 메소드 추가
	public int insertMember(MemberVO mVo) { // 회원 가입을 위한 메소드로 회원 가입 폼에서 입력받은 회원 정보를 매개 변수로 전달받습니다.
		int result = -1;
		String sql = "INSERT INTO member VALUES(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mVo.getName());
		pstmt.setString(2, mVo.getUserid());
		pstmt.setString(3, mVo.getPwd());
		pstmt.setString(4, mVo.getEmail());
		pstmt.setString(5, mVo.getPhone());
		pstmt.setInt(6, mVo.getAdmin());
		result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 회원 정보를 변경하기 위한 메소드 추가하기
	public int updateMember(MemberVO mVo) {
		int result = -1;
		String sql = "update member set pwd=?, email=?, phone=?, admin=?, where userid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getPhone());
			pstmt.setInt(4, mVo.getAdmin());
			pstmt.setString(5, mVo.getUserid());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
} // class


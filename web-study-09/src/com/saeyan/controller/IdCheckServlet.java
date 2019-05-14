package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import com.saeyan.dao.MemberDAO;

@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid"); // 회원 가입 시 입력한 아이디를 얻어옵니다.
		MemberDAO mDao = MemberDAO.getInstance(); // DAO 객체를 얻어옵니다.
		int result = mDao.confirmID(userid); // 아이디 중복 체크를 위한 confirmID() 메소드에 아이디를 전달해 주어 결과값을 얻어옵니다.
		
		// 아이디 중복 체크 후 얻어온 confirmID() 메소드의 결과값을 사용자 아이디와 함께 idcheck.jsp 페이지에 어트리뷰트에 실어 보냅니다.
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/idcheck.jsp"); 
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

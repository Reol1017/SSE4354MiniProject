package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckOTPServlet
 */
@WebServlet("/CheckOTPServlet")
public class CheckOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOTPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String account = request.getParameter("account");
			String amount = request.getParameter("amount");
			String OTP = request.getParameter("OTP");
			System.out.println(account);
			System.out.println(amount);
			System.out.println(OTP);
			response.sendRedirect("http://localhost:8081/SSE4354BankSystem/VertifyOTPServlet?account="+account+"&amount="+amount+"&InputOTP="+OTP);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}

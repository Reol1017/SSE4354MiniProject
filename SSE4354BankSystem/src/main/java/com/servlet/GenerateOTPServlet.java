package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;

/**
 * Servlet implementation class GenerateOTPServlet
 */
@WebServlet("/GenerateOTPServlet")
public class GenerateOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateOTPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account = request.getParameter("account");
		String amount = request.getParameter("amount");
		System.out.println(account);
		System.out.println(amount);
		int OTP = generateOTP();
		System.out.println(OTP);
		Timestamp timenow = new Timestamp(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 5);
//		cal.add(Calendar.SECOND, 1);
		Timestamp OTP_Expire = new Timestamp(cal.getTimeInMillis());
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521:fsktm", "nky", "nky");
			PreparedStatement stmt = conn.prepareStatement("UPDATE accounts SET OTP = ?, OPT_EXPIRE = ? WHERE ID = ?");
			stmt.setInt(1, OTP);
			stmt.setTimestamp(2, OTP_Expire);
			stmt.setString(3, account);
			ResultSet rs = stmt.executeQuery();
			String email = "";
			try {
				PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM accounts WHERE ID = ?");
				stmt2.setString(1, account);
				ResultSet rs2 = stmt2.executeQuery();
				if(rs2.next()) {
					email = rs2.getString("Email");
//					System.out.println("Success!");
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
//			response.sendRedirect("http://localhost:8081/SSE4354MerchantSystem/CheckOTP.jsp?OTP="+OTP+"&email="+email);
//			response.sendRedirect("http://localhost:8081/SSE4354MerchantSystem/CheckOTP.jsp?account="+account+"&amount="+amount);
//			response.sendRedirect("http://localhost:8082/SSE4354MerchantSystem/CheckOTP.jsp?account="+account+"&amount="+amount);
			response.sendRedirect("http://localhost:8081/SSE4354BankSystem/SendEmailServlet?OTP=" + OTP+"&email="+email+"&account="+account+"&amount="+amount);
//			response.sendRedirect("http://localhost:8081/Test/Test.jsp");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static int generateOTP() {
		int OTP = (int)(Math.random()*900000)+99999;
		return OTP;
	}

}

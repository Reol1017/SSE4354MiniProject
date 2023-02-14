package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VertifyOTPServlet
 */
@WebServlet("/VertifyOTPServlet")
public class VertifyOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VertifyOTPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String account = request.getParameter("account");
		String amount = request.getParameter("amount");
		String InputOTP = request.getParameter("InputOTP");
		Double payment = Double.valueOf(amount.toString());
		System.out.println(account);
		System.out.println(amount);
		System.out.println(InputOTP);
		Timestamp timenow = new Timestamp(System.currentTimeMillis());
		String returnString = "";
		boolean pay = false;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521:fsktm", "nky", "nky");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accounts WHERE ID = ?");
			stmt.setString(1, account);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				String OTP = (String)rs.getString("OTP");
				Timestamp ExpireTime = rs.getTimestamp("OPT_Expire");
				Double Balance = rs.getDouble("Balance");
				if(OTP.equals(InputOTP)) {
					if(timenow.before(ExpireTime)) {
						if(Balance > payment) {
							Double newBalance = Balance - payment;
							if(pay(newBalance, account)) {
								returnString = "Payment Success!";
								pay = true;
							}else {
								returnString = "Payment failed, Unknown reason";
							}
						}else {
							returnString = "Payment failed, you do not have enough balance";
						}
					}else {
						returnString = "OTP has expired";
					}
				}else {
					returnString = "Invalid OTP";
				}
//				System.out.println("Success!");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(returnString);
		
//		PrintWriter out = response.getWriter();
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<title>Example Page</title>");
//		out.println("</head>");
//		out.println("<style>");
//		out.println("body{");
//		out.println("	text-align:center");
//		out.println("}");
//		out.println("</style>");
//		out.println("<body>");
//		if(pay) {
//			out.println("<h1><font color='green'>"+returnString+"</font></h1>");
//		}else {
//			out.println("<h1><font color='red'>"+returnString+"</font></h1>");
//		}
//		out.println("</body>");
//		out.println("</html>");
		response.sendRedirect("http://localhost:8082/SSE4354MerchantSystem/Outcome.jsp?returnString="+returnString+"&pay="+pay);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public boolean pay(double newBalance, String account) {
		boolean test = false;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521:fsktm", "nky", "nky");
			PreparedStatement stmt = conn.prepareStatement("UPDATE accounts SET Balance = ? WHERE ID = ?");
			stmt.setDouble(1, newBalance);
			stmt.setString(2, account);
			stmt.executeQuery();
			test = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return test;
	}

}

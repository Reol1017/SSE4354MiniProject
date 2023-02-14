package com.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class CheckBalanceServlet
 */
//@WebServlet("/CheckBalanceServlet")
public class CheckBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CheckBalanceServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		PrintWriter out = response.getWriter();
		try{
			String account = request.getParameter("account");
			String pin = request.getParameter("pin");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521:fsktm", "nky", "nky");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accounts where id=?");
			stmt.setString(1, account);
			ResultSet rs = stmt.executeQuery();
			
			String returnString = " ";
			String returnBalance = " ";
			if(rs.next()) {
				if(pin.equals(rs.getString("Online_pin"))) {
					returnString = "Hello " + rs.getString("name") + "</br>"
							+ "User ID: " + rs.getString("id") + "</br>"
							+ "Creation Date: " + rs.getString("Creation_Date") + "</br>"
							+ "Email: " + rs.getString("Email") + "</br>";
					returnBalance = "Your balance is: RM " + rs.getString("balance");
				}else {
					returnBalance = "Invalid account or password";
				}
			}else {
				returnBalance = "Invalid account or password";
			}
			
			if(returnString != null && !returnString.isEmpty()) {request.setAttribute("returnString", returnString);}
			if(returnBalance != null && !returnBalance.isEmpty()) {request.setAttribute("returnBalance", returnBalance);}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CheckBalance.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			String returnBalance = "Cannot connect to the database";
			
			request.setAttribute("returnBalance", returnBalance);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CheckBalance.jsp");
			dispatcher.forward(request, response);
		}
	}

}

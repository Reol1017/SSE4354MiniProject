package com.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBMStest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			String acc = "10068";
			String pin = "123456";
			//DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521:fsktm", "nky", "nky");
			PreparedStatement stmt = conn.prepareStatement("SELECT id, name, balance, online_pin FROM accounts where id=? and online_pin=?");
			stmt.setString(1, acc);
			stmt.setString(2, pin);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("Hello " + rs.getString("name") + "</br>Your balance is: RM " + rs.getString("balance"));
			}

		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Cannot connect to the database");
		}

	}

}

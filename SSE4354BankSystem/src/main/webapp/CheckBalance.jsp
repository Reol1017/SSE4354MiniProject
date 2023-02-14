<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Balance Check Page</title>
</head>
<style>
	body{
		text-align:center
	}
</style>
<body>
<h1>Bank A Online System</h1>
<h2>Check Your Balance Here</h2>
<form method="POST" action="CheckBalance">
	Bank Account: <input type="text" id="account" name="account" required /><br>
	Account PIN: <input type="password" id="pin" name="pin" required /><br>
	<input type="submit" value="Check my balance" />
</form>
<%
String returnString = (String) request.getAttribute("returnString");
String returnBalance = (String) request.getAttribute("returnBalance");
%>
<h2><%= (returnString != null && !returnString.equals("")) ? returnString : "" %></h2>
<h2><font color="red"><%= (returnBalance != null && !returnBalance.equals("")) ? returnBalance : "" %></font></h2>
</body>
</html>
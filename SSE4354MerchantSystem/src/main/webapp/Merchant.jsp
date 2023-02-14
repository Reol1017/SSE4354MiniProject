<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	body{
		text-align:center
	}
</style>
<body>
<h1>Merchant System</h1>
<h2>Input your account and amount here</h2>
<form method="POST" action="Merchant">
	Bank Account: <input type="text" id="account" name="account" required /><br>
	Amount RM: <input type="number" id="amount" name="amount" required /><br>
	<input type="submit" value="Pay my Bill" />
</form>

</body>
</html>
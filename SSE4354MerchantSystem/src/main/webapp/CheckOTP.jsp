<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CheckOTP</title>
</head>
<style>
	body{
		text-align:center
	}
</style>
<body>
<h1>Merchant System</h1>
<h2>Input your OTP here</h2>
<%
String account = request.getParameter("account");
String amount = request.getParameter("amount");
%>
<form method="POST" action="CheckOTP">
	OTP: <input type="number" id="OTP" name="OTP" required /><br>
	<input type="submit" value="Input" /><br>
	<input type="hidden" name="account" value="<%=account %>"/><br>
	<input type="hidden" name="amount" value="<%=amount %>"/><br>
</form>
</body>
</html>
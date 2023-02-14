<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h1>
<%
String returnString = request.getParameter("returnString");
String pay = request.getParameter("pay");
String output = "";
if(pay.equals("true")){
	output = output + "<font color='green'>";
}else{
	output = output + "<font color='red'>";
}
output = output + returnString + "</font>";
out.println(output);
%>
</h1>
</body>
</html>
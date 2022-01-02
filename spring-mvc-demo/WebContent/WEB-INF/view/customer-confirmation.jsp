<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Confirmation</title>
</head>
<body>
	Customer is confirmed: ${customer.firstName} ${customer.lastName}
	<br><br>
	Free Passes: ${customer.freePasses }
	<br><br>
	PostalCode: ${customer.postalCode }
	<br><br>
	CourseCode: ${customer.courseCode }
</body>
</html>
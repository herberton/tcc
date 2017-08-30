<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="author" content="Herberon Candido Souza">
		<poc:import-bootstrap/>
		<poc:import-css path="/resources/css/home.css"/>
		<title>POC - Back-office</title>
	</head>
	<body>
		<poc:navbar-backoffice title="Home"/>
		<div class="container">
			<div class="starter-template">
				<h1>POC</h1>
				<p class="lead">
					Hello ${loggedUser.login}<br>
					<br/>IP: ${networkAddress}
				</p>
			</div>
		</div> <!-- /.container -->
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
    	<meta name="author" content="Herberon Candido Souza">
        <poc:import-bootstrap/>
        <poc:import-css path="/resources/css/login.css"/>
	    <title>POC</title>
	</head>
    <body>
		<div class="container">
	      <form class="form-signin" method="post" action="${pageContext.request.contextPath}/login" >
	        <h2 class="form-signin-heading">Fa�a o login na POC </h2>
	        <label for="login" class="sr-only">Usu�rio</label>
	        <input type="text" id="login" name="login" class="form-control" placeholder="Usu�rio" required autofocus autocomplete="off">
	        <label for="password" class="sr-only">Senha</label>
	        <input type="password" id="password" name="password" class="form-control" placeholder="Senha" required autocomplete="off">
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
	        <br/>IP: ${networkAddress}
	      </form>
	    </div> <!-- /container -->
	</body>
</html>
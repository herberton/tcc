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
	      <form class="form-signin">
	        <h2 class="form-signin-heading">Please sign in at ${mensagem} </h2>
	        <label for="inputEmail" class="sr-only">Email address</label>
	        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
	        <label for="inputPassword" class="sr-only">Password</label>
	        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
	        <div class="checkbox">
	          <label>
	            <input type="checkbox" value="remember-me"> Remember me
	          </label>
	        </div>
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	      </form>
	    </div> <!-- /container -->
	</body>
</html>
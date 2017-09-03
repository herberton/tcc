<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags"%>
<%@ attribute name="title" required="true" %>
<%@ attribute name="showHome" required="false" %>
<!-- NAVBAR -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button 
				type="button" 
				class="navbar-toggle collapsed"
				data-toggle="collapse" 
				data-target="#navbar" 
				aria-expanded="false" 
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<c:choose>
				<c:when test="${showHome != null && showHome}">
					<a class="navbar-brand">Agro</a>
				</c:when>
				<c:otherwise>
					<a class="navbar-brand" href="${pageContext.request.contextPath}/">Agro</a>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<c:if test="${showHome != null && showHome}">
					<li><a href="${pageContext.request.contextPath}?index=false">Home</a></li>
				</c:if>
				<li class="active"><a href="#">${title}</a></li>
				<c:if test="${loggedUser != null && loggedUser.isAdmin}">
					<li><a href="${pageContext.request.contextPath}?index=true">E-commerce</a></li>
				</c:if>
				<li class="dropdown">
					<a 
						class="dropdown-toggle" 
						data-toggle="dropdown" 
						role="button" 
						aria-haspopup="true"
						aria-expanded="false">
						Cadastro	
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/registration/user/">Usuário</a></li>
						<c:if test="${loggedUser != null && loggedUser.isAdmin}">
							<li><a href="${pageContext.request.contextPath}/registration/role/">Perfil</a></li>
						</c:if>
					</ul>
				</li>
			</ul>
			<form class="navbar-form navbar-right logout-form" method="post" action="${pageContext.request.contextPath}/logout">
				<div class="form-group">
					<p class="navbar-text">
						<strong class="text-info"><a href="${pageContext.request.contextPath}/registration/user/">${loggedUser.login}</a></strong><small>@</small><strong class="text-success">${networkAddress}</strong>
					</p>
				</div>
				<button type="submit" class="btn btn-danger">Sair</button>
			</form>
		</div> <!--/.nav-collapse -->
	</div>
</nav>
<!-- / NAVBAR -->

	    
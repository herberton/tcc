<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags"%>
<%@ attribute name="title" required="true" %>
<%@ attribute name="hideLoginForm" required="false" %>
<!-- NAVBAR -->
<div class="navbar-wrapper">
	<div class="container">
		<nav class="navbar navbar-inverse navbar-static-top">
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
					<a class="navbar-brand" href="${pageContext.request.contextPath}/?index=true">Agro</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active">
							<a href="#">${title}</a>
						</li>
						<c:if test="${loggedUser != null && loggedUser.isAdmin}">
							<li><a href="${pageContext.request.contextPath}/">Back-office</a></li>
						</c:if>
						<li class="dropdown">
							<a 
								class="dropdown-toggle" 
								data-toggle="dropdown" 
								role="button" 
								aria-haspopup="true"
								aria-expanded="false">
								Produtos
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li class="dropdown-header">Agricultura</li>
								<li><a href="#">Defensivos</a></li>
								<li><a href="#">Fertilizantes</a></li>
								<li><a href="#">Sementes</a></li>
								<li role="separator" class="divider"></li>
								<li class="dropdown-header">Utensílhos</li>
								<li><a href="#">Ferramentas</a></li>
								<li><a href="#">Jardinagem</a></li>
								<li><a href="#">Uso Geral</a></li>
								<li role="separator" class="divider"></li>
								<li class="dropdown-header">Máquinas e Componentes</li>
								<li><a href="#">Implementos Agrícolas</a></li>
								<li><a href="#">Plantio</a></li>
								<li><a href="#">Pulverização</a></li>
							</ul>
						</li>
					</ul>
					<c:choose>
						<c:when test="${loggedUser == null}">
							<c:if test="${hideLoginForm != 'true'}">
								<form class="navbar-form navbar-right" method="post" action="${pageContext.request.contextPath}/login">
									<div class="form-group">
										<input type="text" placeholder="Login" name="login" class="form-control" autocomplete="off" required>
									</div>
									<div class="form-group">
										<input type="password" placeholder="Senha" name="password" class="form-control" autocomplete="off">
									</div>
									<div class="btn-group">
									  <button type="submit" class="btn btn-success">Entrar</button>
									  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									    <span class="caret"></span>
									    <span class="sr-only">Toggle Dropdown</span>
									  </button>
									  <ul class="dropdown-menu">
									    <li class="dropdown-header">Não possui um login?</li>
									  	<li><a href="${pageContext.request.contextPath}/registration/ecommerce-user/">Cadastre-se</a></li>
									  </ul>
									</div>
								</form>
							</c:if>
						</c:when>
						<c:otherwise>
							<form class="navbar-form navbar-right logout-form" method="post" action="${pageContext.request.contextPath}/logout">
								<div class="form-group">
									<p class="navbar-text">
										<strong class="text-info"><a href="${pageContext.request.contextPath}/registration/ecommerce-user/">${loggedUser.login}</a></strong><small>@</small><strong class="text-success">${networkAddress}</strong>
									</p>
								</div>
								<button type="submit" class="btn btn-danger">Sair</button>
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</nav>
		<jsp:doBody/>
	</div>
</div>
<!-- / NAVBAR -->

	    
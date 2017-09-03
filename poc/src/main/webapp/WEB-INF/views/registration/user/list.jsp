<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="author" content="Herberon Candido Souza">
		<poc:import-bootstrap system="backoffice"/>
		<poc:import-css path="/resources/css/registration/user/list.css"/>
		<title>POC - Back-office</title>
	</head>
	<body>
		<poc:navbar-backoffice title="Usuários" showHome="true"/>
		<div class="container">
			<div class="page-header">
				<h1>Lista de Usuários</h1>
			</div>
			<div class="row buttom-area">
				<div class="col-md-12">
					<a href="${pageContext.request.contextPath}/registration/user/form" class="btn btn-primary">Novo</a>
				</div>
			</div>
			<poc:message/>
			<div class="table-responsive">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>CPF</th>
							<th>Login</th>
							<th>Papéis</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${list}">
							<tr>
								<td>${user.id}</td>
								<td>${user.cpf}</td>
								<td>${user.login}</td>
								<td class="list-group">
									<c:forEach var="role" items="${user.roles}">
										<a class="list-group-item list-group-item-info">
											<label class="list-group-item-heading">${role.name}</label>
											<p class="list-group-item-text">${role.type}</p>
										</a>
									</c:forEach>
								</td>
								<td>
									<c:if test="${user.login != 'admin' && user.password != 'admin' && user.cpf != '00000000000'}">
										<a href="${pageContext.request.contextPath}/registration/user/form?id=${user.id}" class="btn btn-success">Alterar</a>
										<a href="${pageContext.request.contextPath}/registration/user/delete?id=${user.id}" class="btn btn-danger">Excluir</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div> <!-- /.container -->
	</body>
</html>
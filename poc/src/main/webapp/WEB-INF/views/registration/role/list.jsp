<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="author" content="Herberon Candido Souza">
		<poc:import-bootstrap system="backoffice"/>
		<poc:import-css path="/resources/css/registration/role/list.css"/>
		<title>POC - Back-office</title>
	</head>
	<body>
		<poc:navbar-backoffice title="Papéis" showHome="true"/>
		<div class="container">
			<div class="page-header">
				<h1>Lista de Papéis</h1>
			</div>
			<div class="row buttom-area">
				<div class="col-md-12">
					<a href="${pageContext.request.contextPath}/registration/role/form" class="btn btn-primary">Novo</a>
				</div>
			</div>
			<poc:message/>
			<div class="table-responsive">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Nome</th>
							<th>Tipo</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="role" items="${list}">
							<tr>
								<td>${role.id}</td>
								<td>${role.name}</td>
								<td>${role.type}</td>
								<td>
									<c:if test="${role.canEdit}">
										<a href="${pageContext.request.contextPath}/registration/role/form?id=${role.id}" class="btn btn-success">Alterar</a>
										<a href="${pageContext.request.contextPath}/registration/role/delete?id=${role.id}" class="btn btn-danger">Excluir</a>
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
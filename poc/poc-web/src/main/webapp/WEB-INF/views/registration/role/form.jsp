<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="author" content="Herberon Candido Souza">
	<poc:import-bootstrap system="backoffice"/>
	<poc:import-css path="/resources/css/registration/role/form.css" />
	<title>Agro</title>
</head>
	<body>
		<poc:navbar-backoffice title="Cadastro"/>
		<div class="container">
			
			<div class="page-header">
				<h1>${role == null || role.id == null ? 'Novo' : 'Alteração de'} Papel</h1>
			</div>
			
			<poc:message/>
			
			<form 
				class="form-horizontal" 
				method="post" 
				action="${pageContext.request.contextPath}/registration/role/form/save">
				
				<input 
					type="hidden" 
					name="id" 
					value="${role == null ? '' : role.id}">
				
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Nome</label>
					<div class="col-sm-10 col-md-8">
						<input 
							type="text" 
							class="form-control" 
							id="name" 
							name="name" 
							placeholder="Nome"  
							autocomplete="off"
							value="${role == null ? '' : role.name}"
							required>
					</div>
				</div>
				<div class="form-group">
					<label for="type" class="col-sm-2 control-label">Tipo</label>
					<div class="col-sm-10 col-md-8">
						<div class="btn-group" data-toggle="buttons">
							<c:forEach var="type" items="${types}" varStatus="status">
								<label class="btn btn-success ${role.type == type ? 'active' : ''}" style="text-align: left;">
									<input 
										type="radio" 
										id="${type.name()}"
										name="type" 
										value="${type.name()}"
										autocomplete="off"
										${role.type == type ? 'checked' : ''}>  
										${type.name()}
								</label>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10 col-md-8">
						<button type="submit" class="btn btn-primary pull-right" data-loading-text="Aguarde...">
							${role == null || role.id == null ? 'Cadastrar' : 'Alterar'}
						</button>
						<a type="button" class="btn btn-danger pull-right" data-loading-text="Aguarde..." href="${pageContext.request.contextPath}/registration/role/" style="margin-right: 5px;">
							<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Voltar
						</a>
					</div>
				</div>
			</form>
	
		</div>
		<!-- /.container -->
		
	</body>
</html>
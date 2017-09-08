<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="author" content="Herberon Candido Souza">
	<poc:import-bootstrap system="backoffice"/>
	<poc:import-css path="/resources/css/registration/user/form.css" />
	<title>Agro</title>
</head>
	<body>
		<poc:navbar-backoffice title="Cadastro"/>
		<div class="container">
			
			<div class="page-header">
				<h1>${user == null || user.id == null ? 'Novo' : 'Alteração de'} Usuário</h1>
			</div>
			
			<poc:message/>
			
			<form 
				class="form-horizontal" 
				method="post" 
				action="${pageContext.request.contextPath}/registration/user/form/save">
				
				<input 
					type="hidden" 
					name="id" 
					value="${user == null ? '' : user.id}">
				
				<div class="form-group">
					<label for="cpf" class="col-sm-2 control-label">CPF</label>
					<div class="col-sm-10 col-md-8">
						<input 
							type="text" 
							class="form-control" 
							id="cpf" 
							name="cpf" 
							placeholder="CPF"  
							autocomplete="off"
							value="${user == null ? '' : user.cpf}"
							required
							pattern="[0-9]{3}([0-9]{3}){2}[0-9]{2}"
							maxlength="11">
					</div>
				</div>
				<div class="form-group">
					<label for="login" class="col-sm-2 control-label">Login</label>
					<div class="col-sm-10 col-md-8">
						<input 
							type="text" 
							class="form-control" 
							id="login" 
							name="login" 
							placeholder="Login" 
							autocomplete="off"
							value="${user == null ? '' : user.login}"
							required>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Senha</label>
					<div class="col-sm-10 col-md-8">
						<input 
							type="password" 
							class="form-control" 
							id="password" 
							name="password" 
							placeholder="Password" 
							value="${user == null ? '' : user.password}">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Papéis</label>
					<div class="col-sm-10 col-md-8">
						<jsp:useBean 
							id="helper" 
							class="br.com.herberton.tcc.puc.poc.helper.UserHelper"
							scope="request"/>
						<div class="btn-group-vertical" data-toggle="buttons">
							<c:forEach var="role" items="${roles}" varStatus="status">
								<label class="btn btn-success ${helper.contains(user, role) ? 'active' : ''}" style="text-align: left;">
									<input 
										type="checkbox" 
										id="${role.id}"
										name="roles[${status.index}].id" 
										value="${role.id}"
										autocomplete="off"
										${helper.contains(user, role) ? 'checked' : ''}>  
										${role.name}
										<span class="badge">${role.type}</span>
								</label>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10 col-md-8">
						<button type="submit" class="btn btn-primary pull-right" data-loading-text="Aguarde...">
							${user == null || user.id == null ? 'Cadastrar' : 'Alterar'}
						</button>
						<c:if test="${loggedUser != null && loggedUser.isAdmin}">
							<a type="button" class="btn btn-danger pull-right" data-loading-text="Aguarde..." href="${pageContext.request.contextPath}/registration/user/" style="margin-right: 5px;">
								<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Voltar
							</a>
						</c:if>
					</div>
				</div>
			</form>
	
		</div>
		<!-- /.container -->
		
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="author" content="Herberon Candido Souza">
	<poc:import-bootstrap system="ecommerce"/>
	<poc:import-css path="/resources/css/registration/ecommerce-user/form.css" />
	<title>Agro</title>
</head>
	<body>
		
		<poc:navbar-ecommerce title="Cadastro" hideLoginForm="true"/>
	
		<div class="container">
			
			<div class="page-header">
				<h1>${loggedUser == null || loggedUser.isAdmin ? 'Novo' : 'Alteração de'} Usuário</h1>
			</div>
			
			<poc:message/>
			
			<form 
				class="form-horizontal" 
				method="post" 
				action="${pageContext.request.contextPath}/registration/ecommerce-user/form/save">
				
				<input 
					type="hidden" 
					name="id" 
					value="${loggedUser == null || loggedUser.isAdmin ? '' : ecommerceUser.id}">
				
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
							value="${ecommerceUser == null && (loggedUser == null || loggedUser.isAdmin) ? '' : ecommerceUser.cpf}" 
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
							value="${ecommerceUser == null && (loggedUser == null || loggedUser.isAdmin) ? '' : ecommerceUser.login}" 
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
							value="${ecommerceUser == null && (loggedUser == null || loggedUser.isAdmin) ? '' : ecommerceUser.password}">
					</div>
				</div>
				<c:if test="${loggedUser == null || loggedUser.isExternalPartner == false}">
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">Deseja se tornar um cliente especial?</label>
						<div class="col-sm-10 col-md-8">
							<div class="radio">
								<label>
									<input 
										type="radio" 
										name="specialCustomer" 
										id="true"
										value="true"
										${ecommerceUser == null && (loggedUser == null || loggedUser.isAdmin) ? '' : ecommerceUser.specialCustomer ? 'checked' : ''}>  
										Sim
								</label>
								<label>
									<input 
										type="radio" 
										name="specialCustomer" 
										id="false"
										value="false"
										${ecommerceUser == null && (loggedUser == null || loggedUser.isAdmin) ? 'checked' : ecommerceUser.specialCustomer ? '' : 'checked'}>  
										Não
								</label>
								<span id="helpBlock" class="help-block">
									Selecione Sim caso queira acessar os dados nosso sistema diretamente 
									através do sistema de sua organização.
								</span>
							</div>
						</div>
					</div>
				</c:if>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10 col-md-8">
						<button type="submit" class="btn btn-primary pull-right">
							${loggedUser == null || loggedUser.isAdmin ? 'Cadastrar' : 'Alterar'}
						</button>
					</div>
				</div>
			</form>
			
			<hr class="divider">
			
			<poc:footer-ecommerce/>
	
		</div>
		<!-- /.container -->
		
	</body>
</html>
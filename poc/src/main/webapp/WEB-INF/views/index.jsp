<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="author" content="Herberon Candido Souza">
	<poc:import-bootstrap system="ecommerce"/>
	<poc:import-css path="/resources/css/index.css" />
	<title>Agro</title>
</head>
	<body>
		<poc:navbar-ecommerce title="Home">
			<poc:message/>
		</poc:navbar-ecommerce>
		<!-- ===================================================== -->
		<!-- CAROUSEL -->
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<poc:img
						path="/resources/img/cliente-especial.jpg" 
						style="first-slide" 
						alt="Promo��es Especiais"/>
					<div class="container">
						<div class="carousel-caption">
							<h1>Promo��es Especiais</h1>
							<p>
								<c:choose>
									<c:when test="${loggedUser == null}">
										Fa�a o cadastro e aproveite as promo��es especiais exclusivas para clientes.
									</c:when>
									<c:otherwise>
										Ol� ${loggedUser.login}, aproveite as promo��es especiais exclusivas para voc�!
									</c:otherwise>
								</c:choose>							
							</p>
							<p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/registration/ecommerce-user" role="button">${loggedUser == null ? 'Cadastre-se!' : 'Atualizar Cadastro'}</a></p>
						</div>
					</div>
				</div>
				<div class="item">
					<poc:img 
						path="/resources/img/fertilizante.jpg" 
						style="second-slide" 
						alt="Promo��es Adubos"/>
					<div class="container">
						<div class="carousel-caption">
							<h1>Fertilizantes com o menor pre�o do mercado</h1>
							<p>Somente neste m�s estamos com promo��es de fertilizantes para as principais marcas do mercado.<p>
								<a class="btn btn-lg btn-primary" href="#" role="button">Confira j�!</a>
							</p>
						</div>
					</div>
				</div>
				<div class="item">
					<poc:img 
						path="/resources/img/orcamento.jpg" 
						style="third-slide" 
						alt="Or�amentos"/>
					<div class="container">
						<div class="carousel-caption">
							<h1>Pre�o congelado por at� 15 dias</h1>
							<p>Fa�a j� or�amentos de nossos produtos e seguramos o pre�o por at� <b>15 dias</b> para vo��.</p>
							<p>
								<a class="btn btn-lg btn-primary" href="#" role="button">Pesquisar produtos!</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"> 
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> 
				<span class="sr-only">Anterior</span>
			</a> 
			<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"> 
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Pr�ximo</span>
			</a>
		</div>
		<!-- / CAROUSEL -->
	
	
		<!-- Marketing messaging and featurettes
	    ================================================== -->
		<!-- Wrap the rest of the page in another container to center all the content. -->
	
		<div class="container marketing">
	
			<!-- Three columns of text below the carousel -->
			<div class="row">
				<div class="col-lg-4">
					<poc:img 
						path="/resources/img/agricultura.jpg" 
						style="img-circle" 
						alt="Agricultura" 
						width="140" 
						height="140"/>
					<h2>Agricultura</h2>
					<p>
						Aqui voc� encontrar� os melhores produtos para sua agricultura categorizados em Defensivos, Fertilizantes e Sementes.	
					</p>
					<p>
						<a class="btn btn-default" href="#" role="button">Confira j�! &raquo;</a>
					</p>
				</div>
				<!-- /.col-lg-4 -->
				<div class="col-lg-4">
					<poc:img 
						path="/resources/img/utensilhos.jpg" 
						style="img-circle" 
						alt="Utens�lhos" 
						width="140" 
						height="140"/>
					<h2>Utens�lhos</h2>
					<p>
						Adquira os mais avan�ados utens�lhos de mercado para o seu neg�cio categorizados em Ferramentas, Jardinagem e Uso Geral.	
					</p>
					<p>
						<a class="btn btn-default" href="#" role="button">Confira j�! &raquo;</a>
					</p>
				</div>
				<!-- /.col-lg-4 -->
				<div class="col-lg-4">
					<poc:img 
						path="/resources/img/maquinas.jpg" 
						style="img-circle" 
						alt="M�quinas e Componentes" 
						width="140" 
						height="140"/>
					<h2>M�quinas e Componentes</h2>
					<p>
						Automatize seu trabalho atrav�s de m�quinas e componentes Implementos Agr�colas, Plantio e Pulveriza��o que maximizam os resultados do seu neg�cio.
					</p>
					<p>
						<a class="btn btn-default" href="#" role="button">Confira j�! &raquo;</a>
					</p>
				</div>
				<!-- /.col-lg-4 -->
			</div>
			<!-- /.row -->
	
	
			<!-- START THE FEATURETTES -->
	
			<hr class="featurette-divider">
	
			<div class="row featurette">
				<div class="col-md-7">
					<h2 class="featurette-heading">
						Variedade de produtos para o seu neg�cio. <span class="text-muted">Com o melhor pre�o do mercado.</span>
					</h2>
					<p class="lead">
						Ofere�emos aos nossos clientes os mais diversos produtos e marcas para que as necessidades de cada cliente sejam atendidas.
					</p>
				</div>
				<div class="col-md-5">
					<poc:img
						path="/resources/img/produtos.png"
						style="featurette-image img-responsive center-block"
						alt="Produtos"/>
				</div>
			</div>
	
			<hr class="featurette-divider">
	
			<div class="row featurette">
				<div class="col-md-7 col-md-push-5">
					<h2 class="featurette-heading">
						A partir de R$ 1.000,00 em compras fornecemos servi�o de entrega em at� 7 dias. <span class="text-muted">Sem custo adicional.</span>
					</h2>
					<p class="lead">
						Trabalhamos em parceria com os nosso clientes. Desse modo acime de R$ 1.000,00 em compras os clientes n�o precisam arcar com custos de frete para entrega de produtos.
					</p>
				</div>
				<div class="col-md-5 col-md-pull-7">
					<poc:img
						path="/resources/img/economia.png"
						style="featurette-image img-responsive center-block"
						alt="Frete Gr�tis"/>
				</div>
			</div>
	
			<hr class="featurette-divider">
	
			<div class="row featurette">
				<div class="col-md-7">
					<h2 class="featurette-heading">
						Descarte e recolhimento de produtos. <span class="text-muted">�tica e responsabilidade com o meio ambiente.</span>
					</h2>
					<p class="lead">
						Nos preocupamos com o meio ambiente. Em parceria com os nosso fornecedores, disponibilizamos o servi�o de 
						descarte de embalagens e de recolhimento de produtos agrot�xicos vencidos aos nosso clientes.
					</p>
				</div>
				<div class="col-md-5">
					<poc:img
						path="/resources/img/etica.png"
						style="featurette-image img-responsive center-block"
						alt="�tica"/>
				</div>
			</div>
	
			<hr class="featurette-divider">
	
			<!-- /END THE FEATURETTES -->
	
			<poc:footer-ecommerce/>
	
		</div>
		<!-- /.container -->
	</body>
</html>
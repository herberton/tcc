<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="author" content="Herberon Candido Souza">
	<poc:import-bootstrap/>
	<poc:import-css path="/resources/css/index.css" />
	<title>Agro</title>
</head>
	<body>
		<poc:navbar-ecommerce title="Home"/>
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
						alt="Promoções Especiais"/>
					<div class="container">
						<div class="carousel-caption">
							<h1>Promoções Especiais</h1>
							<p>Faça o cadastro e aproveite as promoções especiais exclusivas para clientes.</p>
							<p><a class="btn btn-lg btn-primary" href="#" role="button">Cadaste-se!</a></p>
						</div>
					</div>
				</div>
				<div class="item">
					<poc:img 
						path="/resources/img/fertilizante.jpg" 
						style="second-slide" 
						alt="Promoções Adubos"/>
					<div class="container">
						<div class="carousel-caption">
							<h1>Fertilizantes com o menor preço do mercado</h1>
							<p>Somente neste mês estamos com promoções de fertilizantes para as principais marcas do mercado.<p>
								<a class="btn btn-lg btn-primary" href="#" role="button">Confira já!</a>
							</p>
						</div>
					</div>
				</div>
				<div class="item">
					<poc:img 
						path="/resources/img/orcamento.jpg" 
						style="third-slide" 
						alt="Orçamentos"/>
					<div class="container">
						<div class="carousel-caption">
							<h1>Preço congelado por até 15 dias</h1>
							<p>Faça já orçamentos de nossos produtos e seguramos o preço por até <b>15 dias</b> para voçê.</p>
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
				<span class="sr-only">Próximo</span>
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
						Aqui você encontrará os melhores produtos para sua agricultura categorizados em Defensivos, Fertilizantes e Sementes.	
					</p>
					<p>
						<a class="btn btn-default" href="#" role="button">Confira já! &raquo;</a>
					</p>
				</div>
				<!-- /.col-lg-4 -->
				<div class="col-lg-4">
					<poc:img 
						path="/resources/img/utensilhos.jpg" 
						style="img-circle" 
						alt="Utensílhos" 
						width="140" 
						height="140"/>
					<h2>Utensílhos</h2>
					<p>
						Adquira os mais avançados utensílhos de mercado para o seu negócio categorizados em Ferramentas, Jardinagem e Uso Geral.	
					</p>
					<p>
						<a class="btn btn-default" href="#" role="button">Confira já! &raquo;</a>
					</p>
				</div>
				<!-- /.col-lg-4 -->
				<div class="col-lg-4">
					<poc:img 
						path="/resources/img/maquinas.jpg" 
						style="img-circle" 
						alt="Máquinas e Componentes" 
						width="140" 
						height="140"/>
					<h2>Máquinas e Componentes</h2>
					<p>
						Automatize seu trabalho através de máquinas e componentes Implementos Agrícolas, Plantio e Pulverização que maximizam os resultados do seu negócio.
					</p>
					<p>
						<a class="btn btn-default" href="#" role="button">Confira já! &raquo;</a>
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
						Variedade de produtos para o seu negócio. <span class="text-muted">Com o melhor preço do mercado.</span>
					</h2>
					<p class="lead">
						Ofereçemos aos nossos clientes os mais diversos produtos e marcas para que as necessidades de cada cliente sejam atendidas.
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
						A partir de R$ 1.000,00 em compras fornecemos serviço de entrega em até 7 dias. <span class="text-muted">Sem custo adicional.</span>
					</h2>
					<p class="lead">
						Trabalhamos em parceria com os nosso clientes. Desse modo acime de R$ 1.000,00 em compras os clientes não precisam arcar com custos de frete para entrega de produtos.
					</p>
				</div>
				<div class="col-md-5 col-md-pull-7">
					<poc:img
						path="/resources/img/economia.png"
						style="featurette-image img-responsive center-block"
						alt="Frete Grátis"/>
				</div>
			</div>
	
			<hr class="featurette-divider">
	
			<div class="row featurette">
				<div class="col-md-7">
					<h2 class="featurette-heading">
						Descarte e recolhimento de produtos. <span class="text-muted">Ética e responsabilidade com o meio ambiente.</span>
					</h2>
					<p class="lead">
						Nos preocupamos com o meio ambiente. Em parceria com os nosso fornecedores, disponibilizamos o serviço de 
						descarte de embalagens e de recolhimento de produtos agrotóxicos vencidos aos nosso clientes.
					</p>
				</div>
				<div class="col-md-5">
					<poc:img
						path="/resources/img/etica.png"
						style="featurette-image img-responsive center-block"
						alt="Ética"/>
				</div>
			</div>
	
			<hr class="featurette-divider">
	
			<!-- /END THE FEATURETTES -->
	
			<poc:footer-ecommerce/>
	
		</div>
		<!-- /.container -->
	</body>
</html>
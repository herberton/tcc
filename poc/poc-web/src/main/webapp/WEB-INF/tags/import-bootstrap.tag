<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags" %>

<%@ attribute name="system" required="true" description="ecommerce|backoffice" %>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<poc:import-css path="/resources/api/bootstrap/3.3.7/dist/css/bootstrap.min.css"/>
<poc:import-css path="/resources/api/bootstrap/3.3.7/assets/css/ie10-viewport-bug-workaround.css"/>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<poc:import-css path="/resources/css/global/${system}.css"/>

<poc:import-js path="/resources/api/jquery/1.12.4/jquery.min.js"/>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<poc:import-js path="/resources/api/bootstrap/3.3.7/assets/js/ie10-viewport-bug-workaround.js"/>

<poc:import-js path="/resources/api/bootstrap/3.3.7/dist/js/bootstrap.min.js"/>

<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="icon">
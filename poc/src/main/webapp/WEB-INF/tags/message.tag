<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="poc" tagdir="/WEB-INF/tags"%>
<c:choose>
	<c:when test="${message == null || message.text == ''}"></c:when>
	<c:when test="${message.isError == true}">
		<div class="alert alert-danger" role="alert">${message.text}</div>
	</c:when>
	<c:otherwise>
		<div class="alert alert-success" role="alert">${message.text}</div>
	</c:otherwise>
</c:choose>
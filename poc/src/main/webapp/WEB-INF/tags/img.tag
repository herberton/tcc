<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="style" required="false" %>
<%@ attribute name="alt" required="false" %>
<%@ attribute name="width" required="false" %>
<%@ attribute name="height" required="false" %>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}${path}"></script>
<img 
	class="${style}" 
	src="${pageContext.request.contextPath}${path}" 
	alt="${alt}"
	width="${width}"
	height="${height}">
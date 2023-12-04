<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/inicio.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
	
</script>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>
	<%@ include file="fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="fragmentos/header.jsp"%>
		<div class="signin">
		<h3>Novedades</h3>
			<div class="contenedornovedades">
				<c:forEach var="productoTemp" items="${listanovedades}">
					<a href="${pageContext.request.contextPath}/catalogo/verproducto?id=${productoTemp.id}"><img alt="${productoTemp.nombre }" src="${pageContext.request.contextPath}/resources/imagenes/productos/${ productoTemp.imagen}"></a>
				</c:forEach>
			</div>
			
			<div class="masvendido">
				<h2>MAS VENDIDO</h2>
				<a href="${pageContext.request.contextPath}/catalogo/verproducto?id=${masvendido.id}"><img class="imgmasvendido" alt="${masvendido.nombre }" src="${pageContext.request.contextPath}/resources/imagenes/productos/${ masvendido.imagen}"></a>
			</div>
		</div>
		<%@ include file="fragmentos/footer.jsp"%>
	</div>
</body>
</html>
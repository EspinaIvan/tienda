<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/catalogo.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
	
</script>
<script
	src="${pageContext.request.contextPath}/resources/js/Catalogo.js" defer></script>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>
	<section>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span>
	</section>
	<div class="contenedor">
		<%@ include file="fragmentos/header.jsp"%>
		<div class="signin">
			<h2>Detalle de la Cesta</h2>

			<table>
				<thead>
					<tr>
						<th>Imagen</th>
						<th>Título</th>
						<th>Precio Unidad</th>
						<th>Cantidad</th>
						<th>Precio Total</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="producto" items="${vercesta}">
						<tr>
							<td><img src="${producto.imagen}" alt="${producto.imagen}"
								width="50" height="50"></td>
							<td>${producto.nombre}</td>
							<td>${producto.precio}</td>
							<td>
								<button class="cantidad-menos" type="button"
									onclick="quitarCantidad(${ producto.id})">
									<span class="icono-menos">-</span>
								</button> <input path="cantidad" class="cantidad-valor" type="text"
									id="cantidad${ producto.id}" name="cantidad" value="${sessionScope.cesta[producto.id].cantidad}" />
								<button class="cantidad-mas" type="button"
									onclick="sumarCantidad(${ producto.id})">
									<span class="icono-mas">+</span>
								</button>
							</td>
							<c:set var="subtotal"
								value="${producto.precio * sessionScope.cesta[producto.id].cantidad}" />
							<td><fmt:formatNumber value="${subtotal}" type="currency"
									currencyCode="EUR" pattern="#,##0.00" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<%@ include file="fragmentos/footer.jsp"%>
	</div>
</body>
</html>

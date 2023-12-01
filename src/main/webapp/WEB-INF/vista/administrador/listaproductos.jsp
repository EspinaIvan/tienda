<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/listaproductos.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
	
</script>

<meta charset="UTF-8">
<title>Lista Productos</title>
</head>
<body>
	<%@ include file="../fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="../fragmentos/header.jsp"%>
		<div class="signin">
		<div class="nuevoproducto"><a href="añadirProducto"> + Agregar Producto </a>
		<span><a href="añadirPlataforma"> Lista Plataformas </a></span>
		</div>
			<div class="contenedor-cards">
				<table class="tabla">
					<thead>
						<tr>
							<th>Fecha De Alta</th>
							<th></th>
							<th>Nombre</th>
							<th>Precio</th>
							<th>stock</th>
							<th>Impuestos</th>
							<th>Activo</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="producto" items="${listaproductos}">
							<tr>
								<td>${producto.fecha_alta }</td>
								<td class="imagentabla"><img src="${pageContext.request.contextPath}/resources/imagenes/productos/${producto.imagen}"
									class="img-fluid rounded-3" alt="${producto.nombre}"></td>
								<td>
									<h5>${producto.nombre}</h5>
								</td>
								<td>${producto.precio }€</td>
								<td>${producto.stock }uds</td>
								<td>${producto.impuesto }%</td>
								<td><c:choose>
										<c:when test="${not producto.baja}">
											<span class="productoactivo"></span>
										</c:when>
										<c:otherwise>
											<span class="productobaja"></span>
										</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${not producto.baja}">
											<a href="${pageContext.request.contextPath}/administrador/bajaproducto?idproducto=${producto.id}">Dar de baja</a>
										</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath}/administrador/altaproducto?idproducto=${producto.id}">Dar de alta</a>
										</c:otherwise>
									</c:choose></td>
								<td><a href="${pageContext.request.contextPath}/administrador/editarproducto?idproducto=${producto.id}">Editar Producto</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<%@ include file="../fragmentos/footer.jsp"%>
	</div>
</body>
</html>

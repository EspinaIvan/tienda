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
		<div class="nuevoproducto"><a href="nuevaplataforma"> + Agregar Plataforma </a>
		</div>
			<div class="contenedor-cards">
				<table class="tabla">
					<thead>
						<tr>
							<th></th>
							<th>Nombre</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="plataforma" items="${listaplataformas}">
							<tr>
								<td class="imagentabla"><img src="${plataforma.imagen}"
									class="img-fluid rounded-3" alt="${plataforma.nombre}"></td>
								<td>
									<h5>${plataforma.nombre}</h5>
								</td>
								<td><a href="${pageContext.request.contextPath}/administrador/borrarplataforma?idproducto=${plataforma.id}">Borrar Plataforma</a></td>
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

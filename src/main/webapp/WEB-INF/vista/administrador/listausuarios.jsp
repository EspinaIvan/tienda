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
	href="${pageContext.request.contextPath}/resources/css/listausuarios.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
	
</script>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>
	<%@ include file="../fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="../fragmentos/header.jsp"%>
		<div class="signin">
			<table>
				<thead>
					<tr>
						<th>Id</th>
						<th>Usuario</th>
						<th>Email</th>
						<th>Fecha de Alta</th>
						<th>Baja
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="usuario" items="${listausuarios}">
						<c:if test="${usuario.roles.id == 1}">
							<tr>
								<td>${usuario.id }</td>
								<td>${usuario.usuario}</td>
								<td>${usuario.email}</td>
								<td>${usuario.fecha_alta}</td>
								<td><c:choose>
										<c:when test="${not usuario.baja}">
											<span class="usuarioactivo"></span>
										</c:when>
										<c:otherwise>
											<span class="usuariobaja"></span>
										</c:otherwise>
									</c:choose></td>
								<td><a href="${pageContext.request.contextPath}/administrador/verdetallesusuario?idusuario=${usuario.id }">Ver detalles</a></td>	
								</td>
								<td><a href="${pageContext.request.contextPath}/usuario/borrarusuario?idusuario=${usuario.id }">Dar de baja</a></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<%@ include file="../fragmentos/footer.jsp"%>
	</div>
</body>
</html>
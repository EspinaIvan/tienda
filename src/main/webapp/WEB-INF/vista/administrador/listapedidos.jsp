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
	href="${pageContext.request.contextPath}/resources/css/verpedidos.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
	
</script>
<meta charset="UTF-8">
<title>Mis Pedidos</title>
</head>
<body>
	<%@ include file="../fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="../fragmentos/header.jsp"%>
		<div class="signin">
			<table>
				<thead>
					<tr>
						<th>ID Usuario</th>
						<th>Fecha</th>
						<th>Método de Pago</th>
						<th>Total</th>
						<th>Estado</th>
						<th>Factura</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pedido" items="${listapedidos}">
						<tr>
							<td>${pedido.id_usuario }</td>
							<td>${pedido.fecha}</td>
							<td>${pedido.metodo_pago}</td>
							<td>${pedido.total}€</td>
							<td>${pedido.estado}</td>
							<td><c:choose>
									<c:when test="${not empty pedido.num_factura}">
							${pedido.num_factura}
							</c:when>
									<c:otherwise> --------
									</c:otherwise>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${pedido.estado eq 'P.E.'}">
										<a
											href="${pageContext.request.contextPath}/administrador/enviarpedido?idpedido=${pedido.id}">Aceptar
											Pedido</a>
									</c:when>
									<c:when
										test="${sessionScope.usuario.roles.id == 3 && pedido.estado eq 'P.C.'}">
										<a
											href="${pageContext.request.contextPath}/administrador/aceptarcancelarpedido?idpedido=${pedido.id}">Cancelar
											Pedido</a>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose></td>
							<td><c:if test="${not empty pedido.num_factura}">
									<button type="button"
										onclick="location.href='tu_enlace_factura'"
										class="btn btn-success">Factura</button>
								</c:if></td>
							<td><a
								href="${pageContext.request.contextPath}/usuario/detallespedido?idpedido=${pedido.id}">Ver
									Detalles</a>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<%@ include file="../fragmentos/footer.jsp"%>
	</div>
</body>
</html>
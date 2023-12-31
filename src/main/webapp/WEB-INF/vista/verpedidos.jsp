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
	<%@ include file="fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="fragmentos/header.jsp"%>
		<div class="signin">
			<c:choose>
				<c:when test="${ empty pedidos}">
					<h2>Lista de Pedido Vacia</h2>
				</c:when>
				<c:otherwise>
					<div class="ordenarfecha">
						<form action="verpedidos" method="get" class="formordenar">
							<label for="ordenarFecha">Ordenar por fecha:</label> <select
								name="ordenarFecha" id="ordenarFecha"
								class="custom-select form-control">
								<option value="DESC">Descendente</option>
								<option value="ASC">Ascendente</option>
							</select>
							<button class="btn btn-outline-secondary filtro" type="submit">Ordenar</button>
						</form>
						<form action="filtrarfecha" method="POST" class="formcalendario">
							<label>Desde:</label> <input type="date" class="form-control"
								name="fechaDesde" required> <label>Hasta:</label> <input
								type="date" class="form-control" name="fechaHasta" required>
							<button class="btn btn-outline-secondary filtro" type="submit">Buscar</button>
						</form>
					</div>
					<table>
						<thead>
							<tr>
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
							<c:forEach var="pedido" items="${pedidos}">
								<tr>
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
									<td><c:if test="${pedido.estado eq 'P.E.'}">
											<a
												href="${pageContext.request.contextPath}/usuario/cancelarpedido?idpedido=${pedido.id}">Cancelar
												Pedido</a>
										</c:if></td>
									<td><c:if test="${not empty pedido.num_factura}">
											<button type="button"
												onclick="location.href='${pageContext.request.contextPath}/usuario/verfactura?idpedido=${pedido.id }'"
												class="btn btn-success">Factura</button>
										</c:if></td>
									<td><a
										href="${pageContext.request.contextPath}/usuario/detallespedido?idpedido=${pedido.id}">Ver
											Detalles</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		<%@ include file="fragmentos/footer.jsp"%>
	</div>
</body>
</html>
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
	href="${pageContext.request.contextPath}/resources/css/detallespedido.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
	
</script>

<meta charset="UTF-8">
<title>Detalles Pedido</title>
</head>
<body>
	<%@ include file="fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="fragmentos/header.jsp"%>
		<div class="signin">
			<div class="contenedor-cards">
				<table class="tabla">
					<c:forEach var="detalles" items="${listaDetallesPedido}">
						<tr>
							<td style="width: 50%;"><img
								src="${detalles.producto.imagen}" class="img-fluid rounded-3"
								alt="${detalles.producto.imagen}"></td>
							<td>
								<h5>${detalles.producto.nombre}</h5>
							</td>
							<td>
								${detalles.precio_unidad } &euro
							</td>
							<td>
								${detalles.unidades }uds
							</td>
							<td>
								${detalles.impuesto }%
							</td>
							<td style="width: 80px; color: white"><c:set var="sumatotal"
									value="${detalles.precio_unidad * detalles.unidades}" /> <fmt:formatNumber
									value="${sumatotal}" type="currency" currencyCode="EUR"
									pattern="#,##€0.00" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<%@ include file="fragmentos/footer.jsp"%>
	</div>
</body>
</html>

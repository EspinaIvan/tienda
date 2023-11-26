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
	href="${pageContext.request.contextPath}/resources/css/verCesta.css">
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
			<div class="contenedor-cards">
				<c:if test="${fn:length(vercesta) == 0}">
					<div class="cestavacia">
						${cestavacia} <i class="bi bi-cart-x iconocestavacia"></i>
					</div>
				</c:if>
				<c:forEach var="producto" items="${vercesta}">
					<div class="card mb-3 contenedortarjeta">
						<div class="card-body">
							<div class="d-flex justify-content-between">
								<div class="d-flex flex-row align-items-center">
									<div style="width: 50%;">
										<img src="${producto.imagen}" class="img-fluid rounded-3"
											alt="${producto.imagen}">
									</div>
									<div class="ms-3">
										<h5>${producto.nombre}</h5>
									</div>
								</div>
								<div class="d-flex flex-row align-items-center">
									<div style="width: 100px;">
										<button class="cantidad-menos" type="button">
											<span class="icono-menos"><a
												href="${pageContext.request.contextPath}/cesta/modificarcantidad?id=${producto.id}&valor=restar">-</a></span>
										</button>
										<input path="cantidad" class="cantidad-valor" type="text"
											id="cantidad${producto.id}" name="cantidad"
											value="${sessionScope.cesta[producto.id].cantidad}" />
										<button class="cantidad-mas" type="button">
											<span class="icono-mas"><a
												href="${pageContext.request.contextPath}/cesta/modificarcantidad?id=${producto.id}&valor=sumar">+</a></span>
										</button>
									</div>
									<div style="width: 80px; color: white">
										<c:set var="sumatotal"
											value="${producto.precio * sessionScope.cesta[producto.id].cantidad} " />
										<fmt:formatNumber value="${sumatotal}" type="currency"
											currencyCode="EUR" pattern="#,##€0.00" />
									</div>
									<a
										href="${pageContext.request.contextPath}/cesta/borrararticulo?id=${producto.id}"
										style="color: #cecece;"><i class="bi bi-trash papelera"></i></a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="pagototal">
				<form:form modelAttribute="pedido" method="post"
					action="${pageContext.request.contextPath}/cesta/procesarpago">
					<div class="mensajestock">${noStock }</div>
					<label for="metodoPago">Método de Pago:</label>
					<form:select path="metodo_pago" id="metodoPago">
						<form:option value="EFECTIVO" label="Efectivo" />
						<form:option value="TARJETA_CREDITO" label="Tarjeta de Crédito" />
						<form:option value="TRANSFERENCIA" label="Transferencia Bancaria" />
						<form:option value="PAYPAL" label="PayPal" />

					</form:select>
					<div>
						Subtotal:
						<fmt:formatNumber value="${subtotal}" type="currency"
							currencyCode="EUR" pattern="#,##€0.00" />
					</div>
					<div>
						Impuestos:
						<fmt:formatNumber value="${impuestos }" type="currency"
							currencyCode="EUR" pattern="#,##€0.00" />
					</div>
					<hr>
					<c:set var="total" value="${subtotal + impuestos }" />
					<div>
						Total:
						<fmt:formatNumber value="${total}" type="currency"
							currencyCode="EUR" pattern="#,##€0.00" />
					</div>
					<c:set var="total" value="${subtotal + impuestos }" />
					<form:hidden path="total" id="total" value="${total }" />

					<input type="submit" value="Enviar" />
				</form:form>
			</div>
		</div>
		<%@ include file="fragmentos/footer.jsp"%>
	</div>
</body>
</html>

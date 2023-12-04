<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/factura.css">
<meta charset="UTF-8">
<title>Factura</title>
</head>
<body>
	<div class="datosempresa">
		<img alt=""
			src="${pageContext.request.contextPath}/resources/imagenes/recursosweb/${ sessionScope.logo.valor}">
		<h2>${ sessionScope.nombre.valor }</h2>
		<p>${ sessionScope.direccion.valor }</p>
		<p>${ sessionScope.CIF.valor }</p>
		<p>${ sessionScope.email.valor }</p>
	</div>
	<div class="datospedido">
		<p>${ pedido.fecha }</p>
		<p>${ pedido.num_factura }</p>
		<p>${ pedido.metodo_pago }</p>

		<table>
			<thead>
				<tr>
					<th>Producto</th>
					<th>Unidades</th>
					<th>P. Unidad</th>
					<th>Impuestos</th>
					<th>Subtotal</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="totalGeneral" value="0" />
				<c:forEach var="detalles" items="${listaDetallesPedido}">
					<tr>
						<td>${detalles.producto.nombre}</td>
						<td>${detalles.unidades}uds</td>
						<td>${detalles.precio_unidad}€</td>
						<td>${detalles.impuesto}%</td>
						<c:set var="totalDetalles"
							value="${detalles.unidades * detalles.precio_unidad}" />
						<c:set var="totalConImpuestos"
							value="${totalDetalles + (totalDetalles * detalles.impuesto / 100)}" />
						<td><fmt:formatNumber value="${totalConImpuestos}"
								type="currency" currencyCode="EUR" maxFractionDigits="2" /></td>
						<c:set var="totalGeneral"
							value="${totalGeneral + totalConImpuestos}" />
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" style="text-align: right;">Total General:</td>
					<td><fmt:formatNumber value="${totalGeneral}" type="currency"
							currencyCode="EUR" maxFractionDigits="2" /></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
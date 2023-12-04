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
						<th></th>
						<th></th>
						<th></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="configuracion" items="${configuraciones}">
						<tr>
							<td>${configuracion.clave }</td>
							<td><form method="POST" action="cambiarconfiguracion">
								<input type="text" value="${configuracion.valor }" name="valor" >
								<input type="hidden" value="${configuracion.id}" name="id">
							</td>
							<td><input type="submit" value="Cambiar Datos"></form></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<%@ include file="../fragmentos/footer.jsp"%>
	</div>
</body>
</html>
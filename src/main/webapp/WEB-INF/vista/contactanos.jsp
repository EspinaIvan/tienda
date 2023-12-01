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
	href="${pageContext.request.contextPath}/resources/css/contactanos.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
	
</script>
<meta charset="UTF-8">
<title>Contactanos</title>
</head>
<body>
	<%@ include file="fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="fragmentos/header.jsp"%>
		<div class="signin">
			<c:if test="${param.enviado == 'true'}">
				<p class="enviocorrecto">Mensaje Enviado Correctamente</p>
			</c:if>
			<form action="enviarCorreo" method="post">
				<label for="destinatario">Tu Email:</label> <input type="text"
					id="destinatario" name="remitente"
					value="${sessionScope.usuario.email }" readonly="readonly">
				<br> <label for="asunto">Asunto:</label> <input type="text"
					id="asunto" name="asunto" required> <br> <label
					for="cuerpo">Cuerpo:</label>
				<textarea id="cuerpo" name="cuerpo" required></textarea>
				<br>
				<button type="submit">Enviar Correo</button>
			</form>
		</div>
		<%@ include file="fragmentos/footer.jsp"%>
	</div>
</body>
</html>
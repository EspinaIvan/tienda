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
	href="${pageContext.request.contextPath}/resources/css/editarclave.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
	
</script>
<script
	src="${pageContext.request.contextPath}/resources/js/cambiarclave.js" defer></script>

<meta charset="UTF-8">
<title>Cambiar Contraseña</title>
</head>
<body>
	<%@ include file="../fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="../fragmentos/header.jsp"%>
		<div class="signin">
			<div class="content">
				<form method="POST" action="cambiarclave" class="form" id="cambiarclave">
					<div class="inputBox">
						<input name="nuevaclave" type="password" /> <i>Contraseña
							Nueva</i>
						<div id="claveError" class="error-message"></div>
					</div>
					<div class="inputBox">
						<input name="repetirnuevaclave" type="password" /> <i>Repetir
							Contraseña Nueva</i>
						<div id="repetirClaveError" class="error-message"></div>
					</div>
					<input type="hidden" name="idusuario" value="${idusuario }">
					<div class="inputBox botonregistro">
						<input type="submit" value="Actualizar Contraseña">
					</div>
				</form>
			</div>
		</div>
		<%@ include file="../fragmentos/footer.jsp"%>
	</div>
</body>
</html>
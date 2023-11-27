<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/registro.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/registro.js" defer></script>
<meta charset="UTF-8">
<title>Formulario Registro</title>
</head>
<body>
	<%@ include file="fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="fragmentos/header.jsp"%>
		<div class="signin">
			<div class="content">
				<h2>Registro</h2>
				<form:form class="form" action="insertarUsuario"
					modelAttribute="usuario" method="POST" id="registroForm">
					<div class="inputBox">
						<form:input path="usuario" type="text" />
						<i>Usuario</i>
						<form:errors path="usuario" cssClass="error" />
						<div id="usuarioError" class="error-message">${ExisteUsuario}</div>
					</div>
					<div class="inputBox">
						<form:input path="email" type="text" />
						<i>Email</i>
						<form:errors path="email" cssClass="error" />
						<div id="usuarioError" class="error-message">${ExisteEmail}</div>
					</div>
					<div class="inputBox">
						<form:input path="clave" type="password" />
						<i>Contraseña</i>
						<form:errors path="clave" cssClass="error" />
						<div id="claveError" class="error-message"></div>
					</div>
					<div class="inputBox">
						<form:input path="repetirClave" type="password" />
						<i>Repetir Contraseña</i>
						<form:errors path="repetirClave" cssClass="error" />
						<div id="repetirClaveError" class="error-message"></div>
					</div>
					<div class="links">
						<p class="yacuenta">¿Ya Tienes Cuenta?</p>
						<a class="iniciasession" href="login">Inicia Sesión</a>
					</div>
					<div class="inputBox botonregistro">
						<input type="submit" value="Registrarse">
					</div>
				</form:form>
			</div>

		</div>
		<%@ include file="fragmentos/footer.jsp"%>

	</div>
</body>
</html>
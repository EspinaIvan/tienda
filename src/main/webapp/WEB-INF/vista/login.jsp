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
	href="${pageContext.request.contextPath}/resources/css/login.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/registro.js" defer></script>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<section>

		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>

		<div class="signin">
			<div class="content">
				<h2>Inicio de Sesion</h2>
				<form:form class="form" action="iniciarSesion"
					modelAttribute="usuario" method="POST" id="loginForm">
					<div class="inputBox">
						<form:input path="usuario" type="text"/>
						<i>Usuario</i>
					</div>
					<div class="inputBox">
						<form:input path="clave" type="password"/>
						<i>Contraseña</i>
					</div>
					<div class="links">
						<p>¿No tienes cuenta? <a href=registro>Registrate</a></p>
					</div>
					<div class="inputBox">
						<input type="submit" value="Login">
					</div>
				</form:form>
			</div>
		</div>
	</section>
</body>
</html>
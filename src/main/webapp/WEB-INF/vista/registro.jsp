<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="ISO-8859-1">
<title>Formulario Registro</title>
</head>
<body>
	<%@ include file="fragmentos/header.jsp"%>
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
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span> <span></span> <span></span> <span></span>
		<span></span> <span></span>

		<div class="signin">
			<div class="content">
				<h2>Registro</h2>
				<form:form class="form" action="insertarUsuario"
					modelAttribute="usuario" method="POST">
					<div class="inputBox">
						<form:input path="usuario" type="text" />
						<i>Usuario</i>
						<form:errors path="usuario" cssClass="error" />
					</div>
					<div class="inputBox">
						<form:input path="nombre" type="text" />
						<i>Nombre</i>
						<form:errors path="nombre" cssClass="error" />
					</div>
					<div class="inputBox">
						<form:input path="apellido1" type="text" />
						<i>Primer Apellido</i>
						<form:errors path="apellido1" cssClass="error" />
					</div>
					<div class="inputBox">
						<form:input path="apellido2" type="text" />
						<i>Segundo Apellido</i>
						<form:errors path="apellido2" cssClass="error" />
					</div>
					<div class="inputBox">
						<form:input path="dni" type="text" />
						<i>DNI</i>
						<form:errors path="dni" cssClass="error" />
					</div>
					<div class="inputBox">
						<form:input path="email" type="text" />
						<i>Email</i>
						<form:errors path="email" cssClass="error" />
					</div>
					<div class="inputBox">
						<form:input path="telefono" type="text" />
						<i>Telefono</i>
						<form:errors path="telefono" cssClass="error" />
					</div>
					<div class="inputBox">
						<form:input path="clave" type="password" />
						<i>Contrase�a</i>
						<form:errors path="clave" cssClass="error" />
					</div>
					<div class="inputBox">
						<form:input path="repetirClave" type="password" />
						<i>Repetir Contrase�a</i>
						<form:errors path="repetirClave" cssClass="error" />
					</div>
					<div class="inputBox">
						<form:input path="provincia" type="text" />
						<i>Provincia</i>
					</div>
					<div class="inputBox">
						<form:input path="localidad" type="text" />
						<i>Localidad</i>
					</div>
					<div class="inputBox">
						<form:input path="direccion" type="text" />
						<i>Direccion</i>
					</div>
					<div class="inputBox">
						<form:input path="id_rol" type="hidden" value="1" />
					</div>
					<div class="inputBox">
						<form:input path="baja" type="hidden" value="false" />
					</div>
					<div class="links">
						<p class="yacuenta">�Ya Tienes Cuenta?</p>
						<a  class="iniciasession" href="#">Inicia Sesi�n</a>
					</div>
					<div class="inputBox botonregistro">
						<input type="submit" value="Registrarse">
					</div>
				</form:form>
			</div>

		</div>

	</section>
	<%@ include file="fragmentos/footer.jsp"%>
</body>
</html>
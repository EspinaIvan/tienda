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
	href="${pageContext.request.contextPath}/resources/css/editarperfil.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/registro.js" defer></script>
<meta charset="UTF-8">
<title>Perfil</title>
</head>
<body>
	<%@ include file="fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="fragmentos/header.jsp"%>
		<div class="signin">
			<div class="content">
				<form:form class="form" action="actualizarusuario"
					modelAttribute="usuario" method="POST" id="registroForm"
					enctype="multipart/form-data">
					<div class="contenedorimagen">
						<img class="imagenfile" alt=""
							src="${pageContext.request.contextPath}/resources/imagenes/usuarios/${ sessionScope.usuario.imagen}">
						<input name="imagenavatar" type="file" class="form-control"
							id="formFile" />
					</div>
					<div class="inputBox">
						<form:input path="usuario" type="text" />
						<i>Usuario</i>
						<form:errors path="usuario" cssClass="error" />
						<div id="usuarioError" class="error-message">${ExisteUsuario}</div>
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
						<form:input path="dni" type="text" maxlength="9" />
						<i>DNI</i>
						<form:errors path="dni" cssClass="error" />
					</div>
					<div class="inputBox">
						<form:input path="email" type="text" />
						<i>Email</i>
						<form:errors path="email" cssClass="error" />
						<div id="usuarioError" class="error-message">${ExisteEmail}</div>
					</div>
					<div class="inputBox">
						<form:input path="telefono" type="text" maxlength="9" />
						<i>Telefono</i>
						<form:errors path="telefono" cssClass="error" />
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
					<div class="links">
						<p class="yacuenta">¿Deseas cambiar, la contraseña?</p>
						<a class="iniciasession"
							href="${pageContext.request.contextPath}/usuario/cambiarclave">Cambiar
							Contraseña</a>
					</div>
					<div class="inputBox botonregistro">
						<input type="submit" value="Actuarlizar Datos">
					</div>
				</form:form>
				<c:if test="${sessionScope.usuario.roles.id == 1}">
					<div class="inputBox contenedorborrar">
						<a href="borrarusuario?idusuario=${sessionScope.usuario.id }"><button
								type="button" class="btn btn-outline-light me-2 botonborrar">Eliminar
								Cuenta</button></a>
					</div>
				</c:if>
			</div>

		</div>
		<%@ include file="fragmentos/footer.jsp"%>

	</div>
</body>
</html>
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
	href="${pageContext.request.contextPath}/resources/css/editarproducto.css">
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
	<%@ include file="../fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="../fragmentos/header.jsp"%>
		<div class="signin">
			<div class="content">
				<h2>Registro</h2>
				<form:form class="form" action="procesarproducto" modelAttribute="producto"
					method="POST" enctype="multipart/form-data">
					<div class="inputBox">
						<form:input path="nombre" type="text" />
						<i>Nombre</i>
					</div>
					<div class="inputBox">
						<form:input path="precio" type="text" />
						<i>Precio</i>
					</div>
					<div class="inputBox">
						<form:input path="stock" type="text" />
						<i>Stock</i>
					</div>
					<div class="inputBox">
						<form:input path="impuesto" type="text" />
						<i>Impuestos</i>
					</div>
					<div class="selecttextbox">
						<div class="inputBox">
							<label for="plataforma.id">Plataforma</label>
							<form:select path="plataforma.id">
								<form:option value="3">XboxX</form:option>
								<form:option value="2">PlayStation5</form:option>
								<form:option value="2">PC</form:option>
								<form:option value="4">Swicht</form:option>
							</form:select>
						</div>
						<div class="inputBox">
							<form:textarea path="descripcion" placeholder="Descripcion"></form:textarea>
						</div>
						<input name="imagen" type="file"/>
					</div>
					<div class="links">
						<p class="yacuenta">¿Ya Tienes Cuenta?</p>
						<a class="iniciasession" href="login">Inicia Sesión</a>
					</div>
					<div class="inputBox botonregistro">
						<input type="submit" value="Actualizar Producto">
					</div>
				</form:form>
			</div>

		</div>
		<%@ include file="../fragmentos/footer.jsp"%>

	</div>
</body>
</html>
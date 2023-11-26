<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/catalogo.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
	
</script>
<script
	src="${pageContext.request.contextPath}/resources/js/Catalogo.js" defer></script>
<meta charset="UTF-8">
<title>Catalogo</title>
</head>
<body>
	<%@ include file="fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="fragmentos/header.jsp"%>
		<div class="signin">
			<div class="card-deck contenedorcarta">
				<c:forEach var="productoTemp" items="${catalogo}">
					<div class="catalogo" style="width: 15rem; margin-bottom: 10px;">
						<a
							href="${pageContext.request.contextPath}/catalogo/verproducto?id=${productoTemp.id}"
							class="enlaceimagen"><img class="card-img-top img-catalogo"
							src="${ productoTemp.imagen}" alt="Card image cap" width=""></a>
						<div class="card-body">
							<h5 class="card-title titulo">${ productoTemp.nombre}</h5>
							<p class="card-text">${ productoTemp.precio}&euro;</p>
							<c:choose>
								<c:when test="${productoTemp.stock eq 0}">
									<span class="sinsctok">¡Sin stock!</span>
								</c:when>
								<c:otherwise>
									<form:form class="form" action="añadirProducto"
										modelAttribute="producto" method="POST">
										<div class="control-cantidad">
											<form:hidden path="producto.id" value="${ productoTemp.id}" />
											<button class="cantidad-menos" type="button"
												onclick="quitarCantidad(${ productoTemp.id})">
												<span class="icono-menos">-</span>
											</button>
											<form:input path="cantidad" class="cantidad-valor"
												type="text" id="cantidad${ productoTemp.id}" name="cantidad"
												value="1" />
											<button class="cantidad-mas" type="button"
												onclick="sumarCantidad(${ productoTemp.id})">
												<span class="icono-mas">+</span>
											</button>
											<button type="submit" class="btn btn-primary botoncesta">Añadir
												Cesta</button>
										</div>
									</form:form>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<%@ include file="fragmentos/footer.jsp"%>
	</div>
</body>
</html>
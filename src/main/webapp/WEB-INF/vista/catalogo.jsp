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
<script>
        function validarNumero(input) {
            // Eliminar los caracteres no numéricos
            input.value = input.value.replace(/[^0-9]/g, '');
            
            if(input.value > 99) {
            	input.value=99;
            }
            
            if(input.value == "") {
            	input.value=1;
            }
        }
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
			<div class="contenedorfiltro">
				<div class="input-group mb-3">
					<div class="contenedorporpalabras">
						<form method="post"
							action="${pageContext.request.contextPath}/catalogo/busquedapalabra">
							<i class="bi bi-search lupa"></i><input class="form-control"
								name="busqueda" type="search" placeholder="Buscar">
							<div class="input-group-append">
								<input class="btn btn-outline-secondary" type="submit"
									value="Buscar" />
							</div>
						</form>
					</div>
				</div>
				<div class="input-group mb-3">
					<div class="contenedorplataforma">
						<form method="post"
							action="${pageContext.request.contextPath}/catalogo/busquedaselect">
							<select name="idplataforma" class="custom-select form-control"
								id="inputGroupSelect01">
								<option value="0" selected>Elige plataforma</option>
								<c:forEach var="plataforma" items="${listaplataformas}">
									<option value="${plataforma.id}">${plataforma.nombre}</option>
								</c:forEach>
							</select>
							<div class="input-group-append botonselect">
								<input class="btn btn-outline-secondary" type="submit"
									value="Buscar" />
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="card-deck contenedorcarta">
				<c:forEach var="productoTemp" items="${catalogo}">
					<c:if test="${not productoTemp.baja}">
						<div class="catalogo" style="width: 15rem; margin-bottom: 10px;">
							<a
								href="${pageContext.request.contextPath}/catalogo/verproducto?id=${productoTemp.id}"
								class="enlaceimagen"><img class="card-img-top img-catalogo"
								src="${pageContext.request.contextPath}/resources/imagenes/productos/${ productoTemp.imagen}"
								alt="Card image cap" width=""></a>
							<div class="card-body">
								<h5 class="card-title titulo">${ productoTemp.nombre}</h5>
								<div class="contenedorprecioplataforma">
									<p class="card-text">${ productoTemp.precio}&euro;</p>
									<span><img class="imagenplataforma" alt=""
										src="${productoTemp.plataforma.imagen }"></span>
								</div>
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
													type="text" id="cantidad${ productoTemp.id}"
													name="cantidad" value="1" oninput="validarNumero(this)" />
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
					</c:if>
				</c:forEach>
			</div>
		</div>
		<%@ include file="fragmentos/footer.jsp"%>
	</div>
</body>
</html>
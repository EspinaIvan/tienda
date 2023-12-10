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
	href="${pageContext.request.contextPath}/resources/css/detalleproducto.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
	
</script>
<script
	src="${pageContext.request.contextPath}/resources/js/Catalogo.js" defer></script>
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
<meta charset="UTF-8">
<title>Detalles Producto</title>
</head>
<body>
	<%@ include file="fragmentos/span.jsp"%>
	<div class="contenedor">
		<%@ include file="fragmentos/header.jsp"%>
		<div class="signin">
			<div class="card-deck contenedorcarta">
				<div class="catalogo">
					<img class="card-img-top img-catalogo"
						src="${pageContext.request.contextPath}/resources/imagenes/productos/${ productoBD.imagen}"
						alt="Card image cap" width="">
					<div class="card-body">
						<h3 class="card-title titulo">${ productoBD.nombre}</h3>
						<h5 class="card-title descripcion">${productoBD.descripcion }</h5>
						<p class="card-text">${ productoBD.precio}&euro;</p>
						<c:choose>
							<c:when test="${productoBD.stock eq 0}">
								<span class="sinsctok">¡Sin stock!</span>
							</c:when>
							<c:otherwise>
								<form:form class="form" action="añadirProducto"
									modelAttribute="producto" method="POST">
									<div class="control-cantidad">
										<form:hidden path="producto.id" value="${ productoBD.id}" />
										<button class="cantidad-menos" type="button"
											onclick="quitarCantidad(${ productoBD.id})">
											<span class="icono-menos">-</span>
										</button>
										<form:input path="cantidad" class="cantidad-valor" type="text"
											id="cantidad${ productoBD.id}" name="cantidad" value="1"
											oninput="validarNumero(this)" />
										<button class="cantidad-mas" type="button"
											onclick="sumarCantidad(${ productoBD.id})">
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
			</div>
		</div>
		<%@ include file="fragmentos/footer.jsp"%>
	</div>
</body>
</html>
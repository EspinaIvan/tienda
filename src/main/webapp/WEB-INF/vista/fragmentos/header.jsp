<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<header class="p-3 text-white cabecera">
	<div class="container">
		<div
			class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			<c:choose>
				<c:when test="${empty sessionScope.usuario}">
					<ul
						class="nav col-12 col-lg-auto mb-2 justify-content-center mb-md-0 navegacion">
						<li><a href="${pageContext.request.contextPath}/"
							class="nav-link px-2 text-secondary">Inicio</a></li>
						<li><a
							href="${pageContext.request.contextPath}/catalogo/vercatalogo"
							class="nav-link px-2 text-white">Catalogo</a></li>
					</ul>
				</c:when>
				<c:otherwise>

					<ul
						class="nav col-12 col-lg-auto mb-2 justify-content-center mb-md-0 navegacion">

						<c:forEach var="menu" 
							items="${ sessionScope.usuario.roles.opcionesMenu}">
							<li><a href="${menu.url_opcion}"
								class="nav-link px-2 text-secondary">${menu.nombre_opcion }</a></li>
						</c:forEach>
					</ul>
				</c:otherwise>
			</c:choose>
			<div class="text-end contenedor-carrito">
				<c:choose>
					<c:when test="${empty sessionScope.usuario}">
						<a href="${pageContext.request.contextPath}/usuario/login"><button
								type="button" class="btn btn-outline-light me-2 inicioboton">Iniciar
								Session</button></a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/usuario/verperfil">
							<img class="avatar" src="${ sessionScope.usuario.imagen}"
							alt="Card image cap">
						</a>
					</c:otherwise>
				</c:choose>
				<c:if test="${not mostrarBotonRegistro}">
					<c:choose>
						<c:when test="${empty sessionScope.usuario}">
							<a href="${pageContext.request.contextPath}/usuario/registro">
								<button type="button" class="btn btn-warning registroboton">
									Registro</button>
							</a>
						</c:when>
						<c:otherwise>
							<a class="enlacecerrarsesion"
								href="${pageContext.request.contextPath}/usuario/cerrarsesion">
								<i class="bi bi-box-arrow-right cerrarsesion"></i>
							</a>
						</c:otherwise>
					</c:choose>
				</c:if>
				<a href="${pageContext.request.contextPath}/cesta/vercesta"
					class="enlace-cesta"><i class="bi bi-cart4 icon-carrito"></i></a>
				<c:if test="${fn:length(cesta) > 0}">
					<span class="cesta-articulo">${fn:length(sessionScope.cesta)}</span>
				</c:if>
			</div>
		</div>
	</div>
</header>
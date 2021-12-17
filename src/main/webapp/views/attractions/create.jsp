<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Crear Atracciones</title>
</head>
<body>

	 <header class="container-fluid bg-secondary bg-gradient text-white pt-1">
        <div class="row justify-content-between">
            <div class="col-3">
                <h3>Visitante: <c:out value="${user.username}"></c:out></h3>
                <h5>Dinero disponible: <c:out value="${user.coins}"></c:out></h5>
                <h5>Tiempo disponible: <c:out value="${user.time}"></c:out></h5>
            </div>
            <div class="col-2 mt-2">
                <a class="btn bg-warning bg-gradient" aria-current="page" href="/TierraMedia/logout">Cerrar Sesion</a>
            </div>
        </div>
        <nav class="bg-transparent text-white container-fluid mt-3">
            <div class="row d-flex justify-content-between text-center">
                <div class="col-2">
                    <a class="nav-link border border-dark border-3 bg-dark text-white rounded"
                        href="../index.jsp">Inicio</a>
                </div>
                <c:if test="${user.isAdmin()}">
						<div class="col-2">
                    <a class="nav-link border border-dark border-3 bg-dark text-white rounded" aria-current="page" href="../users/index.do">Usuarios</a></div>
					</c:if>
                <div class="col-2">
                    <a class="nav-link border border-dark border-3 bg-dark text-white rounded" aria-current="page" href="./index.do">Atracciones</a>
                </div>
                <div class="col-2">
                    <a class="nav-link border border-dark border-3 bg-dark text-white rounded" href="../promos/index.do">Promociones</a>
                </div>
                <div class="col-2">
                    <a class="nav-link border border-dark border-3 bg-dark text-white rounded" href="../itinerario/index.do">Itinerario</a>
                </div>
            </div>
        </nav>
    </header>

	<main class="container">

		<c:if test="${attraction != null && !attraction.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al crear la atracción.</p>
			</div>
		</c:if>

		<form action="/TierraMedia/attractions/create.do" method="post">
			<jsp:include page="/views/attractions/form.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>

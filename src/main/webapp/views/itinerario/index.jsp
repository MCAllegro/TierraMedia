<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/jq-3.6.0/dt-1.11.3/af-2.3.7/b-2.1.1/cr-1.5.5/date-1.1.1/fc-4.0.1/fh-3.2.0/kt-2.6.4/r-2.2.9/rg-1.1.4/rr-1.2.8/sc-2.0.5/sb-1.3.0/sp-1.4.0/sl-1.3.3/sr-1.0.1/datatables.min.css"/>
 
<script type="text/javascript" src="https://cdn.datatables.net/v/bs5/jq-3.6.0/dt-1.11.3/af-2.3.7/b-2.1.1/cr-1.5.5/date-1.1.1/fc-4.0.1/fh-3.2.0/kt-2.6.4/r-2.2.9/rg-1.1.4/rr-1.2.8/sc-2.0.5/sb-1.3.0/sp-1.4.0/sl-1.3.3/sr-1.0.1/datatables.min.js"></script>
<script type="text/javascript">
window.addEventListener('DOMContentLoaded', function() {
	$('.datatable').DataTable ({
		colReorder: true
		
	});
}</script>
<jsp:include page="/partials/head.jsp"></jsp:include>
<link rel="stylesheet" href="assets/stylesheets/styles.css">
</head>
<body>

	    <header class="container-fluid bg-secondary bg-gradient text-white pt-1">
        <div class="row justify-content-between">
            <div class="col-2">
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
                    <a class="nav-link border border-dark border-3 bg-dark text-white rounded" aria-current="page" href="../attractions/index.do">Atracciones</a>
                </div>
                <div class="col-2">
                    <a class="nav-link border border-dark border-3 bg-dark text-white rounded" href="../promos/index.do">Promociones</a>
                </div>
                <div class="col-2">
                    <a class="nav-link border border-dark border-3 bg-dark text-white rounded" href="./index.do">Itinerario</a>
                </div>
            </div>
        </nav>
    </header>
    <main class="container-fluid">
		<article class="container mt-4 text-center">
			<div class="row">
				<h3>Lista de Tus Compras</h3>
			</div>
			<div class="row justify-content-center mt-2">
			<table class="table table-responsive">
			<c:if test="${user.comprasAnteriores()}">
					<thead>
						<tr>
							<th scope="col">Nombre</th>
							<th scope="col">Costo</th>
							<th scope="col">Duracion</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaIti}" var="item">
							<tr>
								<td><c:out value="${item.name}"></c:out></td>
								<td><c:out value="${item.cost}"></c:out></td>
								<td><c:out value="${item.duration}"></c:out>Hs</td>
							</tr>
						</c:forEach>
					</tbody>
					</c:if>
				</table>
			</div>
		</article>
	</main>
</body>
</html>
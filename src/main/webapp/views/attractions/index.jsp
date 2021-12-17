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
    <main class="container-fluid">
    <c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>
        <article class="container mt-4 text-center">
            <div class="row">
                <h3>Atracciones de la Tierra Media</h3>
            </div>
            
        </article>
    <c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/TierraMedia/attractions/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Atracción
				</a>
			</div>
		</c:if>
		<div class="container my-3"></div>
		<div class="table-responsive"></div>
		<table class="datatable table table-dark table-bordered table-stripped" class="display" style="width:100%">
			<thead>
				<tr>
					<th>Atracci&oacute;n</th>
					<th>Costo</th>
					<th>Duraci&oacute;n</th>
					<th>Cupo</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${attractions}" var="attraction">
					<tr>
						<td><strong><c:out value="${attraction.name}"></c:out></strong>
							</td>
						<td><c:out value="${attraction.cost}"></c:out></td>
						<td><c:out value="${attraction.duration}"></c:out></td>
						<td><c:out value="${attraction.capacity}"></c:out></td>

						<td><c:if test="${user.admin}">
								<a href="/TierraMedia/attractions/edit.do?id=${attraction.id}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/TierraMedia/attractions/delete.do?id=${attraction.id}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if> 
							
							<c:choose>
								<c:when
									test="${user.canAfford(attraction) && user.canAttend(attraction) && attraction.canHost()}">
									<a href="/TierraMedia/attractions/buy.do?id=${attraction.id}"
										class="btn btn-success rounded" role="button">Comprar</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-secondary rounded disabled"
										role="button">Dinero Insuficiente</a>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>
</body>

</html>
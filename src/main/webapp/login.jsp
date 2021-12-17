<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>

<jsp:include page="partials/head.jsp"></jsp:include>
<link rel="stylesheet" href="assets/stylesheets/styles.css">
</head>
<body>

	 <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
                    <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#"
                                id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false"><i id="iconoPersonLogint" class="bi bi-person-circle"></i>
                                Iniciar Sesión </a>
                            <form class="dropdown-menu p-4 me-4" action="login" method="post" id="loginForm">
                                <c:if test="${flash != null}">
                                    <div class="alert alert-danger">
                                        <p>
                                            <c:out value="${flash}" />
                                        </p>
                                    </div>
                                </c:if>
                                <div class="mb-3 me-3">
                                    <label for="username" class="form-label">Usuario</label>
                                    <input type="text" class="form-control" name="username" required="required">
                                </div>

                                <div class="mb-3 me-3">
                                    <label for="password" class="form-label">Contraseña</label>
                                    <input type="password" class="form-control" name="password" required="required">
                                </div>
                                <div class="d-grid gap-2 me-3">
                                    <button type="submit" class="btn btn-lg btn-primary">Ingresar</button>
                                </div>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <main class="w-100" id="fondoLogin">
        <div class="container-fluid">
            <div class="row py-5 text-white text-center">
            <c:if test="${success != null}">
								<div class="alert alert-success">
									<p>
										<c:out value="${success}" />
									</p>
								</div>
							</c:if>
                <h2>VIVÍ LA EXPERIENCIA</h2>
                <h2>TIERRA MEDIA</h2>
                <h5>INICIÁ SESIÓN PARA ACCEDER AL CONTENIDO</h5>
            </div>
            <div class="d-flex justify-content-center">
				<div id="carouselExampleControls" class="carousel slide w-50"
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="assets/img/login-carrousel-1-final.jpg" class="d-block w-100" alt="IMAGEN PROMOCIONAL">
						</div>
						<div class="carousel-item">
							<img src="assets/img/login-carrousel-2-final.jpg" class="d-block w-100" alt="IMAGEN PROMOCIONAL">
						</div>
						<div class="carousel-item">
							<img src="assets/img/login-carrousel-3-final.jpg" class="d-block w-100" alt="IMAGEN PROMOCIONAL">
						</div>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleControls" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleControls" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
				</div>
        </div>

    </main>

</body>

</html>
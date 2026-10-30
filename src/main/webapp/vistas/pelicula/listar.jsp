<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/styles.css"/>
        <title>Listado películas</title>
    </head>
    <body>
        <div class="tabla">
            <h1 class="page-title">Lista de películas</h1>
            <hr>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn-tabla">Volver al inicio</a>
            <a href="PeliculaControlador?accion=nuevo" class="btn-tabla">Nueva</a>
            <table>
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Título</th>
                        <th>Precio</th>
                        <th>Duración</th>
                        <th>Idioma</th>
                        <th>Género</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${peliculas}" var="item">
                        <tr>
                            <td>${item.codigo}</td>
                            <td>${item.titulo}</td>
                            <td>${item.precio}</td>
                            <td>${item.duracion} min</td>
                            <td>${item.idioma}</td>
                            <td>${item.genero}</td>
                            <td>
                                <a href="PeliculaControlador?accion=editar&codigo=${item.codigo}" class="btn-tabla">Modificar</a>
                                <a href="PeliculaControlador?accion=borrar&codigo=${item.codigo}" class="btn-tabla">Borrar</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${peliculas.size() == 0}">
                        <tr>
                            <td colspan="7">No hay registros de películas.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>

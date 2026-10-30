<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/styles.css"/>
        <title>${pelicula == null ? "Nueva" : "Editar"} película</title>
    </head>
    <body>
        <div class="tabla">
            <h1 class="page-title">${pelicula == null ? "Nueva" : "Editar"} película</h1>
            <hr>
            <form action="PeliculaControlador" method="post">
                <c:if test="${pelicula != null}">
                    <input type="hidden" name="codigo" value="${pelicula.codigo}">
                </c:if>
                <table>
                    <tr>
                        <td>Título:</td>
                        <td><input type="text" name="titulo" value="${pelicula.titulo}" required></td>
                    </tr>
                    <tr>
                        <td>Precio:</td>
                        <td><input type="number" step="0.01" name="precio" value="${pelicula.precio}" required></td>
                    </tr>
                    <tr>
                        <td>Duración (min):</td>
                        <td><input type="number" name="duracion" value="${pelicula.duracion}" required></td>
                    </tr>
                    <tr>
                        <td>Idioma:</td>
                        <td><input type="text" name="idioma" value="${pelicula.idioma}" required></td>
                    </tr>
                    <tr>
                        <td>Género:</td>
                        <td><input type="text" name="genero" value="${pelicula.genero}" required></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="${pelicula == null ? "Guardar" : "Actualizar"}" class="btn-tabla">
                            <a href="PeliculaControlador?accion=listar" class="btn-tabla">Cancelar</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>

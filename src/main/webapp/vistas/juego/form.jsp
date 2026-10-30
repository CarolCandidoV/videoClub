<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/styles.css"/>
        <title>${juego == null ? "Nuevo" : "Editar"} juego</title>
    </head>
    <body>
        <div class="tabla">
            <h1 class="page-title">${juego == null ? "Nuevo" : "Editar"} juego</h1>
            <hr>
            <form action="JuegoControlador" method="post">
                <c:if test="${juego != null}">
                    <input type="hidden" name="codigo" value="${juego.codigo}">
                </c:if>
                <table>
                    <tr>
                        <td>Título:</td>
                        <td><input type="text" name="titulo" value="${juego.titulo}" required></td>
                    </tr>
                    <tr>
                        <td>Precio:</td>
                        <td><input type="number" step="0.01" name="precio" value="${juego.precio}" required></td>
                    </tr>
                    <tr>
                        <td>Plataforma:</td>
                        <td><input type="text" name="plataforma" value="${juego.plataforma}" required></td>
                    </tr>
                    <tr>
                        <td>Género:</td>
                        <td><input type="text" name="genero" value="${juego.genero}" required></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="${juego == null ? "Guardar" : "Actualizar"}" class="btn-tabla">
                            <a href="JuegoControlador?accion=listar" class="btn-tabla">Cancelar</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>

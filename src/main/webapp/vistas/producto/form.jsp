<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/styles.css"/>
        <title>${producto == null ? "Nuevo" : "Editar"} producto</title>
    </head>
    <body>
        <div class="tabla">
            <h1 class="page-title">${producto == null ? "Nuevo" : "Editar"} producto</h1>
            <hr>
            <form action="ProductoControlador" method="post">
                <c:if test="${producto != null}">
                    <input type="hidden" name="codigo" value="${producto.codigo}">
                </c:if>
                <table>
                    <tr>
                        <td>Título:</td>
                        <td><input type="text" name="titulo" value="${producto.titulo}" required></td>
                    </tr>
                    <tr>
                        <td>Precio:</td>
                        <td><input type="number" step="0.01" name="precio" value="${producto.precio}" required></td>
                    </tr>
                    <tr>
                        <td>Tipo:</td>
                        <td>
                            <select name="tipo" required>
                                <option value="PELICULA" ${producto.tipo == "PELICULA" ? "selected" : ""}>Película</option>
                                <option value="JUEGO" ${producto.tipo == "JUEGO" ? "selected" : ""}>Juego</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="${producto == null ? "Guardar" : "Actualizar"}" class="btn-tabla">
                            <a href="ProductoControlador?accion=listar" class="btn-tabla">Cancelar</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/styles.css"/>
        <title>${alquiler == null ? "Nuevo" : "Editar"} alquiler</title>
    </head>
    <body>
        <div class="tabla">
            <h1 class="page-title">${alquiler == null ? "Nuevo" : "Editar"} alquiler</h1>
            <hr>
            <form action="AlquilerControlador" method="post">
                <c:if test="${alquiler != null}">
                    <input type="hidden" name="codAlquiler" value="${alquiler.codAlquiler}">
                </c:if>
                <table>
                    <tr>
                        <td>Código cliente:</td>
                        <td><input type="number" name="codCliente" value="${alquiler.codCliente}" required></td>
                    </tr>
                    <tr>
                        <td>Código producto:</td>
                        <td><input type="number" name="codProducto" value="${alquiler.codProducto}" required></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="${alquiler == null ? "Guardar" : "Actualizar"}" class="btn-tabla">
                            <a href="AlquilerControlador?accion=listar" class="btn-tabla">Cancelar</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>

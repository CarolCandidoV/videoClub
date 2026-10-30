<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/styles.css"/>
        <title>${cliente == null ? "Nuevo" : "Editar"} cliente</title>
    </head>
    <body>
        <div class="tabla">
            <h1 class="page-title">${cliente == null ? "Nuevo" : "Editar"} cliente</h1>
            <hr>
            <form action="ClienteControlador" method="post">
                <c:if test="${cliente != null}">
                    <input type="hidden" name="codigo" value="${cliente.codigo}">
                </c:if>
                <table>
                    <tr>
                        <td>Documento:</td>
                        <td><input type="text" name="documento" value="${cliente.documento}" required></td>
                    </tr>
                    <tr>
                        <td>Nombre:</td>
                        <td><input type="text" name="nombre" value="${cliente.nombre}" required></td>
                    </tr>
                    <tr>
                        <td>Apellidos:</td>
                        <td><input type="text" name="apellidos" value="${cliente.apellidos}" required></td>
                    </tr>
                    <tr>
                        <td>Teléfono:</td>
                        <td><input type="text" name="telefono" value="${cliente.telefono}"></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="email" name="email" value="${cliente.email}"></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="${cliente == null ? "Guardar" : "Actualizar"}" class="btn-tabla">
                            <a href="ClienteControlador?accion=listar" class="btn-tabla">Cancelar</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>

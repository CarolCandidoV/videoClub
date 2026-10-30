<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/styles.css"/>
        <title>Listado clientes</title>
    </head>
    <body>
        <div class="tabla">
            <h1 class="page-title">Lista de clientes</h1>
            <hr>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn-tabla">Volver al inicio</a>
            <a href="ClienteControlador?accion=nuevo" class="btn-tabla">Nuevo</a>
            <table>
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Documento</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Teléfono</th>
                        <th>Email</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${clientes}" var="item">
                        <tr>
                            <td>${item.codigo}</td>
                            <td>${item.documento}</td>
                            <td>${item.nombre}</td>
                            <td>${item.apellidos}</td>
                            <td>${item.telefono}</td>
                            <td>${item.email}</td>
                            <td>
                                <a href="ClienteControlador?accion=editar&codigo=${item.codigo}" class="btn-tabla">Modificar</a>
                                <a href="ClienteControlador?accion=borrar&codigo=${item.codigo}" class="btn-tabla">Borrar</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${clientes.size() == 0}">
                        <tr>
                            <td colspan="7">No hay registros de clientes.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>

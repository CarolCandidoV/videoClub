<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/styles.css"/>
        <title>Listado alquileres</title>
    </head>
    <body>
        <div class="tabla">
            <h1 class="page-title">Lista de alquileres</h1>
            <hr>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn-tabla">Volver al inicio</a>
            <a href="AlquilerControlador?accion=nuevo" class="btn-tabla">Nuevo</a>
            <a href="AlquilerControlador?accion=pendientes" class="btn-tabla">Pendientes</a>
            <table>
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Cód. Cliente</th>
                        <th>Cód. Producto</th>
                        <th>Fecha alquiler</th>
                        <th>Fecha entrega</th>
                        <th>Alquilado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${alquileres}" var="item">
                        <tr>
                            <td>${item.codAlquiler}</td>
                            <td>${item.codCliente}</td>
                            <td>${item.codProducto}</td>
                            <td>${item.fechaAlquiler}</td>
                            <td>${item.fechaEntrega}</td>
                            <td>${item.alquilado ? "Sí" : "No"}</td>
                            <td>
                                <a href="AlquilerControlador?accion=editar&codAlquiler=${item.codAlquiler}" class="btn-tabla">Modificar</a>
                                <a href="AlquilerControlador?accion=borrar&codAlquiler=${item.codAlquiler}" class="btn-tabla">Borrar</a>
                                <c:if test="${item.alquilado}">
                                    <a href="AlquilerControlador?accion=devolver&codAlquiler=${item.codAlquiler}" class="btn-tabla">Devolver</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${alquileres.size() == 0}">
                        <tr>
                            <td colspan="7">No hay registros de alquileres.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>

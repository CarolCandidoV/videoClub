<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/styles.css"/>
        <title>Listado productos</title>
    </head>
    <body>
        <div class="tabla">
            <h1 class="page-title">Lista de productos</h1>
            <hr>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn-tabla">Volver al inicio</a>
            <a href="ProductoControlador?accion=nuevo" class="btn-tabla">Nuevo</a>
            <table>
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Título</th>
                        <th>Precio</th>
                        <th>Tipo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${productos}" var="item">
                        <tr>
                            <td>${item.codigo}</td>
                            <td>${item.titulo}</td>
                            <td>${item.precio}</td>
                            <td>${item.tipo}</td>
                            <td>
                                <a href="ProductoControlador?accion=editar&codigo=${item.codigo}" class="btn-tabla">Modificar</a>
                                <a href="ProductoControlador?accion=borrar&codigo=${item.codigo}" class="btn-tabla">Borrar</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${productos.size() == 0}">
                        <tr>
                            <td colspan="5">No hay registros de productos.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>

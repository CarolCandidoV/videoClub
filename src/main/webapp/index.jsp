<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>CRUD - Videoclub</title>
        <link rel="stylesheet" href="./css/styles.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <main class="main-content">
            <h1>CRUD - Videoclub</h1>

            <div class="container">
                <h2>Clientes</h2>
                <a href="ClienteControlador?accion=listar" class="btn">Listar clientes</a>
                <a href="ClienteControlador?accion=nuevo" class="btn">Nuevo cliente</a>
            </div>

            <div class="container">
                <h2>Productos</h2>
                <a href="ProductoControlador?accion=listar" class="btn">Listar productos</a>
                <a href="ProductoControlador?accion=nuevo" class="btn">Nuevo producto</a>
            </div>

            <div class="container">
                <h2>Películas</h2>
                <a href="PeliculaControlador?accion=listar" class="btn">Listar películas</a>
                <a href="PeliculaControlador?accion=nuevo" class="btn">Nueva película</a>
            </div>

            <div class="container">
                <h2>Juegos</h2>
                <a href="JuegoControlador?accion=listar" class="btn">Listar juegos</a>
                <a href="JuegoControlador?accion=nuevo" class="btn">Nuevo juego</a>
            </div>

            <div class="container">
                <h2>Alquileres</h2>
                <a href="AlquilerControlador?accion=listar" class="btn">Listar alquileres</a>
                <a href="AlquilerControlador?accion=nuevo" class="btn">Nuevo alquiler</a>
                <a href="AlquilerControlador?accion=pendientes" class="btn">Pendientes</a>
            </div>
        </main>
    </body>
</html>

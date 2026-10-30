# VideoClub

Aplicación web para gestionar un videoclub, hecha en Java con Servlets y JSP. Proyecto del módulo de Programación de 1º de DAW.

Permite gestionar clientes, películas, juegos y alquileres (crear, listar, editar y borrar), ver los alquileres pendientes y registrar devoluciones.

## Tecnologías

- Java 11
- Jakarta EE 10 (Servlets, JSP, JSTL)
- MySQL y JDBC
- Maven
- Tomcat 10

## Estructura

- `database/videoClub.sql`: script para crear la base de datos con datos de prueba.
- `src/main/java/com/videoclub/config`: conexión con la base de datos.
- `src/main/java/com/videoclub/modelo`: clases Cliente, Producto, Pelicula, Juego y Alquiler.
- `src/main/java/com/videoclub/dao`: consultas a la base de datos.
- `src/main/java/com/videoclub/controlador`: servlets.
- `src/main/webapp`: páginas JSP y CSS.

Pelicula y Juego heredan de Producto. En la base de datos se guardan en sus propias tablas, que comparten el código con la tabla `producto`.

## Cómo ejecutarlo

1. Ejecutar `database/videoClub.sql` en MySQL.
2. Cambiar el usuario, la contraseña y el puerto en `ConexionDB.java` (está configurado con el puerto 3307).
3. Abrir el proyecto en NetBeans y ejecutarlo con Tomcat.
4. Entrar en http://localhost:8080/videoClub

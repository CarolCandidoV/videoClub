-- =========================================================
--  Base de datos del proyecto VideoClub
--  Crea la base de datos, las tablas y carga datos de ejemplo
-- =========================================================

CREATE DATABASE IF NOT EXISTS videoClub
  CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE videoClub;

-- ---------- Tablas ----------

CREATE TABLE IF NOT EXISTS cliente (
    codCliente  INT AUTO_INCREMENT PRIMARY KEY,
    documento   VARCHAR(20)  NOT NULL UNIQUE,
    nombre      VARCHAR(50)  NOT NULL,
    apellidos   VARCHAR(100) NOT NULL,
    telefono    VARCHAR(15),
    email       VARCHAR(100)
);

-- Producto es la tabla "padre": cada película o juego es un producto
CREATE TABLE IF NOT EXISTS producto (
    codProducto INT AUTO_INCREMENT PRIMARY KEY,
    titulo      VARCHAR(100)  NOT NULL,
    precio      DECIMAL(6,2)  NOT NULL,
    tipo        VARCHAR(20)   NOT NULL   -- 'PELICULA' o 'JUEGO'
);

CREATE TABLE IF NOT EXISTS pelicula (
    codPelicula INT PRIMARY KEY,
    duracion    INT,
    idioma      VARCHAR(30),
    genero      VARCHAR(50),
    FOREIGN KEY (codPelicula) REFERENCES producto(codProducto) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS juego (
    codJuego    INT PRIMARY KEY,
    plataforma  VARCHAR(50),
    genero      VARCHAR(50),
    FOREIGN KEY (codJuego) REFERENCES producto(codProducto) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS alquiler (
    codAlquiler   INT AUTO_INCREMENT PRIMARY KEY,
    codCliente    INT      NOT NULL,
    codProducto   INT      NOT NULL,
    fechaAlquiler DATETIME NOT NULL,
    fechaEntrega  DATETIME NULL,
    alquilado     TINYINT(1) NOT NULL DEFAULT 1,   -- 1 = pendiente de devolver
    FOREIGN KEY (codCliente)  REFERENCES cliente(codCliente)   ON DELETE CASCADE,
    FOREIGN KEY (codProducto) REFERENCES producto(codProducto) ON DELETE CASCADE
);

-- ---------- Datos de ejemplo ----------

INSERT INTO cliente (documento, nombre, apellidos, telefono, email) VALUES
('12345678A', 'Juan', 'García López', '612345678', 'juan@email.com'),
('87654321B', 'María', 'Martínez Ruiz', '698765432', 'maria@email.com'),
('11122333C', 'Carlos', 'Sánchez Pérez', '611223344', 'carlos@email.com'),
('44455566D', 'Ana', 'López Fernández', '644556677', 'ana@email.com'),
('77788899E', 'Laura', 'Díaz Gómez', '677889900', 'laura@email.com');


INSERT INTO producto (titulo, precio, tipo) VALUES
('El Padrino', 19.99, 'PELICULA'),
('Inception', 14.99, 'PELICULA'),
('Toy Story', 9.99, 'PELICULA'),
('Matrix', 12.99, 'PELICULA'),
('Interestelar', 16.99, 'PELICULA');


INSERT INTO pelicula (codPelicula, duracion, idioma, genero) VALUES
(1, 175, 'Inglés', 'Drama'),
(2, 148, 'Inglés', 'Ciencia Ficción'),
(3, 81, 'Español', 'Animación'),
(4, 136, 'Inglés', 'Acción'),
(5, 169, 'Inglés', 'Ciencia Ficción');


INSERT INTO producto (titulo, precio, tipo) VALUES
('The Legend of Zelda', 59.99, 'JUEGO'),
('FIFA 24', 49.99, 'JUEGO'),
('Minecraft', 29.99, 'JUEGO'),
('God of War', 39.99, 'JUEGO'),
('Mario Kart', 44.99, 'JUEGO');


INSERT INTO juego (codJuego, plataforma, genero) VALUES
(6, 'Nintendo Switch', 'Aventura'),
(7, 'PS5', 'Deportes'),
(8, 'PC', 'Sandbox'),
(9, 'PS5', 'Acción'),
(10, 'Nintendo Switch', 'Carreras');


INSERT INTO alquiler (codCliente, codProducto, fechaAlquiler, fechaEntrega, alquilado) VALUES
(1, 1, '2026-06-01 10:00:00', '2026-06-03 18:00:00', 0),
(1, 6, '2026-06-01 10:00:00', '2026-06-03 18:00:00', 0),
(2, 2, '2026-06-02 12:30:00', NULL, 1),
(3, 3, '2026-06-02 15:00:00', '2026-06-04 20:00:00', 0),
(3, 8, '2026-06-02 15:00:00', NULL, 1),
(4, 4, '2026-06-03 09:00:00', NULL, 1),
(5, 5, '2026-06-03 11:00:00', NULL, 1),
(5, 10, '2026-06-03 11:00:00', NULL, 1),
(2, 7, '2026-06-03 17:00:00', NULL, 1),
(4, 9, '2026-06-04 14:00:00', NULL, 1);

package com.videoclub.controlador;

import com.videoclub.dao.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductoControlador", urlPatterns = {"/ProductoControlador"})
public class ProductoControlador extends HttpServlet {

    private ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                request.setAttribute("productos", productoDAO.listarTodos());
                request.getRequestDispatcher("vistas/producto/listar.jsp").forward(request, response);
                break;

            case "nuevo":
                request.getRequestDispatcher("vistas/producto/form.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("ProductoControlador?accion=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipo = request.getParameter("tipo");
        String titulo = request.getParameter("titulo");
        String precioStr = request.getParameter("precio");

        if ("PELICULA".equals(tipo)) {
            response.sendRedirect("PeliculaControlador?accion=nuevo&titulo=" + titulo + "&precio=" + precioStr);
        } else if ("JUEGO".equals(tipo)) {
            response.sendRedirect("JuegoControlador?accion=nuevo&titulo=" + titulo + "&precio=" + precioStr);
        } else {
            response.sendRedirect("ProductoControlador?accion=listar");
        }
    }
}

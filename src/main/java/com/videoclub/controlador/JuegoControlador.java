package com.videoclub.controlador;

import com.videoclub.dao.JuegoDAO;
import com.videoclub.modelo.Juego;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "JuegoControlador", urlPatterns = {"/JuegoControlador"})
public class JuegoControlador extends HttpServlet {

    private JuegoDAO juegoDAO = new JuegoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                request.setAttribute("juegos", juegoDAO.listarTodos());
                request.getRequestDispatcher("vistas/juego/listar.jsp").forward(request, response);
                break;

            case "nuevo":
                request.getRequestDispatcher("vistas/juego/form.jsp").forward(request, response);
                break;

            case "editar":
                int codigoEditar = Integer.parseInt(request.getParameter("codigo"));
                request.setAttribute("juego", juegoDAO.buscarPorId(codigoEditar));
                request.getRequestDispatcher("vistas/juego/form.jsp").forward(request, response);
                break;

            case "borrar":
                int codigoBorrar = Integer.parseInt(request.getParameter("codigo"));
                juegoDAO.borrar(codigoBorrar);
                response.sendRedirect("JuegoControlador?accion=listar");
                break;

            default:
                response.sendRedirect("JuegoControlador?accion=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigoStr = request.getParameter("codigo");
        String titulo = request.getParameter("titulo");
        String precioStr = request.getParameter("precio");
        String plataforma = request.getParameter("plataforma");
        String genero = request.getParameter("genero");

        double precio = Double.parseDouble(precioStr);

        Juego j = new Juego();
        j.setTitulo(titulo);
        j.setPrecio(precio);
        j.setPlataforma(plataforma);
        j.setGenero(genero);

        if (codigoStr != null && !codigoStr.isEmpty()) {
            j.setCodigo(Integer.parseInt(codigoStr));
            juegoDAO.editar(j);
        } else {
            juegoDAO.insertar(j);
        }

        response.sendRedirect("JuegoControlador?accion=listar");
    }
}

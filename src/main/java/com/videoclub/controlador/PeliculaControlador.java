package com.videoclub.controlador;

import com.videoclub.dao.PeliculaDAO;
import com.videoclub.modelo.Pelicula;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PeliculaControlador", urlPatterns = {"/PeliculaControlador"})
public class PeliculaControlador extends HttpServlet {

    private PeliculaDAO peliculaDAO = new PeliculaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                request.setAttribute("peliculas", peliculaDAO.listarTodos());
                request.getRequestDispatcher("vistas/pelicula/listar.jsp").forward(request, response);
                break;

            case "nuevo":
                request.getRequestDispatcher("vistas/pelicula/form.jsp").forward(request, response);
                break;

            case "editar":
                int codigoEditar = Integer.parseInt(request.getParameter("codigo"));
                request.setAttribute("pelicula", peliculaDAO.buscarPorId(codigoEditar));
                request.getRequestDispatcher("vistas/pelicula/form.jsp").forward(request, response);
                break;

            case "borrar":
                int codigoBorrar = Integer.parseInt(request.getParameter("codigo"));
                peliculaDAO.borrar(codigoBorrar);
                response.sendRedirect("PeliculaControlador?accion=listar");
                break;

            default:
                response.sendRedirect("PeliculaControlador?accion=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigoStr = request.getParameter("codigo");
        String titulo = request.getParameter("titulo");
        String precioStr = request.getParameter("precio");
        String duracionStr = request.getParameter("duracion");
        String idioma = request.getParameter("idioma");
        String genero = request.getParameter("genero");

        double precio = Double.parseDouble(precioStr);
        int duracion = Integer.parseInt(duracionStr);

        Pelicula p = new Pelicula();
        p.setTitulo(titulo);
        p.setPrecio(precio);
        p.setDuracion(duracion);
        p.setIdioma(idioma);
        p.setGenero(genero);

        if (codigoStr != null && !codigoStr.isEmpty()) {
            p.setCodigo(Integer.parseInt(codigoStr));
            peliculaDAO.editar(p);
        } else {
            peliculaDAO.insertar(p);
        }

        response.sendRedirect("PeliculaControlador?accion=listar");
    }
}

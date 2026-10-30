package com.videoclub.controlador;

import com.videoclub.dao.AlquilerDAO;
import com.videoclub.modelo.Alquiler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AlquilerControlador", urlPatterns = {"/AlquilerControlador"})
public class AlquilerControlador extends HttpServlet {

    private AlquilerDAO alquilerDAO = new AlquilerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                request.setAttribute("alquileres", alquilerDAO.listarTodos());
                request.getRequestDispatcher("vistas/alquiler/listar.jsp").forward(request, response);
                break;

            case "pendientes":
                request.setAttribute("alquileres", alquilerDAO.listarPendientes());
                request.getRequestDispatcher("vistas/alquiler/listar.jsp").forward(request, response);
                break;

            case "nuevo":
                request.getRequestDispatcher("vistas/alquiler/form.jsp").forward(request, response);
                break;

            case "editar":
                int codEditar = Integer.parseInt(request.getParameter("codAlquiler"));
                request.setAttribute("alquiler", alquilerDAO.buscarPorId(codEditar));
                request.getRequestDispatcher("vistas/alquiler/form.jsp").forward(request, response);
                break;

            case "borrar":
                int codBorrar = Integer.parseInt(request.getParameter("codAlquiler"));
                alquilerDAO.borrar(codBorrar);
                response.sendRedirect("AlquilerControlador?accion=listar");
                break;

            case "devolver":
                int codDevolver = Integer.parseInt(request.getParameter("codAlquiler"));
                alquilerDAO.devolver(codDevolver);
                response.sendRedirect("AlquilerControlador?accion=listar");
                break;

            default:
                response.sendRedirect("AlquilerControlador?accion=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codAlquilerStr = request.getParameter("codAlquiler");
        String codClienteStr = request.getParameter("codCliente");
        String codProductoStr = request.getParameter("codProducto");

        int codCliente = Integer.parseInt(codClienteStr);
        int codProducto = Integer.parseInt(codProductoStr);

        Alquiler a = new Alquiler();
        a.setCodCliente(codCliente);
        a.setCodProducto(codProducto);

        if (codAlquilerStr != null && !codAlquilerStr.isEmpty()) {
            a.setCodAlquiler(Integer.parseInt(codAlquilerStr));
            alquilerDAO.editar(a);
        } else {
            alquilerDAO.insertar(a);
        }

        response.sendRedirect("AlquilerControlador?accion=listar");
    }
}

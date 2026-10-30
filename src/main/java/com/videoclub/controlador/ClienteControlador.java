package com.videoclub.controlador;

import com.videoclub.dao.ClienteDAO;
import com.videoclub.modelo.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClienteControlador", urlPatterns = {"/ClienteControlador"})
public class ClienteControlador extends HttpServlet {

    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                request.setAttribute("clientes", clienteDAO.listarTodos());
                request.getRequestDispatcher("vistas/cliente/listar.jsp").forward(request, response);
                break;

            case "nuevo":
                request.getRequestDispatcher("vistas/cliente/form.jsp").forward(request, response);
                break;

            case "editar":
                int codigoEditar = Integer.parseInt(request.getParameter("codigo"));
                request.setAttribute("cliente", clienteDAO.buscarPorId(codigoEditar));
                request.getRequestDispatcher("vistas/cliente/form.jsp").forward(request, response);
                break;

            case "borrar":
                int codigoBorrar = Integer.parseInt(request.getParameter("codigo"));
                clienteDAO.borrar(codigoBorrar);
                response.sendRedirect("ClienteControlador?accion=listar");
                break;

            default:
                response.sendRedirect("ClienteControlador?accion=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigoStr = request.getParameter("codigo");
        String documento = request.getParameter("documento");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        Cliente c = new Cliente();
        c.setDocumento(documento);
        c.setNombre(nombre);
        c.setApellidos(apellidos);
        c.setTelefono(telefono);
        c.setEmail(email);

        if (codigoStr != null && !codigoStr.isEmpty()) {
            c.setCodigo(Integer.parseInt(codigoStr));
            clienteDAO.editar(c);
        } else {
            clienteDAO.insertar(c);
        }

        response.sendRedirect("ClienteControlador?accion=listar");
    }
}

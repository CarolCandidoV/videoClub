package com.videoclub.modelo;

import com.videoclub.dao.AlquilerDAO;
import com.videoclub.dao.ClienteDAO;
import com.videoclub.dao.JuegoDAO;
import com.videoclub.dao.PeliculaDAO;
import com.videoclub.dao.ProductoDAO;
import java.util.ArrayList;

public class VideoClub {

    private static VideoClub instancia;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProductoDAO productoDAO = new ProductoDAO();
    private PeliculaDAO peliculaDAO = new PeliculaDAO();
    private JuegoDAO juegoDAO = new JuegoDAO();
    private AlquilerDAO alquilerDAO = new AlquilerDAO();

    private VideoClub() {}

    public static VideoClub getInstancia() {
        if (instancia == null) {
            instancia = new VideoClub();
        }
        return instancia;
    }

    public void agregarCliente(Cliente c) {
        clienteDAO.insertar(c);
    }

    public void agregarProducto(Producto p) {
        if (p instanceof Pelicula) {
            peliculaDAO.insertar((Pelicula) p);
        } else if (p instanceof Juego) {
            juegoDAO.insertar((Juego) p);
        }
    }

    public Cliente buscarCliente(int codigo) {
        return clienteDAO.buscarPorId(codigo);
    }

    public Producto buscarProducto(int codigo) {
        return productoDAO.buscarPorId(codigo);
    }

    public boolean alquilarProducto(int codCliente, int codProducto) {
        Cliente c = clienteDAO.buscarPorId(codCliente);
        Producto p = productoDAO.buscarPorId(codProducto);

        if (c != null && p != null) {
            Alquiler a = new Alquiler();
            a.setCodCliente(codCliente);
            a.setCodProducto(codProducto);
            alquilerDAO.insertar(a);
            return true;
        }

        return false;
    }

    public void mostrarAlquileres() {
        for (Alquiler a : alquilerDAO.listarTodos()) {
            System.out.println(a);
        }
    }

    public ArrayList<Cliente> getClientes() {
        return clienteDAO.listarTodos();
    }

    public ArrayList<Producto> getProductos() {
        return productoDAO.listarTodos();
    }

    public ArrayList<Alquiler> getAlquileres() {
        return alquilerDAO.listarTodos();
    }
}

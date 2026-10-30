package com.videoclub.dao;

import com.videoclub.config.ConexionDB;
import com.videoclub.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductoDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Producto> listarTodos() {
        ArrayList<Producto> lista = new ArrayList<>();

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT * FROM producto";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int cod = rs.getInt("codProducto");
                String tip = rs.getString("tipo");

                if ("PELICULA".equals(tip)) {
                    PeliculaDAO pelDAO = new PeliculaDAO();
                    lista.add(pelDAO.buscarPorId(cod));
                } else if ("JUEGO".equals(tip)) {
                    JuegoDAO jueDAO = new JuegoDAO();
                    lista.add(jueDAO.buscarPorId(cod));
                }
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {

        } finally {
            try {
                if (cn != null) cn.close();
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException sqle2) {
                sqle2.printStackTrace();
            }
        }
        return lista;
    }

    public Producto buscarPorId(int codigo) {
        Producto prod = null;

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT * FROM producto WHERE codProducto = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);

            rs = ps.executeQuery();
            if (rs.next()) {
                String tip = rs.getString("tipo");
                if ("PELICULA".equals(tip)) {
                    PeliculaDAO pelDAO = new PeliculaDAO();
                    prod = pelDAO.buscarPorId(codigo);
                } else if ("JUEGO".equals(tip)) {
                    JuegoDAO jueDAO = new JuegoDAO();
                    prod = jueDAO.buscarPorId(codigo);
                }
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {

        } finally {
            try {
                if (cn != null) cn.close();
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return prod;
    }

    int insertarBase(Producto producto) {
        int generado = 0;

        try {
            cn = ConexionDB.getConnection();

            String sql = "INSERT INTO producto (titulo, precio, tipo) VALUES (?, ?, ?)";
            ps = cn.prepareStatement(sql);

            ps.setString(1, producto.getTitulo());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getTipo());

            generado = ps.executeUpdate();

            if (generado > 0) {
                String sqlId = "SELECT MAX(codProducto) FROM producto";
                Statement st = cn.createStatement();
                ResultSet rsId = st.executeQuery(sqlId);
                if (rsId.next()) {
                    int id = rsId.getInt(1);
                    producto.setCodigo(id);
                    generado = id;
                }
                rsId.close();
                st.close();
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {

        } finally {
            try {
                if (cn != null) cn.close();
                if (ps != null) ps.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return generado;
    }

    void actualizarBase(Producto producto) {
        try {
            cn = ConexionDB.getConnection();

            String sql = "UPDATE producto SET titulo = ?, precio = ?, tipo = ? WHERE codProducto = ?";
            ps = cn.prepareStatement(sql);

            ps.setString(1, producto.getTitulo());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getTipo());
            ps.setInt(4, producto.getCodigo());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {

        } finally {
            try {
                if (cn != null) cn.close();
                if (ps != null) ps.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    void eliminarBase(int codigo) {
        try {
            cn = ConexionDB.getConnection();

            String sql = "DELETE FROM producto WHERE codProducto = ?";
            ps = cn.prepareStatement(sql);

            ps.setInt(1, codigo);

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {

        } finally {
            try {
                if (cn != null) cn.close();
                if (ps != null) ps.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}

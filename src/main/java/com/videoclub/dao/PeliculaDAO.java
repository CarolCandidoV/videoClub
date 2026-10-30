package com.videoclub.dao;

import com.videoclub.config.ConexionDB;
import com.videoclub.modelo.Pelicula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PeliculaDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Pelicula> listarTodos() {
        ArrayList<Pelicula> lista = new ArrayList<>();

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT p.*, pel.* FROM producto p "
                       + "INNER JOIN pelicula pel ON p.codProducto = pel.codPelicula";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pelicula pel = new Pelicula();
                pel.setCodigo(rs.getInt("codProducto"));
                pel.setTitulo(rs.getString("titulo"));
                pel.setPrecio(rs.getDouble("precio"));
                pel.setDuracion(rs.getInt("duracion"));
                pel.setIdioma(rs.getString("idioma"));
                pel.setGenero(rs.getString("genero"));
                lista.add(pel);
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

    public Pelicula buscarPorId(int codigo) {
        Pelicula pel = null;

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT p.*, pel.* FROM producto p "
                       + "INNER JOIN pelicula pel ON p.codProducto = pel.codPelicula "
                       + "WHERE p.codProducto = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);

            rs = ps.executeQuery();
            if (rs.next()) {
                pel = new Pelicula();
                pel.setCodigo(rs.getInt("codProducto"));
                pel.setTitulo(rs.getString("titulo"));
                pel.setPrecio(rs.getDouble("precio"));
                pel.setDuracion(rs.getInt("duracion"));
                pel.setIdioma(rs.getString("idioma"));
                pel.setGenero(rs.getString("genero"));
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
        return pel;
    }

    public int insertar(Pelicula pelicula) {
        int result = 0;

        try {
            cn = ConexionDB.getConnection();

            ProductoDAO prodDAO = new ProductoDAO();
            int idGenerado = prodDAO.insertarBase(pelicula);
            if (idGenerado == 0) return 0;

            String sql = "INSERT INTO pelicula (codPelicula, duracion, idioma, genero) VALUES (?, ?, ?, ?)";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, pelicula.getCodigo());
            ps.setInt(2, pelicula.getDuracion());
            ps.setString(3, pelicula.getIdioma());
            ps.setString(4, pelicula.getGenero());

            result = ps.executeUpdate();

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

        return result;
    }

    public int editar(Pelicula pelicula) {
        int result = 0;

        try {
            cn = ConexionDB.getConnection();

            ProductoDAO prodDAO = new ProductoDAO();
            prodDAO.actualizarBase(pelicula);

            String sql = "UPDATE pelicula SET duracion = ?, idioma = ?, genero = ? WHERE codPelicula = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, pelicula.getDuracion());
            ps.setString(2, pelicula.getIdioma());
            ps.setString(3, pelicula.getGenero());
            ps.setInt(4, pelicula.getCodigo());

            result = ps.executeUpdate();

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

        return result;
    }

    public int borrar(int codigo) {
        int result = 0;

        try {
            cn = ConexionDB.getConnection();

            String sql = "DELETE FROM pelicula WHERE codPelicula = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            result = ps.executeUpdate();

            if (result > 0) {
                ProductoDAO prodDAO = new ProductoDAO();
                prodDAO.eliminarBase(codigo);
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

        return result;
    }
}

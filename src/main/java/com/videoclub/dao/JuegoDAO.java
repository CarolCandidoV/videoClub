package com.videoclub.dao;

import com.videoclub.config.ConexionDB;
import com.videoclub.modelo.Juego;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JuegoDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Juego> listarTodos() {
        ArrayList<Juego> lista = new ArrayList<>();

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT p.*, j.* FROM producto p "
                       + "INNER JOIN juego j ON p.codProducto = j.codJuego";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Juego j = new Juego();
                j.setCodigo(rs.getInt("codProducto"));
                j.setTitulo(rs.getString("titulo"));
                j.setPrecio(rs.getDouble("precio"));
                j.setPlataforma(rs.getString("plataforma"));
                j.setGenero(rs.getString("genero"));
                lista.add(j);
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

    public Juego buscarPorId(int codigo) {
        Juego j = null;

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT p.*, j.* FROM producto p "
                       + "INNER JOIN juego j ON p.codProducto = j.codJuego "
                       + "WHERE p.codProducto = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);

            rs = ps.executeQuery();
            if (rs.next()) {
                j = new Juego();
                j.setCodigo(rs.getInt("codProducto"));
                j.setTitulo(rs.getString("titulo"));
                j.setPrecio(rs.getDouble("precio"));
                j.setPlataforma(rs.getString("plataforma"));
                j.setGenero(rs.getString("genero"));
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
        return j;
    }

    public int insertar(Juego juego) {
        int result = 0;

        try {
            cn = ConexionDB.getConnection();

            ProductoDAO prodDAO = new ProductoDAO();
            int idGenerado = prodDAO.insertarBase(juego);
            if (idGenerado == 0) return 0;

            String sql = "INSERT INTO juego (codJuego, plataforma, genero) VALUES (?, ?, ?)";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, juego.getCodigo());
            ps.setString(2, juego.getPlataforma());
            ps.setString(3, juego.getGenero());

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

    public int editar(Juego juego) {
        int result = 0;

        try {
            cn = ConexionDB.getConnection();

            ProductoDAO prodDAO = new ProductoDAO();
            prodDAO.actualizarBase(juego);

            String sql = "UPDATE juego SET plataforma = ?, genero = ? WHERE codJuego = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, juego.getPlataforma());
            ps.setString(2, juego.getGenero());
            ps.setInt(3, juego.getCodigo());

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

            String sql = "DELETE FROM juego WHERE codJuego = ?";
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

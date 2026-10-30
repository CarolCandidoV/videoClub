package com.videoclub.dao;

import com.videoclub.config.ConexionDB;
import com.videoclub.modelo.Alquiler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AlquilerDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Alquiler> listarTodos() {
        ArrayList<Alquiler> lista = new ArrayList<>();

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT * FROM alquiler";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapear(rs));
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

    public ArrayList<Alquiler> listarPendientes() {
        ArrayList<Alquiler> lista = new ArrayList<>();

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT * FROM alquiler WHERE alquilado = 1";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapear(rs));
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

    public ArrayList<Alquiler> listarPorCliente(int codCliente) {
        ArrayList<Alquiler> lista = new ArrayList<>();

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT * FROM alquiler WHERE codCliente = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapear(rs));
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

    public Alquiler buscarPorId(int codAlquiler) {
        Alquiler a = null;

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT * FROM alquiler WHERE codAlquiler = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codAlquiler);

            rs = ps.executeQuery();
            if (rs.next()) {
                a = mapear(rs);
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
        return a;
    }

    public int insertar(Alquiler alquiler) {
        int result = 0;

        try {
            cn = ConexionDB.getConnection();

            String sql = "INSERT INTO alquiler (codCliente, codProducto, fechaAlquiler, alquilado) "
                       + "VALUES (?, ?, ?, 1)";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, alquiler.getCodCliente());
            ps.setInt(2, alquiler.getCodProducto());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

            result = ps.executeUpdate();
            alquiler.setAlquilado(true);

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

    public int editar(Alquiler alquiler) {
        int result = 0;

        try {
            cn = ConexionDB.getConnection();

            String sql = "UPDATE alquiler SET codCliente = ?, codProducto = ?, "
                       + "fechaAlquiler = ?, fechaEntrega = ?, alquilado = ? WHERE codAlquiler = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, alquiler.getCodCliente());
            ps.setInt(2, alquiler.getCodProducto());
            ps.setTimestamp(3, alquiler.getFechaAlquiler() != null ? Timestamp.valueOf(alquiler.getFechaAlquiler()) : null);
            ps.setTimestamp(4, alquiler.getFechaEntrega() != null ? Timestamp.valueOf(alquiler.getFechaEntrega()) : null);
            ps.setBoolean(5, alquiler.isAlquilado());
            ps.setInt(6, alquiler.getCodAlquiler());

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

    public int borrar(int codAlquiler) {
        int result = 0;

        try {
            cn = ConexionDB.getConnection();

            String sql = "DELETE FROM alquiler WHERE codAlquiler = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codAlquiler);

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

    public int devolver(int codAlquiler) {
        int result = 0;

        try {
            cn = ConexionDB.getConnection();

            String sql = "UPDATE alquiler SET fechaEntrega = ?, alquilado = 0 WHERE codAlquiler = ?";
            ps = cn.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(2, codAlquiler);

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

    private Alquiler mapear(ResultSet rs) throws SQLException {
        Alquiler a = new Alquiler();
        a.setCodAlquiler(rs.getInt("codAlquiler"));
        a.setCodCliente(rs.getInt("codCliente"));
        a.setCodProducto(rs.getInt("codProducto"));

        Timestamp tsAlquiler = rs.getTimestamp("fechaAlquiler");
        if (tsAlquiler != null) a.setFechaAlquiler(tsAlquiler.toLocalDateTime());

        Timestamp tsEntrega = rs.getTimestamp("fechaEntrega");
        if (tsEntrega != null) a.setFechaEntrega(tsEntrega.toLocalDateTime());

        a.setAlquilado(rs.getBoolean("alquilado"));
        return a;
    }
}

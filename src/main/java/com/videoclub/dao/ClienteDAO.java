package com.videoclub.dao;

import com.videoclub.config.ConexionDB;
import com.videoclub.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Cliente> listarTodos() {
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT * FROM cliente";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCodigo(rs.getInt("codCliente"));
                c.setDocumento(rs.getString("documento"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));
                lista.add(c);
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

    public Cliente buscarPorId(int codigo) {
        Cliente c = null;

        try {
            cn = ConexionDB.getConnection();
            String sql = "SELECT * FROM cliente WHERE codCliente = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);

            rs = ps.executeQuery();
            if (rs.next()) {
                c = new Cliente();
                c.setCodigo(rs.getInt("codCliente"));
                c.setDocumento(rs.getString("documento"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));
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
        return c;
    }

    public int insertar(Cliente cliente) {
        int result = 0;

        try {
            cn = ConexionDB.getConnection();

            String sql = "INSERT INTO cliente (documento, nombre, apellidos, telefono, email) VALUES (?, ?, ?, ?, ?)";
            ps = cn.prepareStatement(sql);

            ps.setString(1, cliente.getDocumento());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getEmail());

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

    public int editar(Cliente cliente) {
        int result = 0;

        try {
            cn = ConexionDB.getConnection();

            String sql = "UPDATE cliente SET documento = ?, nombre = ?, apellidos = ?, telefono = ?, email = ? WHERE codCliente = ?";
            ps = cn.prepareStatement(sql);

            ps.setString(1, cliente.getDocumento());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getEmail());
            ps.setInt(6, cliente.getCodigo());

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

            String sql = "DELETE FROM cliente WHERE codCliente = ?";
            ps = cn.prepareStatement(sql);

            ps.setInt(1, codigo);

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
}

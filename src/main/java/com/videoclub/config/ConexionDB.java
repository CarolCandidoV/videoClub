package com.videoclub.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {
    
    public static final String username = "root";
    public static final String password = "TU_PASSWORD";
    public static final String database = "videoClub";
    public static final String url = "jdbc:mysql://localhost:3307/" + database;

    public static Connection getConnection() {
        Connection cn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, username, password);
            
            System.out.println("Conexion con la BBDD " + database + " establecida correctamente.");
        } catch (Exception e) {
            System.out.println("Conexion con la BBDD " + database + " no pudo establecerse.");
            System.out.println("ERROR: " + e.getMessage());
        }
        return cn;
    }

    
}

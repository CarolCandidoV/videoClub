package com.videoclub.config;

import java.sql.Connection;

public class Test {
    
    public static void main(String[] args) {
    
        Connection cn = null;
        
        cn = ConexionDB.getConnection();
        
        if(cn == null) {
            System.out.println("No hay conexion con la BBDD");
        } else {
            System.out.println("Hay conexion con la BBDD!!!");
        }
    
    }
    
}

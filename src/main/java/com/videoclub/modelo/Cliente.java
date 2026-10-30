package com.videoclub.modelo;

public class Cliente {

    private int codigo;
    private String documento;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;

    public Cliente() {}

    public Cliente(int codigo, String documento, String nombre, String apellidos, String telefono, String email) {
        this.codigo = codigo;
        this.documento = documento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " " + apellidos;
    }
}

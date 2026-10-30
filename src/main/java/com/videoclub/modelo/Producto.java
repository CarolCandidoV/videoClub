package com.videoclub.modelo;

public abstract class Producto {

    protected int codigo;
    protected String titulo;
    protected double precio;
    protected String tipo;

    public Producto() {}

    public Producto(int codigo, String titulo, double precio, String tipo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.precio = precio;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return titulo + " - " + precio;
    }
}

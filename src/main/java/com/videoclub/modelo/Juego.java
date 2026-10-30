package com.videoclub.modelo;

public class Juego extends Producto {

    private String plataforma;
    private String genero;

    public Juego() {
        this.tipo = "JUEGO";
    }

    public Juego(int codigo, String titulo, double precio,
                 String plataforma, String genero) {
        super(codigo, titulo, precio, "JUEGO");
        this.plataforma = plataforma;
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Juego -> " + super.toString();
    }
}

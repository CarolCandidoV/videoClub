package com.videoclub.modelo;

public class Pelicula extends Producto {

    private int duracion;
    private String idioma;
    private String genero;

    public Pelicula() {
        this.tipo = "PELICULA";
    }

    public Pelicula(int codigo, String titulo, double precio,
                    int duracion, String idioma, String genero) {
        super(codigo, titulo, precio, "PELICULA");
        this.duracion = duracion;
        this.idioma = idioma;
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Pelicula -> " + super.toString();
    }
}

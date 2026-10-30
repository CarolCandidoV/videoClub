package com.videoclub.modelo;

import java.time.LocalDateTime;

public class Alquiler {

    private int codAlquiler;
    private int codCliente;
    private int codProducto;
    private LocalDateTime fechaAlquiler;
    private LocalDateTime fechaEntrega;
    private boolean alquilado;

    public Alquiler() {}

    public Alquiler(int codAlquiler, int codCliente, int codProducto,
                    LocalDateTime fechaAlquiler, LocalDateTime fechaEntrega, boolean alquilado) {
        this.codAlquiler = codAlquiler;
        this.codCliente = codCliente;
        this.codProducto = codProducto;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaEntrega = fechaEntrega;
        this.alquilado = alquilado;
    }

    public int getCodAlquiler() {
        return codAlquiler;
    }

    public void setCodAlquiler(int codAlquiler) {
        this.codAlquiler = codAlquiler;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public LocalDateTime getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(LocalDateTime fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public boolean isAlquilado() {
        return alquilado;
    }

    public void setAlquilado(boolean alquilado) {
        this.alquilado = alquilado;
    }

    @Override
    public String toString() {
        return "Alquiler #" + codAlquiler + " - Cliente " + codCliente + " alquilo producto " + codProducto;
    }
}

package org.example.model;

public class ReporteDTO {
    public Integer numero;
    public String fecha;
    public String nombreCliente;
    public String descripcion;
    public Double precio;
    public String estado;

    public ReporteDTO(Integer numero, String fecha, String nombreCliente, String descripcion, Double precio, String estado) {
        this.numero = numero;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }
}
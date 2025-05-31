package org.angelo.webappcookie.models;

public class Producto {
    //Declaramos variables para crear el objeto productos
    private  Long id;
    private String nombre;
    private String tipo;
    private Double precio;
    //implementamos el constructor vacio
    public Producto() {
    }

    public Producto(Long id, String nombre, String tipo, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}

package org.angelo.webappcookie.models;

public class Articulo {
    private Long idArticulo;
    private Categoria categoria;
    private String codigo;
    private String nombre;
    private int stock;
    private String descripcion;
    private String imagen;
    private int condicion;

    //Implementamos un constructor vacio
    public Articulo(){

    }
    //Implementamos un constructor que inicialice todos los parametros
    public Articulo(Long idArticulo, String tipo, String codigo, String nombre, int stock, String descripcion, String imagen, int condicion) {
        Categoria categoria = new Categoria();
        categoria.setNombre(tipo);
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.condicion = condicion;
    }
    //Implementamos los metodos get y set

    public Long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCondicion() {
        return condicion;
    }

    public void setCondicion(int condicion) {
        this.condicion = condicion;
    }
}

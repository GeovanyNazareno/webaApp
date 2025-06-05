package org.angelo.webappcookie.repository;

import org.angelo.webappcookie.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CategoriaRepositoryJdbcImplemt implements Repository<Categoria> {

    //Creamos una variable donde vamos a guarda la conexisón
    private Connection conn;

    public CategoriaRepositoryJdbcImplemt(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from categorias")) {
            while (rs.next()) {
                Categoria c = getCategoria(rs);
                categorias.add(c);
            }
        }
        return categorias;
    }


    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stmt = conn.prepareStatement(
                "select * from categorias where idcategoria=?")) {
            stmt.setLong(1, id); //1 ,2, 3 ,4
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    categoria=getCategoria(rs);
                }

            }

        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        // Validar que los campos esenciales no sean nulos o vacíos
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'nombre' es obligatorio y no puede estar vacío.");
        }
        if (categoria.getDescripcion() == null || categoria.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'descripcion' es obligatorio y no puede estar vacío.");
        }

        //Declaramos una variable de tipo String de nombre sql
        String sql;
        if (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0) {
            sql = "update categorias set nombre=?, descripcion=? Where idcategoria=?";
        } else {
            sql = "insert into categorias(nombre, descripcion, condicion) VALUES(?,?,1)";
        }
        // Implemento un condicional para saber si el idcategoria es distinto y mayor
        if (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0) {
            sql = "update categorias set nombre=?, descripcion=? Where idcategoria=?";
        } else {
            sql = "insert into categorias(nombre, descripcion, condicion) VALUES(?,?,1)";
        }

        try(PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            if (categoria.getIdCategoria()!=null && categoria.getIdCategoria()>0) {
                stmt.setLong(3, categoria.getIdCategoria());
            }

            //stmt.setInt(3, categoria.getCondicion());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setNombre(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setCondicion(rs.getInt("condicion"));
        c.setIdCategoria(rs.getLong("idCategoria"));
        return c;
    }
}
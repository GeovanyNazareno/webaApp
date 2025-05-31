package org.angelo.webappcookie.repository;

import org.angelo.webappcookie.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CategoriaRepositoryJdbcImplement implements Repository<Categoria> {

    //Obtenemos la conecion BBDD
    private Connection conn;

    public CategoriaRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from categorias")) {
            while (rs.next()) {
                Categoria cate = getCategorias(rs);
                categorias.add(cate);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porID(long id) throws SQLException {
        Categoria categorias=null;
        try (PreparedStatement stmt = conn.prepareStatement(
                "select * from categorias where idCategoria=?")) {
            stmt.setLong(1,id);
            try (ResultSet rs = stmt.executeQuery()) {
                categorias=getCategorias(rs);
            }
        }
        return categorias;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

        //Declaramos una variable de tipo String de nombre sql
        String sql;
        //Implementamos un condicional para saber si el idCategoria es distinto y mayo
        if (categoria.getIdCategoria() != null &&categoria.getIdCategoria()>0){
            sql = "update categorias set nombre=?, descripcion=? where idCategoria=?";
        }else {
            sql = "insert into categorias(nombre, descripcion, condicion) VALUES(?,?,1)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
           stmt.setString(1,categoria.getNombre());
           stmt.setString(2,categoria.getDescripcion());
           //stmt.setInt(3,categoria.getCondicion());
           stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(long id) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(
                "delete from categorias where idCategoria=?")){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }


    @Override
    public void activar(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update categorias set condicion = 1 where idCategoria = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }

    }

    @Override
    public void desactivar(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update categorias set condicion = 0 where idCategoria = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }

    }

    private static Categoria getCategorias(ResultSet rs) throws SQLException {
        Categoria cate = new Categoria();
        cate.setIdCategoria(rs.getLong("idCategoria"));
        cate.setNombre(rs.getString("nombre"));
        cate.setDescripcion(rs.getString("descripcion"));
        cate.setCondicion(rs.getInt("condicion"));
        return cate;
    }


}

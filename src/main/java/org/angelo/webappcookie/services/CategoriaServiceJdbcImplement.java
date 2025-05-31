package org.angelo.webappcookie.services;

import org.angelo.webappcookie.models.Categoria;
import org.angelo.webappcookie.repository.CategoriaRepositoryJdbcImplement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriaServiceJdbcImplement implements CategoriaService {

    private final CategoriaRepositoryJdbcImplement repositoryJdbc;
    private CategoriaRepositoryJdbcImplement repositoryJdbcImplement;
    //Implementamos el constructor para octener la conexion y los metodos del CRUD
    public CategoriaServiceJdbcImplement(Connection conn) {
        this.repositoryJdbc= new CategoriaRepositoryJdbcImplement(conn) {
            @Override
            public void activar(long id) throws SQLException {

            }

            @Override
            public void desactivar(long id) throws SQLException {

            }
        };
    }

    @Override
    public List<Categoria> listar() {
        try{
            return repositoryJdbc.listar();
        }catch (SQLException throwables){
            throw  new ServiceJdbcExeption(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porId(Long id) {
        try {
            return Optional.ofNullable((repositoryJdbc.porID(id)));
        } catch (SQLException throwables) {
            throw  new ServiceJdbcExeption(throwables.getMessage(), throwables.getCause());
        }
    }
    @Override
    public void guardar(Categoria categoria) {
        try {
            repositoryJdbc.guardar(categoria); // solo guarda, sin actualizar
        } catch (SQLException throwables) {
            throw new ServiceJdbcExeption(throwables.getMessage(), throwables.getCause());
        }
    }
}

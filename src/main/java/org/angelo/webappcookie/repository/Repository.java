package org.angelo.webappcookie.repository;

import org.angelo.webappcookie.models.Categoria;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

    //Implementamos los metodos para el crud a la base de datos
    List<T> listar() throws SQLException;
    //Implementamos el metodo por id
    T porID(long id) throws SQLException;

    Categoria porId(Long id) throws SQLException;

    //Implementamos un metodo guardar
    void guardar(T t ) throws SQLException;
    //Implementamos el metodo eliminar
    void eliminar(long id) throws SQLException;
    //Implementamos el metodo activar
    void  activar(long id) throws SQLException;
    //Implementamos el metodo desativar
    void desactivar(long id) throws SQLException;

}

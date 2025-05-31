package org.angelo.webappcookie.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.angelo.webappcookie.models.Categoria;
import org.angelo.webappcookie.services.CategoriaService;
import org.angelo.webappcookie.services.CategoriaServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;


@WebServlet("/categoria/form")
public class CategoriaFormControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Traemos la conexion a la base de datos
        Connection conn = (Connection)  req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
        Long id;
        //Vañadimos que el campo ingresado sea un número
        try {
            //En la variable id guardamos la que estamos mapeando por e metodo get idCatrgoria
            id=Long.parseLong(req.getParameter("idCategoria"));

        }catch (NumberFormatException e){
            id=0L;
        }
        //Creamos un objeto Categoria vacio
        Categoria categoria = new Categoria();
        //Verificamos si el id>o
        if (id>0){
            //Creamos una variablede tipo optional para optener la categoria por id
            Optional<Categoria> optionalCategoria = service.porId(id);
            //Si la variable optional esta presnete obtenemos todos los valores
              if (optionalCategoria.isPresent()){
                  categoria = optionalCategoria.get();
              }
        }
        //seteamos los atributos en el alcance de request
        req.setAttribute("categorias", categoria);
        getServletContext().getRequestDispatcher("/formularioCategoria.jsp").forward(req, resp);
    }

    //sobre escribimos el metodo doPost

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection)  req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
                String nombre = req.getParameter("nombre");
                String descripcion = req.getParameter("descripcion");
                //Obtenemos el idCategoria
        Long idCategoria;
        try{
            idCategoria = Long.parseLong(req.getParameter("idCategoria"));
        }catch(NumberFormatException e){
            idCategoria = 0L;
        }
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        service.guardar(categoria);
        //Redirecionamos al listado para que nos ejecute el metodo doPost
        resp.sendRedirect(req.getContextPath()+"/categorias");
    }
}

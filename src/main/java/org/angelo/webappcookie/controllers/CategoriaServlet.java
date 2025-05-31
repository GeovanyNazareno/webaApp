package org.angelo.webappcookie.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.angelo.webappcookie.models.Categoria;
import org.angelo.webappcookie.services.CategoriaService;
import org.angelo.webappcookie.services.CategoriaServiceJdbcImplement;
import org.angelo.webappcookie.services.LoginService;
import org.angelo.webappcookie.services.LoginServiceSesionImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/categorias")
public class CategoriaServlet extends HttpServlet {

    //Creamos la noexion a la BBDD

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Creamos la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        //Creamos el nuevo objeto
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
        //Creamos la lista
        List<Categoria> categorias = service.listar();
        //Obtenemos los parametros de la sesion
        LoginService auth = new LoginServiceSesionImplement();
        Optional<String> userName = auth.getUserName(req);

        //Setiamos los atributos de categorias y el username
        req.setAttribute("categoria", categorias);
        req.setAttribute("userName", userName);

        //re direcionamos ala listarCategoria.jsp
        getServletContext().getRequestDispatcher("/categoriaListar.jsp").forward(req, resp);
    }
}

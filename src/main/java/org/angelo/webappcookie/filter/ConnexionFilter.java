package org.angelo.webappcookie.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.angelo.webappcookie.services.ServiceJdbcExeption;
import org.angelo.webappcookie.uti.ConnexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

//Anotacion
@WebFilter("/*")
public class ConnexionFilter implements Filter {
    // La clase filter siempre va a estar orientada a las peticiones request


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //Obtenemos la conexion a la base de datos
        try (Connection conn = ConnexionBaseDatos.getConnection()) {
            //Implemento un if para saber si esta activado el automivit
            if (conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try {
                //Ssetiamos los parametros de la conexion
                request.setAttribute("conn", conn);
                //Filtramos la conexion
                chain.doFilter(request, response);
                conn.commit();

            }catch (SQLException | ServiceJdbcExeption e){
                //La consulta no se ejecuta y se vuelve a su estado anterior de la consulta
                conn.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

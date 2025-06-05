package org.angelo.webappcookie.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.angelo.webappcookie.services.LoginService;
import org.angelo.webappcookie.services.LoginServiceSesionImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

//path o anotación
@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //implementamos el objeto de tipo sesion
        LoginService auth=new LoginServiceSesionImplement();
        //creamos una variable opcional
        //para obtener el nombre del usuario
        Optional<String> usernameOptional = auth.getUserName(req);


        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                //Creo la plantilla html
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Hola usuario " + usernameOptional.get() +"</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola "+usernameOptional.get()+" ya iniciaste sesión anteriormente!</h1>");
                out.println("<p><a href='"+req.getContextPath()+"/index.html'>Volver al inicio</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        }else{
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            // Inicia una sesión en caso de éxito
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            // Redirige al inicio
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            // Error de autenticación
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=1");
        }
    }
}
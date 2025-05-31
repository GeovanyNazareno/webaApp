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
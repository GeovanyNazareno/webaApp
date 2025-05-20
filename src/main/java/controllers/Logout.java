package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.LoginService;
import services.LoginServiceSesionImplement;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Creamos e implementamos el Objeto sesion
        LoginService auth = new LoginServiceSesionImplement();
        Optional<String> usernameOptional = auth.getUserName();
        //una condicional para saber si la sesion esta presente
        if (usernameOptional.isPresent()) {
            HttpSession session = req.getSession();
            //Destruimos la sesion
            session.invalidate();
        }
        //Regresamos al index
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }
}

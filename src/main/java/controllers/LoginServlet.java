package controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.LoginService;
import services.LoginServiceImplement;
import services.LoginServiceSesionImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

//path o anotación
@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {
    final static  String USERNAME = "admin";
    final static  String PASSWORD = "12345";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /*  Cookie[] cookies = req.getCookies() !=null ? req.getCookies() : new Cookie[0];
        //Busco el arreglo cookie si existe la cookie
        //solicitada y la convierto en String
        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                //Convertimos a string el valor
                .map(Cookie::getValue)
                .findAny();*/
        //Creamos el nuevo objeto de la cookie
        //LoginService aut=new LoginServiceImplement();
        //Implementamos el nuevo objeto de tipo session
        LoginService auth = new LoginServiceSesionImplement();
        Optional<String> userNameOptional=auth.getUserName(req);
        if (userNameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                //Creo la plantilla html
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=utf-8>");
                out.println("<title>Hola "+userNameOptional.get() +"</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola"+userNameOptional.get()+" ya iniciaste secion anteriormente!</h1>");
                out.println("<p><a href='"+req.getContextPath()+"/index.html'>Volver al inicio</a></p>");
                out.println("</body>");
                out.println("</html>");
            }

        }else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            //Creamos la cookie
           /* Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                //Creo la plantilla html
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=utf-8>");
                out.println("<title>Bienvenido a la app</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Bienvenido a mi APP</h1>");
                out.println("</body>");
                out.println("</html>");
            }*/

            //Manejo de Seciones
            //Creamos la Secion
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath()+"/login.html");
        }else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no tienes acceso");
        }
    }
}

package org.angelo.webappcookie.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.angelo.webappcookie.services.LoginService;
import org.angelo.webappcookie.services.LoginServiceSesionImplement;

import java.io.IOException;
import java.util.Optional;

@WebFilter("/*") // Aplica el filtro a todas las rutas. Cambiado de rutas específicas a "/*"
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Obtenemos la ruta que intenta acceder el usuario
        String uri = httpRequest.getRequestURI();

        // Evitar filtrar estas rutas públicas
        if (uri.endsWith("login.jsp") || uri.endsWith("login") || uri.endsWith("logout")
                || uri.startsWith("/CSS") || uri.startsWith("/assets")) {
            chain.doFilter(request, response); // Continúa con las rutas públicas
            return;
        }

        // Verificar la autenticación usando el servicio de sesión implementado
        LoginService loginService = new LoginServiceSesionImplement();
        Optional<String> username = loginService.getUserName(httpRequest);

        if (username.isPresent()) {
            // Si la sesión es válida, continúa con la solicitud
            chain.doFilter(request, response);
        } else {
            // Si no hay sesión, redirige al login.jsp
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }
}
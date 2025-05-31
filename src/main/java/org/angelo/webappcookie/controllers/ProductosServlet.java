package org.angelo.webappcookie.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.angelo.webappcookie.models.Producto;
import org.angelo.webappcookie.services.ProductoService;
import org.angelo.webappcookie.services.ProductoServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//Anotaciones
@WebServlet("/productos")
public class ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Producto> productos = service.listar();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        //Creo la plantilla html
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=utf-8>");
        out.println("<title>Listar Producto</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/producto.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Listar Producto</h1>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID PRODUCTO</th>");
        out.println("<th>NOMBRE </th>");
        out.println("<th>TIPO </th>");
        out.println("<th>PRECIO </th>");
        out.println("</tr>");
        productos.forEach(p -> {
            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + p.getNombre() + "</td>");
            out.println("<td>" + p.getTipo() + "</td>");
            out.printf("<td>$%.2f</td>%n", p.getPrecio());
            out.println("</tr>");
        });
        out.println("</table>");
        out.println("<p><a href='"+req.getContextPath()+"/index.html'>Volver al inicio</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
}

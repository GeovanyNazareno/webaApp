<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 29/5/2025
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*, org.angelo.webappcookie.models.Categoria"%>
<%
    Categoria categorias = (Categoria) request.getAttribute("categorias");
%>

<html>
<head>
    <title>Formulario Categoria</title>
</head>
<body>
<h1>Formulario Categoria</h1>
<div>
    <form action="<%=request.getContextPath()%>/categoria/form" method="post">
        <div>
            <label for="nombre">Ingrese el nombre de categoria</label>
            <div>
                <input type="hidden" name="id" value="<%=categorias.getIdCategoria()%>">
                <input type="text" id="nombre" name="nombre" value="<%=categorias.getNombre() != null ? categorias.getNombre():""%>">
            </div>
        </div>

        <div>
            <label for="descripcion">Ingrese la descripción</label>
            <div>
                <input type="text" id="descripcion" name="descripcion" value="<%=categorias.getDescripcion() !=null ? categorias.getDescripcion():""%>">
            </div>
        </div>



        <div>
            <input type="submit" value="<%=(categorias.getIdCategoria()!= null && categorias.getIdCategoria()>0) ? "Editar" : "Crear"%>" >

        </div>
    </form>
</div>

</body>
</html>

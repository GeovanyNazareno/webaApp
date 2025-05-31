<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 28/5/2025
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, org.angelo.webappcookie.models.Categoria" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categoria");
    Optional<String> username = (Optional<String>) request.getAttribute("userName");
%>
<html>
<head>
    <title>Listado Categoria</title>
    <link rel="stylesheet" href="CSS/producto.css">
</head>
<body>
<%
    if(username.isPresent()){
%>
<div style="color:blue;"> Hola <%= username.get() %>, bienvenido a la aplicación</div>
<div><p><a href="${pageContext.request.contextPath}/categoria/form">Ingrese el producto</a></p></div>
<%
    }
%>

<h1>Listado Categoria</h1>
<%
    if (username.isPresent()){%>
<div style="color: blue">Hola, <%=username.get()%> bienvenido</div>
<%}%>

<table>
    <thead>
    <th>ID CATEDORIA</th>
    <th>NOMBRE</th>
    <th>DESCRIPCION</th>
    <th>CONDICION</th>
    <th>ACCION</th>
    </thead>
    <%
        for (Categoria cate : categorias){%>
    <tbody>
    <td><%=cate.getIdCategoria()%></td>
    <td><%=cate.getNombre()%></td>
    <td><%=cate.getDescripcion()%></td>
    <td><%=cate.getCondicion()%></td>
    <td>
        <a href="">Editar</a>
        <a href="">Eliminar</a>
    </td>
    </tbody>
    <%}%>

</table>


</body>
</html>

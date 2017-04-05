<%-- 
    Document   : login
    Created on : 04-abr-2017, 17:41:12
    Author     : montse
--%>


<%@page errorPage="pagina_errors_usuaris.jsp" contentType="text/html" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usu" scope="request" class="model.Usuari" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entrada al sistema</title>
    </head>
    <body>
        <h1> Entrada al sistema </h1>
        <jsp:setProperty name="usu" property="*" />
        <% if (request.getParameter("id") == null) { %>
        <form method="post">
            email o id<input type="text" name="id" size="20" required/><br/>
            password    <input type="password" name="password" size="20"  required/> <br/>
            <input type="submit" value="Entrar"/>
        </form>
        <!-- s'hauria de controlar amb javascript que el formulari no pot ser buit-->
        <% } else {%>
        
        <jsp:forward page="ControladorUsuaris?accio=login"/>   

        <%  }
        %>
        Si és la primera vegada, <a href="registrar.jsp">registra't </a>
    </body>
</html>


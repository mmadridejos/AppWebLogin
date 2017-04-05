<%-- 
    Document   : benvinguda
    Created on : 04-abr-2017, 19:16:11
    Author     : montse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Benvinguda</title>
    </head>
    <body>
        <% String nom=(String)request.getAttribute("nom");
        %>
        <h1>Hola <%=nom%></h1>
    </body>
</html>

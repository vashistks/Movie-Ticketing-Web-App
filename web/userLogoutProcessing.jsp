<%-- 
    Document   : userLogoutProcessing
    Created on : Aug 23, 2013, 8:27:31 PM
    Author     : Vasanth
--%>

<%@page import="com.sun.crypto.provider.RSACipher"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <%
            session.invalidate();
            response.sendRedirect("index.jsp");
        %>
    </body>
</html>

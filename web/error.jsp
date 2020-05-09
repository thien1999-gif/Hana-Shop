<%-- 
    Document   : error
    Created on : Mar 14, 2020, 10:51:50 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error page</h1>
        <font color="red">
        ${requestScope.ERROR}
        </font>
        </br>
        <a href="index.jsp">Back to Home</a>
    </body>
</html>

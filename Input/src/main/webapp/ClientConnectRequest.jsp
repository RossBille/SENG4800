<%-- 
    Document   : ClientConnectRequest
    Created on : Aug 12, 2013, 7:09:03 PM
    Author     : rowanburgess
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client Connect Request</title>
    </head>
    <body>
        <div align="center">
    <h1>Connection Parameters</h1>
    <p>Please enter your login credentials below.<br>

    <form method="POST" action="connect">
    	<input type="text" name="mac"/> <br>
    	<input type="text" name="type"/> <br>
    	<input type="submit"/>
    </form>
    </div>
    </body>
</html>

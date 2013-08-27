<%-- 
    Document   : SampleClient3
    Created on : Aug 21, 2013, 1:19:30 PM
    Author     : rowanburgess
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Browser Client</title>
        
    </head>
    <body>
        <div align="center">
        <h1>SENG4800 Browser Client Example 1.0</h1>
        <p>This is the entry point for a user with a web browser into the system.<br><br>
            Contact the Connection Broker to join a game below!<br>
       </p>

        <form method="POST" action="connect">
            <input type="hidden" name="type" value="connect"/>
            <input type="submit"/>
        </form>
            
        </div>
    </body>
</html>

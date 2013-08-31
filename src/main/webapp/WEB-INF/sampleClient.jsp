<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            if ("WebSocket" in window) //if web socket is supported in this browser
            {
                alert("WebSockets are supported. (Yay!)");
                var url = ${result.message}
                var websocket = new WebSocket(url);
                alert(url);
            }

            else
            {
                alert("WebSockets are not supported, reverting to 'POST' for data trasmission");
            }
        </script>
        
        <title>SENG4800 - Sample Client</title>
    </head>
    <body>

        <div align="center">
            <h1>SENG4800 - Sample Client</h1>
            <c:choose>
                <c:when test="${result.error == 'false'}"> <!--   connection request successful -->
                    <h3>Connection Status: successful</h3>
                    <scr
                    </c:when>
                    <c:otherwise> <!--   connection request failed -->
                        <h3>Connection Status: failed</h3>

                    </c:otherwise>
                </c:choose>

                <form method="get" action="connect">
                    <input type="submit"/>
                </form>
        </div>
    </body>
</html>

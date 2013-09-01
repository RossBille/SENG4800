<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SENG4800 - Sample Client</title>
    </head>
    <body>
        <div align="center">
            <h1>SENG4800 - Sample Client</h1>
            <c:choose>
                <c:when test="${result.error == 'false'}"> <!--   connection request successful -->
                    <script type="text/javascript">
                        if ("WebSocket" in window) //if web socket is supported in this browser
                        {
                            //alert("WebSockets are supported. (Yay!)");
                            var websocket = new WebSocket("${result.message}");
                            websocket.onmessage = onMessage;

                            function onMessage(evt) //used to post registration token to server
                            {
                                var code = "${result.code}";
                                sendInstruction(code);
                            }

                            function sendInstruction(instruction)
                            {
                                websocket.send(instruction);
                            }
                        }

                        else //web sockets are not supported
                        {
                            alert("WebSockets are not supported, reverting to 'POST' for data trasmission");
                        }
                    </script>
                    <h3>Connection Status: successful</h3>

                    <c:forEach items="${instructions}" var="i">
                        <button onclick="sendInstruction(&quot ${i} &quot)">${i}</button>                       
                    </c:forEach>

                </c:when>
                <c:otherwise> <!--   connection request failed -->
                    <h3>Connection Status: failed</h3>
                    <p>
                        Unable to join the current game session. <br>
                        Message From Server: ${result.message}
                    </p>

                    <form method="get" action="connect"> <!-- hit servlet with another request -->
                        <input type="submit"/>
                    </form>

                </c:otherwise>
            </c:choose>

        </div>
    </body>
</html>

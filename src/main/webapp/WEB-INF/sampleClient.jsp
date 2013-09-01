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
                            alert("WebSockets are supported. (Yay!)");
                            var url = "${result.message}";
                            alert(url);
                            var websocket = new WebSocket(url);
                            websocket.onmessage = onMessage;
                            websocket.onopen = onOpen;

                            function onOpen(evt)
                            {
                                alert("Connection Successful!");
                            }

                            function onMessage(evt) //used to post registration token to server
                            {
                                var token = new Object();
                                token.message = "clientHello";
                                token.code = "${result.code}";
                                token.error = ${result.error};
                                websocket.send(stringit(token, "au.edu.newcastle.seng48002013.results.Result"));
                            }


                            function sendInstruction(instruction)
                            {

                            }

                            function stringit(obj, cls)
                            {
                                cls = "{\"@class\":\"" + cls + "\",";
                                var js = JSON.stringify(obj);
                                js = js.substr(1);
                                js = cls + js;
                                return js;
                            }
                        }

                        else //web sockets are not supported
                        {
                            alert("WebSockets are not supported, reverting to 'POST' for data trasmission");
                        }
                    </script>
                    <h3>Connection Status: successful</h3>

                    <c:forEach items="${instructions}" var="i">
                        <h4>${i}</h4>                         
                    </c:forEach>

                </c:when>
                <c:otherwise> <!--   connection request failed -->
                    <h3>Connection Status: failed</h3>
                    <p>
                        Unable to join the current game session, please try again
                        later.
                    </p>

                    <form method="get" action="connect"> <!-- hit servlet with another request -->
                        <input type="submit"/>
                    </form>

                </c:otherwise>
            </c:choose>

        </div>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
                                //alert(instruction);
                                websocket.send(instruction);
                            }
                        }

                        else //web sockets are not supported
                        {
                            alert("WebSockets are not supported, reverting to 'POST' for data trasmission");
                            //var socketSupport = false;
                        }
                    </script>

                    <h3>Connection Status: Success</h3>

                    <c:forEach items="${instructions}" var="temp">

                        <c:set var="myVar" value="${temp.value}" />
                        <c:set var="search" value="\"" />
                        <c:set var="replace" value="&quot" />
                        <c:set var="escapedString" value="${fn:replace(myVar, search, replace)}"/>
                        <button onclick="sendInstruction('${escapedString}');">${temp.key}</button>
                        
                    </c:forEach>
					<script type="text/javascript">
						document.onkeydown = function() {
    						switch (window.event.keyCode) {
        						case 37:
            						//alert('left');
									sendInstruction('{"@class":&quotau.edu.newcastle.seng48002013.instructions.phone.TouchScreen",&quotx1":10.0,&quotx2":0.0,&quoty1":0.0,&quoty2":0.0,&quotos":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",&quotphoneId":&quot3f9013a2397906107aa1e33797b8",&quotid":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36,3f9013a2397906107aa1e33797b8,1380361765192"}')
            						break;
        						case 38:
            						alert('up');
            						break;
        						case 39:
            						//alert('right');
									sendInstruction('{"@class":&quotau.edu.newcastle.seng48002013.instructions.phone.TouchScreen",&quotx1":0.0,&quotx2":10.0,&quoty1":0.0,&quoty2":0.0,&quotos":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",&quotphoneId":&quot3f9013a2397906107aa1e33797b8",&quotid":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36,3f9013a2397906107aa1e33797b8,1380361765216"}');
            						break;
        						case 40:
        						    alert('down');
       						     break;
 						   }
						};
					</script>
                </c:when>

                <c:otherwise> <!--   connection request failed -->
                    <h3>Connection Status: Fail</h3>
                    <p>
                        Unable to join the current game session. <br>
                        Server Message: ${result.message}
                    </p>

                    <form method="get" action="connect"> <!-- hit servlet with another request -->
                        <input type="submit"/>
                    </form>

                </c:otherwise>
            </c:choose>

        </div>
    </body>
</html>


						
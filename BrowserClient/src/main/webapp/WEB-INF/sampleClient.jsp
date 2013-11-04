<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head><script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SENG4800 - Sample Client</title>
        <link rel="stylesheet" href="css/styles.css"/>
    </head>
    <body>
        <div class="container">
            <h1>Sample Input Client</h1>

            <c:choose>
                <c:when test="${result.error == 'false'}"> <!--   connection request successful -->
                    <h3>Connection Status: <span class="success">Success</span></h3>

                    <div class="buttons">
                        <div class="button-row">
                            <div class="button-column"></div>
                            <div class="button-column">
                                <button id="up"><i class="icon-caret-up"></i></button>
                            </div>
                            <div class="button-column"></div>
                        </div>
                        <div class="button-row">
                            <div class="button-column">
                                <button id="left"><i class="icon-caret-left"></i></button>
                            </div>
                            <div class="button-column coloured"></div>
                            <div class="button-column">
                                <button id="right"><i class="icon-caret-right"></i></button>
                            </div>
                        </div>
                        <div class="button-row">
                            <div class="button-column"></div>
                            <div class="button-column">
                                <button id="down"><i class="icon-caret-down"></i></button>
                            </div>
                            <div class="button-column"></div>
                        </div>

                    </div>

                    <script type="text/javascript">
                        //websocket connection and message handler
                        if ("WebSocket" in window) {//if web socket is supported in this browser
                            var websocket = new WebSocket("${result.message}");
                            websocket.onmessage = onMessage;
                            //used to post registration token to server
                            function onMessage(evt) {
                                var code = "${result.code}";
                                sendInstruction(code);
                            }

                            function sendInstruction(instruction) {
                                //alert(instruction);
                                console.log(instruction);
                                websocket.send(instruction);
                            }
                        } else {//web sockets are not supported
                            alert("WebSockets are not supported, reverting to 'POST' for data trasmission");
                            //var socketSupport = false;
                        }
                    </script>
                    <script type="text/javascript">
                        //button related movement
                        var timeout;
                        var interval = 1;
                        var $up = $('#up'), $down = $('#down'), $left = $('#left'), $right = $('#right');

                        $left.mousedown(function() {
                            timeout = setInterval(function() {
                                sendInstruction('{"@class":&quotau.edu.newcastle.seng48002013.instructions.phone.TouchScreen",&quotx1":10.0,&quotx2":0.0,&quoty1":0.0,&quoty2":0.0,&quotos":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",&quotphoneId":&quot3f9013a2397906107aa1e33797b8",&quotid":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36,3f9013a2397906107aa1e33797b8,1380361765192"}');
                            }, interval);
                        });

                        $right.mousedown(function() {
                            timeout = setInterval(function() {
                                sendInstruction('{"@class":&quotau.edu.newcastle.seng48002013.instructions.phone.TouchScreen",&quotx1":0.0,&quotx2":10.0,&quoty1":0.0,&quoty2":0.0,&quotos":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",&quotphoneId":&quot3f9013a2397906107aa1e33797b8",&quotid":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36,3f9013a2397906107aa1e33797b8,1380361765216"}');
                            }, interval);
                        });

                        $down.mousedown(function() {
                            timeout = setInterval(function() {
                                sendInstruction('{"@class":&quotau.edu.newcastle.seng48002013.instructions.phone.TouchScreen",&quotx1":0.0,&quotx2":0.0,&quoty1":10.0,&quoty2":0.0,&quotos":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",&quotphoneId":&quot3f9013a2397906107aa1e33797b8",&quotid":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36,3f9013a2397906107aa1e33797b8,1380361765192"}');
                            }, interval);
                        });

                        $up.mousedown(function() {
                            timeout = setInterval(function() {
                                sendInstruction('{"@class":&quotau.edu.newcastle.seng48002013.instructions.phone.TouchScreen",&quotx1":0.0,&quotx2":0.0,&quoty1":0.0,&quoty2":10.0,&quotos":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",&quotphoneId":&quot3f9013a2397906107aa1e33797b8",&quotid":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36,3f9013a2397906107aa1e33797b8,1380361765192"}');
                            }, interval);
                        });

                        $(document).mouseup(function() {
                            clearInterval(timeout);
                            return false;
                        });

                    </script>
                    <script type="text/javascript">
                        //keystroke related movement
                        document.onkeydown = function() {
                            switch (window.event.keyCode) {
                                case 37:
                                    //alert('left');
                                    sendInstruction('{"@class":&quotau.edu.newcastle.seng48002013.instructions.phone.TouchScreen",&quotx1":10.0,&quotx2":0.0,&quoty1":0.0,&quoty2":0.0,&quotos":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",&quotphoneId":&quot3f9013a2397906107aa1e33797b8",&quotid":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36,3f9013a2397906107aa1e33797b8,1380361765192"}');
                                    break;
                                case 38:
                                    //aleart('up');
                                    sendInstruction('{"@class":&quotau.edu.newcastle.seng48002013.instructions.phone.TouchScreen",&quotx1":0.0,&quotx2":0.0,&quoty1":0.0,&quoty2":10.0,&quotos":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",&quotphoneId":&quot3f9013a2397906107aa1e33797b8",&quotid":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36,3f9013a2397906107aa1e33797b8,1380361765192"}');
                                    break;
                                case 39:
                                    //alert('right');
                                    sendInstruction('{"@class":&quotau.edu.newcastle.seng48002013.instructions.phone.TouchScreen",&quotx1":0.0,&quotx2":10.0,&quoty1":0.0,&quoty2":0.0,&quotos":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",&quotphoneId":&quot3f9013a2397906107aa1e33797b8",&quotid":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36,3f9013a2397906107aa1e33797b8,1380361765216"}');
                                    break;
                                case 40:
                                    //aleart('down');
                                    sendInstruction('{"@class":&quotau.edu.newcastle.seng48002013.instructions.phone.TouchScreen",&quotx1":0.0,&quotx2":0.0,&quoty1":10.0,&quoty2":0.0,&quotos":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",&quotphoneId":&quot3f9013a2397906107aa1e33797b8",&quotid":&quotMozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36,3f9013a2397906107aa1e33797b8,1380361765192"}');
                                    break;
                            }
                        };
                    </script>
                </c:when>

                <c:otherwise> <!--   connection request failed -->
                    <h3>Connection Status: <span class="fail">Fail</span></h3>
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



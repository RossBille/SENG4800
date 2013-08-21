<%-- 
    Document   : playing
    Created on : Aug 21, 2013, 5:36:33 PM
    Author     : rowanburgess
--%>

<%@page import="au.edu.newcastle.seng48002013.results.Result"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Browser Client</title>     
    </head>
    <body>
        <div align="center">
            <h1>Game Arena</h1>
            <p>You have successfully joined the game!<br><br>
                Use the buttons below to send instructions!<br>
            </p>
            
            <button onclick="left();" >Left</button>
            <button onclick="right();" >Right</button>
        </div>
        <script type="text/javascript" >
            
            var token = '<%= session.getAttribute("token")%>';
            var url = '<%= session.getAttribute("url")%>';
            var websocket = new WebSocket(url);
            websocket.onmessage = onMessage;
            websocket.onopen = onOpen;

        function onOpen(evt) 
        {
            alert("Connection Successful!");
            var json = "$" + token;
            websocket.send(json);
        }

        function onMessage(evt) {
            alert("Mesage From Server: " + evt.data);
        }
        function right()
            {
                //build the json
                var object = new Object();
                object.phoneId = token;
                object.os = "android";
                object.x1 = 1;
                object.x2 = 0;
                object.y1 = 0;
                object.y2 = 0;

                //send the json
                websocket.send(stringit(object, "au.edu.newcastle.seng48002013.instructions.phone.TouchScreen"));
            }
            function left()
            {
                //build the json
                var object = new Object();
                object.phoneId = token;
                object.os = "android";
                object.x1 = 0;
                object.x2 = 1;
                object.y1 = 0;
                object.y2 = 0;

                //send the json
                websocket.send(stringit(object, "au.edu.newcastle.seng48002013.instructions.phone.TouchScreen"));
            }

            // End test functions

            //write json object with class attribute
            function stringit(obj, cls)
            {
                cls = "{\"@class\":\"" + cls + "\",";
                var js = JSON.stringify(obj);
                js = js.substr(1);
                js = cls + js;
                return js;
            }
        </script>
    </body>



</html>

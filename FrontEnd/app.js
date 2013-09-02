var WebSocketServer = require('ws').Server
var wss = new WebSocketServer({port: 8080});

wss.on ('connection', function(socket) {
    console.log("Opened");
    file = require('./objects.json');
    socket.send(JSON.stringify(file));

    var x = 0; var y = 400;
    setInterval(function() { 
        for (var i = 0; i < file.length; i++) {
            file[i].dx = x;
        }
        socket.send(JSON.stringify(file));
        x += 10;
        if (x >= 800) {
            x =  0;
        }
    }, 1);

    socket.on('message', function(message) {
        console.log("received %s", message);
        socket.send(message);
    });

    socket.on('close', function() {
        console.log("Closed");
    });
});

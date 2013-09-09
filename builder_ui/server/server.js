var fs = require('fs');
var pd = require('pretty-data').pd;

var WebSocketServer = require('ws').Server;
var wss = new WebSocketServer({port: 8088});

wss.on ('connection', function(socket) {
    console.log("Opened");
    file = require('./objects.json');
    socket.send(JSON.stringify(file));

    socket.on('message', function(message) {
        console.log("received %s", message);
        var out = pd.xml(message);
        fs.writeFile("out.xml", out, function(err) {
            if(err) {
                console.log(err);
            }
            else {
                console.log("Saved file");
            }
        });
    });

    socket.on('close', function() {
        console.log("Closed");
    });
});

var fs = require('fs');
var pd = require('pretty-data').pd;

var WebSocketServer = require('ws').Server;
var wss = new WebSocketServer({port: 8088});

wss.on('connection', function(socket) {
    console.log("Connection opened");

    var objects_file = require('./objects.json');
    socket.send(JSON.stringify(objects_file));

    socket.on('message', function(message) {
        console.log("received %s", message);
        var out = JSON.parse(message);
        var xml = pd.xml(out.data);

        fs.writeFile(out.file_name, xml, function(err) {
            if(err) {
                console.log(err);
            }
            else {
                console.log("File saved to disk");
            }
        });
    });

    socket.on('close', function() {
        console.log("Connection closed");
    });
});

var fs = require('fs');
var pd = require('pretty-data').pd;

var WebSocketServer = require('ws').Server;
var wss = new WebSocketServer({port: 8088});

wss.on('connection', function(socket) {
    console.log("Connection opened");

    var objects_file = require('./objects.json');
    var backgrounds_file = require('./backgrounds.json');

    var data = {
        objects: objects_file,
        backgrounds: backgrounds_file
    }

    console.log('data to send:');
    console.log(data);

    socket.send(JSON.stringify(data));

    socket.on('message', function(message) {
        console.log("received %s", message);
        var out = JSON.parse(message);
        var xml_pretty = pd.xml(out.data);

        var pattern = /<([\/]*)([a-zA-Z_0-9]+)([^>]*)>/g;

        var xml = xml_pretty.replace(pattern, function(full, before, tag, after) {
            return "<" + before + tag.toUpperCase() + after + ">";
        });

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

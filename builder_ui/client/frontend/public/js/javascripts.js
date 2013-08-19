var ws = null;

var start = function() {
    if (ws !== null){
        ws.close();
    }
    //our global
    ws = new WebSocket("ws://localhost:8080");
    ws.onmessage = function(msg) {
        console.log("Message receieved:" + msg.data);
        var instruction = JSON.parse(msg.data);
        move('.circle')
            .translate(instruction['x'], instruction['y'])
            .end();
    }
    ws.onopen = function() {
        console.log("Opened");
    };
    ws.onclose = function() {
        console.log("Closed");
    };
    return "Started";
};

var left = function() {
    var json = {
        x: 0, 
        y: 200 
    }
    ws.send(JSON.stringify(json));
};

var right = function() {
    var json = {
        x: 200, 
        y: 200
    }
    ws.send(JSON.stringify(json));
};



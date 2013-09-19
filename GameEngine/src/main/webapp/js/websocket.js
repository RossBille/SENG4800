var app = app || {};
var action = app.action || {};

app.websocket = (function() {
    var Constructor;

    Constructor = function() {
        this.ws = new WebSocket("ws://localhost:8080/GameEngine/output");
        this.ws.onopen = onOpen;
        this.ws.onclose = onClose;
        this.ws.onmessage = onMessage;
    }

    Constructor.prototype.close = function() {
        if (this.ws !== null) {
            this.ws.close();
        }
    }

    /* Our web socket function */

    function onMessage(msg) {
        var objects = [];
        var instruction = JSON.parse(msg.data);
        //console.log(msg.data);

        while (instruction.length > 0) {
            var o = instruction.pop();
            objects.push(o);
        }
        /* our callback */
        action(objects);
    }

    function onOpen() {
        console.log("Opened");
    }

    function onClose() {
        console.log("Closed");
    }

    return Constructor;

})();

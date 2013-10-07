var app = app || {};
var action = app.action || {};

app.Websocket = (function() {
    var Constructor;

    Constructor = function(url) {
        this.ws = new WebSocket(url);
        this.ws.onopen = onOpen;
        this.ws.onclose = onClose;
        this.ws.onmessage = onMessage;
    }

    Constructor.prototype.open = function() {
        if (this.ws !== null) {
            return;
        }
        try {
            this.ws.open();
        }
        catch (e) {
            console.log("Could not open web socket");
            return new Error("Could not open web socket");
        }
    }

    Constructor.prototype.close = function() {
        if (this.ws !== null) {
            this.ws.close();
        }
    }
    /* Our web socket function */

    function onMessage(msg) {
        var instruction = JSON.parse(msg.data);
        /* our callback */
        action(instruction);
    }

    function onOpen() {
        console.log("Opened");
    }

    function onClose() {
        console.log("Closed");
    }

    return Constructor;

})();

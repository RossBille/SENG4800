var app = app || {};

app.Websocket = (function() {
    var Constructor;
    var helper = new app.Helper;

    Constructor = function(url) {
        this.url = url;
        this.ws = new WebSocket(url);
        this.ws.onopen = onOpen;
        this.ws.onclose = onClose;
        this.ws.onmessage = onMessage;
    }

    Constructor.prototype.open = function() {
        if (this.ws.readyState !== 3) {
            return;
        }
        else {
            this.ws = new WebSocket(this.url);
        }
    }

    Constructor.prototype.close = function() {
        if (this.ws.readyState !== 3) {
            this.ws.close();
        }
        else {
            return new Error("Websocket is closed");
        }
    }

    function onMessage(msg) {
        var instruction = JSON.parse(msg.data);
        /* our callback */
        helper.draw(instruction);
    }

    function onOpen() {
        console.log("Opened");
    }

    function onClose() {
        console.log("Closed");
    }

    return Constructor;

})();

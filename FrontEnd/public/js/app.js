var app = app || {};

app.main = (function() {
    /* Our websocket */
    var webSocket = new app.websocket;
    /* Our objects */
    var factory = new app.objectFactory;

    window.setTimeout(function() {
        webSocket.close();
    },2000);

})();

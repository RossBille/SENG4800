var app = app || {};

app.main = (function() {
    /* Our initialisation will go here */
    $.get('http://localhost/objects', function(response) {
        console.log(response);
        app.list = JSON.parse(response);
        /* Once loaded load the websocket and begin */
    });

    /* Our websocket */
    var webSocket = new app.websocket;
    //window.setTimeout(function() { webSocket.close(); },2000);

})();

var app = app || {};

app.main = (function() {
    /* Our initialisation will go here */
    $.getJSON('http://localhost:8080/GameEngine/setup?callback=?', function(response) {
        console.log(response);
        var json = response;
        console.log(json.imageUrls);

        /* Once loaded load the websocket and begin */

    });

    /* Our websocket */
    var webSocket = new app.websocket;
    window.setTimeout(function() { webSocket.close(); },10000);

})();

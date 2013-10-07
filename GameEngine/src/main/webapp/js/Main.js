var app = app || {};
//var sprites = app.sprites || [];

app.Main = (function() {
    /* Our initialisation will go here */
    $.getJSON('http://localhost:8080/GameEngine/setup?callback=?', function(response) {
        var json = response;
    });

    /* Our websocket */
    var webSocket = new app.Websocket('ws://localhost:8080/GameEngine/output');
    window.setTimeout(function() { webSocket.close(); mocha.run(); },1000);
})();

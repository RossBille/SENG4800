'use strict';

/* Services/Factories */

App.factory('ListService', function ($timeout) {
    var service = {};
    var json = {};

    /* Our evented service call, this doesn't block on IO */
    service.getObjects = function (callback) {
        var ws = new WebSocket("ws://localhost:8088");
        ws.onmessage = function (msg) {
            $timeout(function () {
                json = JSON.parse(msg.data);
                //send out objects back to parent
                callback(json.Objects);
                ws.close();
            });
        };
    };

    return service;
});

App.factory('SaveXML', function($timeout) {
    var ws = new WebSocket("ws://localhost:8088");
    ws.send(outFile);
});

App.factory('GameService', function () {
    return {
        game: {
            setup: {},
            levels: []
        }
    }
});

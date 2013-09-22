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
                console.log("JSON");
                console.log(json);

                //send out objects back to parent
                callback(json);
                ws.close();
            });
        };
    };

    return service;
});

/* Save file using stub server */
App.factory('SaveXML', function($timeout) {
    return {
        write: function(outFile) {
            var ws = new WebSocket("ws://localhost:8088");

            ws.onopen = function() {
                console.log("Opened WS connection");
                ws.send(JSON.stringify(outFile));
                ws.close();
            };
        }
    };
});

App.factory('GameService', function () {
    return {
        game: {
            setup: {
                game_name: 'My Game',
                canvas_size: {
                    width: 1920,
                    height: 1080
                },
                starting_level: 0,
                players: {
                    min: 1,
                    max: 4
                },
                sprites: {
                    sprite: []
                },
                backgrounds: {
                    background: []
                }
            },
            levels: {
                level: []
            }
        }
    }
});

App.factory('CanvasService', function () {
    return {
        canvas: {
            width: '',
            height: '',
            multiplier: 0
        }
    }
});

App.factory('ConfigCompletionService', function () {
    return {
        config_completed: {
            status: false
        }
    }
});

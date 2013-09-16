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

/* @author b1nd 
 * Save file using stub server 
 * TEMPORARY
 */
App.factory('SaveXML', function($timeout) {
    return {
        write: function(outFile) {
            var ws = new WebSocket("ws://localhost:8088");
            ws.onopen = function() {
                console.log("Opened WS");
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
                game_name: 'My game',
                canvas_size: {
                    width: 1920,
                    height: 1080
                },
                starting_level: 1,
                players: {
                    min: 1,
                    max: 4
                },
                sprites: {
                    sprite: []
                },
                backgrounds: {
                    background: [
                        {
                            background_id: 0,
                            background_name: "Grey Background",
                            image: "/img/game_background.jpg",
                            speed: 1,
                            position_type: "tiled"
                        }
                    ]
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

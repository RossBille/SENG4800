'use strict';

/* Controllers */

function LevelsController($scope, $http, ListService, GameService) {
    $scope.game = GameService.game;

    $scope.scene_objects = [];

    $scope.clicked = function () {
        console.log('scene item clicked');
    };

    $scope.objectClicked = function () {
        console.log('object clicked');
    };

    $scope.saveLevel = function () {
        console.log('now saving level');

        var level = {
            level_id: 0,
            level_name: "Level One",
            background_id: 0,
            objects: {
                object: []
            },
            events: {
                event: []
            }
        };

        level.objects.object.push($scope.scene_objects);
        $scope.game.levels.push(level);

        var game_levels = {
            levels: {
                level: []
            }
        };
        game_levels.levels.level = $scope.game.levels;
        var form_data_xml = json2xml(game_levels);

        $http({
            method: 'POST',
            url: '/LevelsServlet',
            data: form_data_xml
        });
    };

    ListService.getObjects(function (objects) {
        $scope.list = objects;
    });

    console.log('game at end of levels controller:');
    console.log($scope.game);
}

function ConfigController($scope, $http, $location, GameService, ListService) {
    $scope.game = GameService.game;
    $scope.game_config = {
        game_name: "My Game",
        starting_level: 1,
        canvas_width: 1920,
        canvas_height: 1080,
        min_players: 1,
        max_players: 4
    };

    ListService.getObjects(function (objects) {
        $scope.sprites = objects;
    });

    console.log('initial game:');
    console.log($scope.game);

    $scope.sendConfig = function () {
        $scope.game.setup.game_name = $scope.game_config.game_name;
        $scope.game.setup.canvas_size = {
            width: $scope.game_config.canvas_width,
            height: $scope.game_config.canvas_height
        }
        $scope.game.setup.starting_level = $scope.game_config.starting_level;
        $scope.game.setup.players = {
            min: $scope.game_config.min_players,
            max: $scope.game_config.max_players
        }
        $scope.game.setup.sprites = {};
        $scope.game.setup.sprites.sprite = [];
        $scope.game.setup.sprites.sprite.push($scope.sprites);
        $scope.game.setup.background = {
            background_id: 0,
            background_name: "Grey Background",
            image: "/img/game_background.jpg",
            speed: 1,
            position_type: "tiled"
        }
        
        var setup = {};
        setup.setup = $scope.game.setup;
        var form_data_xml = json2xml(setup);

        /*var form_data_json = {
            setup: $scope.game.setup
        }*/

        $http({
            method: 'POST',
            url: '/GameConfigServlet',
            //data: form_data_json
            data: form_data_xml
        });

        $location.path('levels');
    }
}

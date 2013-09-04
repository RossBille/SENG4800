'use strict';

/* Controllers */

function LevelsController($scope, ListService, GameService) {
    $scope.game = GameService.game;

    $scope.scene_objects = [];

    $scope.clicked = function () {
        console.log('scene item clicked');
    }

    $scope.objectClicked = function () {
        console.log('object clicked');
    }

    var level = {
        id: 0,
        name: "Level One",
        background_id: 1,
        objectList: {objects: []},
        events: []
    };

    ListService.getObjects(function (objects) {
        $scope.list = objects;
        level.objectList.objects = objects;
    });

    $scope.game.levels.push(level);

    console.log('game at end of levels controller:');
    console.log($scope.game);
}

function ConfigController($scope, $http, $location, GameService, ListService) {
    $scope.game = GameService.game;
    $scope.game_config = {};

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
        $scope.game.setup.sprites = [];
        $scope.game.setup.sprites.push($scope.sprites);
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
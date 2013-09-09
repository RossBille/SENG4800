'use strict';

/* Controllers */

function LevelsController($scope, $http, ListService, GameService, SaveXML) {
    $scope.game = GameService.game;

    $scope.scene_objects = [];

    $scope.current_level = null;

    $scope.createNewLevel = function () {
        var new_level = {
            level_id: level_index,
            level_name: "Level " + level_index,
            background_id: '',
            objects: {
                object: []
            },
            events: {
                event: []
            }
        };

        $scope.game.levels.level.push(new_level);

        $scope.current_level = $scope.game.levels.level[level_index];

        level_index++;
    };

    $scope.view_URL = '';

    $scope.selected_item = null;

    $scope.setSelectedItem = function (index) {
        $scope.selected_item = $scope.scene_objects[index];
    };

    $scope.clicked = function(index){
        console.log('scene item clicked');
        console.log(index);

        $scope.setSelectedItem(index);
        $scope.view_URL = 'views/ball_detail.html';

        console.log('game:');
        console.log($scope.game);
    };

    $scope.saveLevel = function () {
        console.log('now saving current level');

        $scope.current_level.objects.object = $scope.scene_objects;
    };

    $scope.newLevel = function () {
        console.log('now creating new level');

        $scope.scene_objects = [];
        scene_index = 0;

        $('.scene').empty();
        $scope.view_URL = '';

        $scope.createNewLevel();
    };

    $scope.saveGame = function() {
        console.log('now saving and POSTing game');

        var game_levels = {
            levels: {}
        };
        game_levels.levels = $scope.game.levels;

        var form_data_xml = {
            data: json2xml(game_levels),
            file_name: 'levels.xml'
        };

        $http({
            method: 'POST',
            url: '/LevelsServlet',
            data: form_data_xml.data
        });

        SaveXML.write(form_data_xml);
    };

    ListService.getObjects(function (objects) {
        $scope.list = objects;
    });

    $scope.createNewLevel();

    console.log('game at end of levels controller:');
    console.log($scope.game);
}

function ConfigController($scope, $http, $location, GameService, ListService, SaveXML) {
    $scope.game = GameService.game;

    ListService.getObjects(function (objects) {
        $scope.sprites = objects;
    });

    console.log('initial game:');
    console.log($scope.game);

    $scope.sendConfig = function () {
        $scope.game.setup.sprites.sprite.push($scope.sprites);

        var setup = {};
        setup.setup = $scope.game.setup;

        var form_data_xml = {
            data: json2xml(setup),
            file_name: 'game.xml'
        };

        /*var form_data_json = {
         setup: $scope.game.setup
         }*/

        $http({
            method: 'POST',
            url: '/GameConfigServlet',
            //data: form_data_json
            data: form_data_xml.data
        });

        SaveXML.write(form_data_xml);

        $location.path('levels');
    }
}

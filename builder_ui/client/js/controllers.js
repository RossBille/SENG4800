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

function ConfigController($scope, $http, $location, GameService) {
    $scope.game = GameService.game;
    $scope.game_config = {};

    console.log('initial game:');
    console.log($scope.game);

    $scope.sendConfig = function () {
        $scope.game.setup = $scope.game_config;

        console.log('game after form submit:');
        console.log($scope.game);

        $http({
            method: 'POST',
            url: '/GameConfigServlet',
            data: $scope.game.setup
        });

        $location.path('levels');
    }
}
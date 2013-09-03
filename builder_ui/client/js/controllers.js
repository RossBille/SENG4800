'use strict';

/* Controllers */

function LevelsController($scope, ListService) {
    ListService.getObjects(function (objects) {
        $scope.list = objects;
    });

    $scope.scene_objects = [];

    $scope.clicked = function () {
        console.log('clicked');
    }

    $scope.objectClicked = function () {
        console.log('object clicked');
    }
}

function ConfigController($scope, $http, $location) {
    $scope.game_config = {};

    $scope.sendConfig = function () {
        console.log($scope.game_config);

        $http({
            method: 'POST',
            url: '/GameConfigServlet',
            data: $scope.game_config
        });

        $location.path("levels");
    }
}
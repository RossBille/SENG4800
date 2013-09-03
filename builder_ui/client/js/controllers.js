'use strict';

/* Controllers */

function SceneController($scope, ListService) {
    ListService.getObjects(function (objects) {
        $scope.list = objects;
    });

    $scope.scene_objects = [];
}

function ConfigController($scope, $http) {
    $scope.game_config = {};

    $scope.sendConfig = function () {
        console.log($scope.game_config);

        $http({
            method: 'POST',
            url: '/GameConfigServlet',
            data: $scope.game_config
        })
    }
}
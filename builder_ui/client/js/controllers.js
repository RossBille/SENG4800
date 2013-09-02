'use strict';

/* Controllers */

function SceneController($scope, ListService) {
    ListService.getObjects(function (objects) {
        $scope.list = objects;
    });

    $scope.scene_objects = [];
}
// Bootstrap the Application
var App = angular.module('App', []);

// Instantiate global variables
var scene_index = 0;

/**
 * @author Clinton Ryan: University Of Newcastle
 * Asynchronous Factory for initialising web socket and receiving JSON
 **/
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

/**
 * @author Clinton Ryan
 * Controller for building the available objectss
 * The controller now works beautifully asynchronously and will block
 **/

App.controller('dndCtrl', function dndCtrl($scope, ListService) {
    ListService.getObjects(function (objects) {
        $scope.list = objects;
    });

    $scope.scene_objects = [];
});

// This makes any element draggable
// Usage: <div draggable>Foobar</div>
App.directive('draggable', function () {
    return {
        // A = attribute, E = Element, C = Class and M = HTML Comment
        restrict: 'A',
        //The link function is responsible for registering DOM listeners as well as updating the DOM.
        link: function (scope, element, attrs) {
            element.draggable({

                revert: true
            });
        }
    };
});

// This makes any element droppable
// Usage: <div droppable></div>
App.directive('droppable', function ($compile) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            //This makes an element Droppable
            element.droppable({
                drop: function (event, ui) {
                    var $drop_target = $(this);
                    var $draggable_original = ui.draggable;
                    var $draggable = $draggable_original.clone();
                    var $draggable_parent = $draggable_original.parent();
                    var $draggable_parent_parent = $draggable_parent.parent();
                    var draggable_original_index = $draggable_original.data('index');
                    var draggable_index = scene_index;


                    console.log('draggable original index: ' + draggable_original_index)
                    console.log('scene index: ' + scene_index)

                    if ($draggable_parent_parent.hasClass('list')) {
                        //getting current div old absolute position
                        var old_position = $draggable_original.offset();

                        $draggable.attr('data-index', scene_index);

                        scene_index++;

                        //assigning div to new parent
                        $drop_target.append($draggable);

                        //remove unneeded object container
                        //$draggable_parent.remove();

                        //getting current div new absolute position
                        var new_position = $draggable.offset();

                        //calculate correct position offset
                        var left_offset = null;
                        var top_offset = null;

                        if (old_position.left > new_position.left) {
                            left_offset = (old_position.left - new_position.left);
                        } else {
                            left_offset = -(new_position.left - old_position.left);
                        }

                        if (old_position.top > new_position.top) {
                            top_offset = (old_position.top - new_position.top);
                        } else {
                            top_offset = -(new_position.top - old_position.top);
                        }

                        //instantly offsetting the div to it current position
                        $draggable.animate({
                            left: '+=' + left_offset,
                            top: '+=' + top_offset

                        }, 0);

                        scope.scene_objects.push(scope.list[draggable_original_index]);

                        var offset = $draggable.position();
                        var offset_left = Math.round(offset.left);
                        var offset_top = Math.round(offset.top);

                        scope.scene_objects[draggable_index].xpos = offset_left;
                        scope.scene_objects[draggable_index].ypos = offset_top;
                        scope.scene_objects[draggable_index].id = draggable_index;

                        $draggable.css('height', scope.scene_objects[draggable_index].image_height);
                        $draggable.css('width',scope.scene_objects[draggable_index].image_width);
                        $draggable.css('background-image', 'url(' + scope.scene_objects[draggable_index].image_path + ')');


                        $draggable.draggable({
                            stop: function () {
                                var offset = $(this).position();
                                var offset_left = Math.round(offset.left);
                                var offset_top = Math.round(offset.top);

                                var draggable_index = $(this).data('index');

                                console.log('draggable index inside draggable: ' + draggable_index);

                                scope.scene_objects[draggable_index].xpos = offset_left;
                                scope.scene_objects[draggable_index].ypos = offset_top;

                                scope.$apply();
                            },
                            containment: ".scene"
                        });

                        scope.$apply();
                    }

                    /*$draggable.resizable({
                        stop: function (event, ui) {
                            var width = $draggable.width();
                            var height = $draggable.height();
                            var draggable_index = $draggable.data('index');

                            scope.list[draggable_index].width = width;
                            scope.list[draggable_index].height = height;

                            scope.$apply();
                        },
                        containment: '.scene'
                    });*/

                    console.log(scope.scene_objects);
                }
            });
        }
    };
});
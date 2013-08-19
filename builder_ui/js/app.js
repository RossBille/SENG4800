// Bootstrap the Application
var App = angular.module('App', []);

// Set up a controller and define a model, list1 and list2 (empty)
App.controller('dndCtrl', function ($scope) {
    $scope.list = [
        {
            "name": 'Object 1',
            "xpos": 0,
            "ypos": 0,
            "width": 0,
            "height": 0
        },
        {
            "name": 'Object 2',
            "xpos": 0,
            "ypos": 0,
            "width": 0,
            "height": 0
        },
        {
            "name": 'Object 3',
            "xpos": 0,
            "ypos": 0,
            "width": 0,
            "height": 0
        }
    ]
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
                stop: function () {
                    var offset = $(this).position();
                    var offset_left = Math.round(offset.left);
                    var offset_top = Math.round(offset.top);
                    var draggable_index = element.data('index');

                    scope.list[draggable_index].xpos = offset_left;
                    scope.list[draggable_index].ypos = offset_top;

                    scope.$apply();
                },
                containment: ".scene"
            });


            element.resizable({
                stop: function (event, ui) {
                    var width = $(this).width();
                    var height = $(this).height();
                    var draggable_index = element.data('index');

                    scope.list[draggable_index].width = width;
                    scope.list[draggable_index].height = height;

                    scope.$apply();
                },
                containment: '.scene'
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
                    var $draggable = ui.draggable;
                    var $draggable_parent = ui.draggable.parent();
                    var $draggable_parent_parent = $draggable_parent.parent();

                    if ($draggable_parent_parent.hasClass('list')) {
                        //getting current div old absolute position
                        var oldPosition = $draggable.offset();

                        //assigning div to new parent
                        $drop_target.append($draggable);

                        //remove unneeded object container
                        $draggable_parent.remove();

                        //getting current div new absolute position
                        var newPosition = $draggable.offset();

                        //calculate correct position offset
                        var leftOffset = null;
                        var topOffset = null;

                        if(oldPosition.left > newPosition.left) {
                            leftOffset = (oldPosition.left - newPosition.left);
                        } else {
                            leftOffset = -(newPosition.left - oldPosition.left);
                        }

                        if(oldPosition.top > newPosition.top) {
                            topOffset = (oldPosition.top - newPosition.top);
                        } else {
                            topOffset = -(newPosition.top - oldPosition.top);
                        }

                        //instantly offsetting the div to its current position
                        $draggable.animate( {

                            left: '+=' + leftOffset,
                            top: '+=' + topOffset

                        }, 0 )
                    }
                }
            });
        }
    };
});
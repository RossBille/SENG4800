// Bootstrap the Application
var App = angular.module('App', []);

// Set up a controller and define a model, list1 and list2 (empty)
App.controller('dndCtrl', function ($scope) {
    $scope.list = [
        {
            "name": 'Object 1',
            "xpos": -1,
            "ypos": -1,
            "width": 0,
            "height": 0
        },
        {
            "name": 'Object 2',
            "xpos": -1,
            "ypos": -1,
            "width": 0,
            "height": 0
        },
        {
            "name": 'Object 3',
            "xpos": -1,
            "ypos": -1,
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
                    var xPos = offset.left;
                    var yPos = offset.top;
                    var dragIndex = element.data('index');

                    scope.list[dragIndex].xpos = xPos;
                    scope.list[dragIndex].ypos = yPos;

                    scope.$apply();
                },
                containment: ".list"
            });

            element.resizable({
                stop: function(event, ui) {
                    var width = $(this).width();
                    var height = $(this).height();
                    var dragIndex = element.data('index');

                    scope.list[dragIndex].width = width;
                    scope.list[dragIndex].height = height;

                    scope.$apply();
                }
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
                /*hoverClass: 'drop-hover',
                drop: function (event, ui) {
                    var dragIndex = angular.element(ui.draggable).data('index'),
                        reject = angular.element(ui.draggable).data('reject'),
                        dragEl = angular.element(ui.draggable).parent(),
                        dropEl = angular.element(this);

                    if (dragEl.hasClass('list1') && !dropEl.hasClass('list1') && reject !== true) {
                     scope.list2.push(scope.list1[dragIndex]);
                     scope.list1.splice(dragIndex, 1);
                     } else if (dragEl.hasClass('list2') && !dropEl.hasClass('list2') && reject !== true) {
                     scope.list1.push(scope.list2[dragIndex]);
                     scope.list2.splice(dragIndex, 1);
                     }
                     scope.$apply();

                    //var position = dropEl.position();
                }*/
            });
        }
    };
});
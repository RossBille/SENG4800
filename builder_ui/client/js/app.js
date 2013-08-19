// Bootstrap the Application
var App = angular.module('App', []);

/**
 * @author Clinton Ryan: University Of Newcastle
 * Asynchronous Factory for initialising web socket and receiving JSON 
 **/
App.factory('ListService', function($timeout) {
    var service = {};
    var json = {};

    /* Our evented service call, this doesn't block on IO */
    service.getObjects = function(callback) {
        var ws = new WebSocket("ws://localhost:8080");
        ws.onmessage =  function (msg) {
            $timeout(function() {
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
    ListService.getObjects(function(objects) {
        $scope.list = objects;
    });
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
                containment: ".scene"
            });


            element.resizable({
                stop: function (event, ui) {
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
                 }

                 */
                 drop: function (event, ui) {
                    if (ui.draggable.parent().hasClass('list')) {
                        $(this).append(ui.draggable);
                    }
                }

                /*drop: function (event, ui) {
                 var targetDIV = document.getElementById('targetDIV');
                 var dropTarget = $(this);

                 //making sure the draggable div doesn't move on its own until we're finished moving it
                 ui.draggable.draggable( "option", "revert", false );

                 //getting current div old absolute position
                 var oldPosition = ui.draggable.offset();

                 //assigning div to new parent
                 //ui.draggable.insertBefore(dropTarget);
                 $(this).append(ui.draggable);

                 //getting current div new absolute position
                 var newPosition = ui.draggable.offset();

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

                 //instantly offsetting the div to it current position
                 ui.draggable.animate( {

                 left: '+=' + leftOffset,
                 top: '+=' + topOffset

                 }, 0 )

                 //allowing the draggable to revert to it's proper position in the new column
                 ui.draggable.draggable( "option", "revert", true );
             }*/
         });
}
};
});

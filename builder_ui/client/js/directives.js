'use strict';

/* Directives */

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

                    if ($draggable_parent_parent.hasClass('list')) {
                        //getting current div old absolute position
                        var old_position = $draggable_original.offset();

                        var new_object = '<div class="scene-object-container"><img src="' + scope.list[draggable_original_index].image_path + '" data-index="' + scene_index + '" ng-click="clicked()" class="object"></div>';
                        console.log(new_object);

                        $drop_target.append($compile(new_object)(scope));

                        var $new_object = $('img[data-index="' + scene_index + '"]');
                        var $new_object_container = $new_object.parent();

                        scene_index++;

                        console.log($new_object);
                        console.log($new_object_container);

                        console.log('new jQuery object has been created');

                        //assigning div to new parent
                        //$drop_target.append($draggable);

                        //getting current div new absolute position
                        var new_position = $new_object_container.offset();

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

                        console.log('left offset: ' + left_offset);
                        console.log('top offset: ' + top_offset);

                        //instantly offsetting the div to it current position
                        $new_object_container.animate({
                            left: '+=' + left_offset,
                            top: '+=' + top_offset

                        }, 0);

                        //push new object
                        var new_scene_object = jQuery.extend(true, {}, scope.list[draggable_original_index]);

                        var offset = $new_object_container.position();
                        console.log(offset);
                        var offset_left = Math.round(offset.left);
                        var offset_top = Math.round(offset.top);

                        console.log('left offset of the scene object: ' + left_offset);
                        console.log('top offset of the scene object: ' + top_offset);

                        new_scene_object.xpos = offset_left;
                        new_scene_object.ypos = offset_top;
                        new_scene_object.id = draggable_index;

                        console.log('new scene object:');
                        console.log(new_scene_object);

                        //console.log('saved scene object xpos: ' + scope.scene_objects[draggable_index].xpos);
                        //console.log('saved scene object ypos: ' + scope.scene_objects[draggable_index].ypos);

                        scope.scene_objects.push(new_scene_object);
                        console.log('scene objects after push:');
                        console.log(scope.scene_objects);

                        $new_object_container.draggable({
                            stop: function () {
                                console.log('scene object dragged');
                                var offset = $(this).position();
                                var offset_left = Math.round(offset.left);
                                var offset_top = Math.round(offset.top);
                                var draggable_index = $(this).find('img').data('index');

                                scope.scene_objects[draggable_index].xpos = offset_left;
                                scope.scene_objects[draggable_index].ypos = offset_top;

                                scope.$apply();
                            },
                            containment: ".scene"
                        });

                        $new_object.resizable({
                            stop: function (event, ui) {
                                console.log('scene object resized');
                                var width = $(this).width();
                                var height = $(this).height();
                                var draggable_index = $(this).find('img').data('index');

                                scope.scene_objects[draggable_index].width = width;
                                scope.scene_objects[draggable_index].height = height;

                                scope.$apply();
                            },
                            containment: '.scene'
                        });

                        scope.$apply();
                    }

                    console.log('scene objects final:');
                    console.log(scope.scene_objects);
                }
            });
        }
    };
});
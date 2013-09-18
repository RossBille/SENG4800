'use strict';

/* Directives */

App.directive('selectable', function() {
    return {
        // A = attribute, E = Element, C = Class and M = HTML Comment
        restrict: 'A',
        //The link function is responsible for registering DOM listeners as well as updating the DOM.
        link: function (scope, element, attrs) {
            element.selectable({
                filter: '.selectable',
                selected: function( event, ui ) {
                    console.log('selected item:');
                    console.log(ui.selected);
                }
            });
        }
    };
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
                    var $draggable_parent = $draggable_original.parent().parent();
                    var $draggable_parent_parent = $draggable_parent.parent();
                    var draggable_original_index = $draggable_original.data('index');
                    var draggable_index = scene_index;

                    if ($draggable_parent_parent.hasClass('object-list')) {
                        //getting current div old absolute position
                        var old_position = $draggable_original.offset();
                        console.log('old position:');
                        console.log(old_position);

                        var new_object = '<div class="scene-object-container"><img src="' + scope.objects[draggable_original_index].image + '" data-index="' + scene_index + '" ng-click="clicked(' + scene_index + ')" class="scene-object"></div>';
                        console.log(new_object);

                        $drop_target.append($compile(new_object)(scope));

                        var $new_object = $('.scene img[data-index="' + scene_index + '"]');
                        var $new_object_container = $new_object.parent();

                        $new_object_container.append('<div class="outline"></div>');

                        if (scope.objects[draggable_original_index].shape.circle) {
                            $new_object.attr('height', (scope.objects[draggable_original_index].shape.circle.radius * 2) / scope.canvas.multiplier);
                            $new_object_container.find('.outline').addClass('circle');
                        }
                        else {
                            $new_object.attr('width', scope.objects[draggable_original_index].shape.rectangle.size.width / scope.canvas.multiplier);
                            $new_object.attr('height', scope.objects[draggable_original_index].shape.rectangle.size.height / scope.canvas.multiplier);
                        }

                        scene_index++;

                        console.log($new_object);
                        console.log($new_object_container);

                        console.log('new jQuery object has been created');

                        //assigning div to new parent
                        //$drop_target.append($draggable);

                        //getting current div new absolute position
                        var new_position = element.offset();
                        console.log('new position:');
                        console.log(new_position);

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

                        console.log('top offset: ' + top_offset);
                        console.log('left offset: ' + left_offset);

                        //instantly offsetting the div to it current position
                        $new_object_container.animate({
                            left: '+=' + left_offset,
                            top: '+=' + top_offset

                        }, 0);

                        //push new object
                        //var new_scene_object = jQuery.extend(true, {}, scope.list[draggable_original_index]);
                        var new_scene_object = {
                            object_id: draggable_index,
                            object_name: scope.objects[draggable_original_index].sprite_name,
                            sprite_id: scope.objects[draggable_original_index].sprite_id,
                            object_shape: {},
                            object_visible: "yes",
                            start_pos: {
                                x: Math.round(left_offset * scope.canvas.multiplier),
                                y: Math.round(top_offset * scope.canvas.multiplier)
                            },
                            start_vel: {
                                x: 1,
                                y: 1
                            },
                            start_acc: {
                                x: 1,
                                y: 1
                            }
                        };

                        if (scope.objects[draggable_original_index].shape.circle) {
                            new_scene_object.object_shape = {
                                circle: {
                                    radius: scope.objects[draggable_original_index].shape.circle.radius
                                }
                            }
                        }
                        else {
                            new_scene_object.object_shape = {
                                rectangle: {
                                    size: {
                                        width: scope.objects[draggable_original_index].shape.rectangle.size.width,
                                        height: scope.objects[draggable_original_index].shape.rectangle.size.height
                                    }
                                }
                            }
                        }

                        /*var offset = $new_object_container.position();
                         console.log(offset);
                         var offset_left = Math.round(offset.left);
                         var offset_top = Math.round(offset.top);

                         console.log('left offset of the scene object: ' + offset_left);
                         console.log('top offset of the scene object: ' + offset_top);*/

                        /*new_scene_object.object_id = draggable_index;
                        new_scene_object.object_name = scope.list[draggable_original_index].sprite_name;
                        new_scene_object.sprite_id = scope.list[draggable_original_index].sprite_id;
                        new_scene_object.object_shape = {
                            circle: {
                                radius: scope.list[draggable_original_index].height
                            }
                        };
                        new_scene_object.object_visible = "yes";


                        new_scene_object.xpos = Math.round(left_offset);
                        new_scene_object.ypos = Math.round(top_offset);
                        new_scene_object.height = scope.list[draggable_original_index].image_height;
                        new_scene_object.width = scope.list[draggable_original_index].image_width; */

                        console.log('new scene object:');
                        console.log(new_scene_object);

                        //console.log('saved scene object xpos: ' + scope.scene_objects[draggable_index].xpos);
                        //console.log('saved scene object ypos: ' + scope.scene_objects[draggable_index].ypos);

                        scope.current_level.objects.object.push(new_scene_object);
                        console.log('scene objects after push:');
                        console.log(scope.current_level.objects.object);

                        $new_object_container.draggable({
                            stop: function () {
                                console.log('scene object dragged');
                                var offset = $(this).position();
                                var offset_left = Math.round(offset.left);
                                var offset_top = Math.round(offset.top);
                                var draggable_index = $(this).find('img').data('index');

                                scope.current_level.objects.object[draggable_index].start_pos.x = offset_left * scope.canvas.multiplier;
                                scope.current_level.objects.object[draggable_index].start_pos.y = offset_top * scope.canvas.multiplier;

                                scope.$apply();
                            },
                            containment: ".scene"
                        });

                        $new_object.resizable({
                            stop: function () {
                                console.log('scene object resized');

                                var width = $(this).width();
                                var height = $(this).height();
                                var draggable_index = $(this).find('img').data('index');

                                if (scope.current_level.objects.object[draggable_index].object_shape.circle) {
                                    scope.current_level.objects.object[draggable_index].object_shape.circle.radius = (width / 2) * scope.canvas.multiplier;
                                }
                                else if (scope.current_level.objects.object[draggable_index].object_shape.rectangle) {
                                    scope.current_level.objects.object[draggable_index].object_shape.rectangle.size.width = width * scope.canvas.multiplier;
                                    scope.current_level.objects.object[draggable_index].object_shape.rectangle.size.height = height * scope.canvas.multiplier;
                                }

                                scope.$apply();
                            },
                            containment: '.scene'
                        });

                        scope.$apply();
                    }

                    console.log('scene objects final:');
                    console.log(scope.current_level.objects.object);
                }
            });
        }
    };
});
'use strict';

/* Controllers */

function LevelsController($scope, $location, $compile, CanvasService, GameService, ConfigCompletionService, SaveXML) {
    $scope.config_completed = ConfigCompletionService.config_completed;

    if ($scope.config_completed.status === false) {
        $location.path('/config');
    }

    $scope.game = GameService.game;
    $scope.canvas = CanvasService.canvas;

    $scope.canvas.width = $scope.game.setup.canvas_size.width;
    $scope.canvas.height = $scope.game.setup.canvas_size.height;

    if ($scope.canvas.width > 1280) {
        $scope.canvas.multiplier = 2;
    }
    else {
        $scope.canvas.multiplier = 1;
    }

    var $scene_container = $('.scene-container');

    $scene_container.css('width', $scope.canvas.width / $scope.canvas.multiplier);
    $scene_container.css('height', $scope.canvas.height / $scope.canvas.multiplier);

    if ($scope.canvas.multiplier === 2) {
        var new_object = '<span class="scene-scale">Scale: 0.5</span>';
    }
    else {
        var new_object = '<span class="scene-scale">Scale: 1</span>';
    }

    $scene_container.append(new_object);

    $scope.scene_background = {'background-image': 'none'};

    $scope.$watch(
        "current_level.background_id",
        function(new_value, old_value) {
            if ($scope.game.setup.backgrounds.background[new_value]) {
                $scope.scene_background = {'background-image': 'url(' + $scope.game.setup.backgrounds.background[new_value].image + ')'};

                $('.scene').removeClass('fill').removeClass('centre').removeClass('tiled');
                $('.scene').addClass($scope.game.setup.backgrounds.background[new_value].position_type);
            }
            else {
                $('.scene').removeClass('fill').removeClass('centre').removeClass('tiled');
                $scope.scene_background = {'background-image': 'none'};
            }
        }
    );

    /* LEVELS */
    $scope.current_level = null;

    $scope.createNewLevel = function() {
        level_index++;

        var new_level = {
            level_id: level_index,
            level_name: "Level " + level_index,
            background_id: '',
            objects: {
                object: []
            },
            events: {
                event: [
                    {
                        event_id: 0,
                        event_type: {
                            step: {}
                        },
                        actions: {
                            action: []
                        }
                    }
                ]
            }
        };

        $scope.game.levels.level.push(new_level);

        $scope.current_level = $scope.game.levels.level[level_index];
    };

    $scope.newLevel = function() {
        console.log('now creating new level');

        scene_index = 0;
        event_index = 0;

        $('.scene').empty();
        $scope.view_URL = '';

        $scope.createNewLevel();
    };

    $scope.currentLevelChanged = function() {
        console.log('current level changed to:');
        console.log($scope.current_level);

        $scope.view_URL = '';
        $scope.saveEvent(false);
        $scope.saveAction(false);

        $('.scene').empty();

        angular.forEach($scope.current_level.objects.object, function(value, key) {
            var $drop_target = $('.scene');

            var new_object = '<div class="scene-object-container"><img src="' + $scope.objects[value.sprite_id].images.image[0] + '" data-index="' + value.object_id + '"class="scene-object"><div class="outline" ng-click="clicked(' + value.object_id + ')" ></div></div>';

            $drop_target.append($compile(new_object)($scope));

            var $new_object = $('.scene img[data-index="' + value.object_id + '"]');
            var $new_object_parent = $new_object.parent();
            var $new_object_container = $new_object.closest('.scene-object-container');

            if ($scope.objects[value.sprite_id].shape.circle) {
                $new_object.attr('height', ($scope.objects[value.sprite_id].shape.circle.radius * 2) / $scope.canvas.multiplier);
                $new_object_parent.find('.outline').addClass('circle');
            }
            else {
                $new_object.attr('width', $scope.objects[value.sprite_id].shape.rectangle.size.width / $scope.canvas.multiplier);
                $new_object.attr('height', $scope.objects[value.sprite_id].shape.rectangle.size.height / $scope.canvas.multiplier);
            }

            $new_object_container.css({left: value.start_pos.x / $scope.canvas.multiplier, top: value.start_pos.y / $scope.canvas.multiplier});

            $new_object_container.draggable({
                stop: function () {
                    console.log('scene object dragged');
                    var offset = $(this).position();
                    var offset_left = Math.round(offset.left);
                    var offset_top = Math.round(offset.top);
                    var draggable_index = $(this).find('img').data('index');

                    var dragged_object_index = -1;

                    angular.forEach($scope.current_level.objects.object, function(value, index) {
                        if (draggable_index === value.object_id) {
                            dragged_object_index = index;

                            console.log('found dragged object index: ' + dragged_object_index);
                        }
                    });

                    if(dragged_object_index === -1) {
                        console.log('ERROR: dragged object index was not found!');
                        return;
                    }

                    $scope.current_level.objects.object[dragged_object_index].start_pos.x = offset_left * $scope.canvas.multiplier;
                    $scope.current_level.objects.object[dragged_object_index].start_pos.y = offset_top * $scope.canvas.multiplier;

                    $scope.$apply();
                },
                containment: ".scene"
            });

            var preserve_aspect_ratio = false;

            if($new_object_container.find('.outline').hasClass('circle')) {
                preserve_aspect_ratio = true;
            }

            $new_object.resizable({
                aspectRatio: preserve_aspect_ratio,
                stop: function () {
                    console.log('scene object resized');

                    var width = $(this).width();
                    var height = $(this).height();
                    var draggable_index = $(this).find('img').data('index');

                    var dragged_object_index = -1;

                    angular.forEach($scope.current_level.objects.object, function(value, index) {
                        if (draggable_index === value.object_id) {
                            dragged_object_index = index;

                            console.log('found dragged object index: ' + dragged_object_index);
                        }
                    });

                    if(dragged_object_index === -1) {
                        console.log('ERROR: dragged object index was not found!');
                        return;
                    }

                    if ($scope.current_level.objects.object[dragged_object_index].object_shape.circle) {
                        $scope.current_level.objects.object[dragged_object_index].object_shape.circle.radius = (width / 2) * $scope.canvas.multiplier;
                    }
                    else if ($scope.current_level.objects.object[dragged_object_index].object_shape.rectangle) {
                        $scope.current_level.objects.object[dragged_object_index].object_shape.rectangle.size.width = width * $scope.canvas.multiplier;
                        $scope.current_level.objects.object[dragged_object_index].object_shape.rectangle.size.height = height * $scope.canvas.multiplier;
                    }

                    $scope.$apply();
                },
                containment: '.scene'
            });
        });
    };

    $scope.game.levels.level = [];
    $scope.createNewLevel();

    /* EVENTS */
    $scope.event_view_URL = '';
    $scope.event_selected_control_URL = '';

    $scope.events = {
        event_type_views: [
            {name: 'Collision', partial: 'views/events/collision.html'},
            {name: 'Boundary', partial: 'views/events/boundary.html'},
            {name: 'Timer', partial: 'views/events/timer.html'},
            {name: 'Input', partial: 'views/events/input.html'},
            {name: 'Step', partial: ''}
        ],
        event_type_options: {
            edges_options: [
                'top', 'bottom', 'left', 'right', 'all'
            ],
            input_type_options: [
                'up', 'down', 'left', 'right', 'vector'
            ]
        }
    }

    $scope.selected_event_type = {
        event_type: null
    };

    $scope.old_selected_event_type = {
        event_type: null
    };

    $scope.selectedEventChanged = function() {
        console.log('selected_event_type:');
        console.log($scope.selected_event_type.event_type);

        if ($scope.selected_event_type.event_type !== null) {
            if ($scope.selected_event_type.event_type.name === 'Step') {
                if ($scope.old_selected_event_type.event_type !== null) {
                    $scope.selected_event_type.event_type = $scope.getEventTypeByName($scope.old_selected_event_type.event_type.name);
                }
                else {
                    $scope.selected_event_type.event_type = null;
                }

                $.pnotify({
                    title: 'New Step Event',
                    text: 'A new Step event cannot be created. Select the Step event from the list of events to add actions to it.',
                    type: 'error'
                });

                return;
            }

            $scope.old_selected_event_type.event_type = $scope.selected_event_type.event_type;

            $scope.event_selected_control_URL = 'views/actions/event_selected_control.html';
            $scope.current_event.actions.action = [];
            $scope.saveAction(false);
            action_index = -1;

            if ($scope.selected_event_type.event_type.name === 'Collision') {
                $scope.current_event.event_type = {
                    collision: {
                        object_id1: '',
                        object_id2: '',
                        allow_overlap: 'no'
                    }
                }
            }
            else if ($scope.selected_event_type.event_type.name === 'Boundary') {
                $scope.current_event.event_type = {
                    boundary: {
                        object_id: '',
                        level_id: '',
                        edges: '',
                        allow_overlap: 'no'
                    }
                }

                $scope.current_event.event_type.boundary.level_id = level_index;
            }
            else if ($scope.selected_event_type.event_type.name === 'Timer') {
                $scope.current_event.event_type = {
                    timer: {
                        length: '',
                        repeat: 'no'
                    }
                }
            }
            else if ($scope.selected_event_type.event_type.name === 'Input') {
                $scope.current_event.event_type = {
                    input: {
                        player_id: '',
                        value: {
                            x: '',
                            y: ''
                        },
                        type: ''
                    }
                }
            }
        }
        else {
            alert('$scope.selected_event_type.event_type === null');

            $scope.event_selected_control_URL = '';
        }
    };

    $scope.current_event = null;

    $scope.newEvent = function() {
        event_index++;
        action_index = -1;

        $scope.saveEvent(false);
        $scope.saveAction(false);

        $scope.event_view_URL = 'views/event_detail.html';

        var new_event = {
            event_id: event_index,
            event_type: {},
            actions: {
                action: []
            }
        };

        $scope.game.levels.level[level_index].events.event.push(new_event);

        var current_event_index = -1;

        angular.forEach($scope.current_level.events.event, function(value, index) {
            if (event_index === value.event_id) {
                current_event_index = index;

                console.log('found current event index: ' + current_event_index);
            }
        });

        if (current_event_index === -1) {
            console.log('ERROR: current event index was not found!');
            return;
        }

        $scope.current_event = $scope.current_level.events.event[current_event_index];
    };

    $scope.saveEvent = function(show_notification) {
        show_notification = typeof show_notification !== 'undefined' ? show_notification : true;

        $scope.event_view_URL = '';
        $scope.event_selected_control_URL = '';

        $scope.selected_event_type = {
            event_type: null
        };

        $scope.current_event = null;

        if (show_notification) {
            $.pnotify({
                title: 'Event Saved',
                text: 'The current event has been saved.',
                type: 'success'
            });
        }
    };

    $scope.deleteEvent = function() {
        if ($scope.current_event.event_type.step) {
            $.pnotify({
                title: 'Event Not Deleted',
                text: 'The step event cannot be deleted.',
                type: 'error'
            });

            return;
        }

        var index_to_delete = -1;

        angular.forEach($scope.current_level.events.event, function(value, index) {
            if ($scope.current_event.event_id === value.event_id) {
                index_to_delete = index;

                console.log('found index to delete: ' + index_to_delete);
            }
        });

        if (index_to_delete === -1) {
            console.log('ERROR: index to delete was not found!');
            return;
        }

        $scope.current_level.events.event.splice(index_to_delete, 1);

        $.pnotify({
            title: 'Event Deleted',
            text: 'The current event has been deleted.',
            type: 'info'
        });

        $scope.saveEvent(false);
    };

    $scope.getEventTypeByName = function(value) {
        console.log('searching for event type: ' + value);

        for (var i = 0; i < $scope.events.event_type_views.length; i++) {
            if ($scope.events.event_type_views[i].name === value) {
                console.log('event type view found:');
                console.log($scope.events.event_type_views[i]);

                $scope.event_selected_control_URL = 'views/actions/event_selected_control.html';

                return $scope.events.event_type_views[i];
            }
        }

        console.log('event type view not found');
        return '';
    };

    $scope.currentEventChanged = function() {
        $scope.event_view_URL = 'views/event_detail.html';
        $scope.action_view_URL = '';
        $scope.current_action = null;
        var current_event_type;

        console.log('current event changed to:');
        console.log($scope.current_event);

        for (var key in $scope.current_event.event_type) {
            current_event_type = key.charAt(0).toUpperCase() + key.slice(1);
        }

        $scope.selected_event_type.event_type = $scope.getEventTypeByName(current_event_type);
    };

    $scope.getEventLabel = function(event_type) {
        var first_key = null;

        angular.forEach(event_type, function(value, key) {
            if (first_key === null) {
                first_key = key;
            }
        });

        if (!first_key) {
            return 'New event';
        }

        first_key = first_key.replace(/_/g, " ");

        return first_key.charAt(0).toUpperCase() + first_key.slice(1);
    };

    /* ACTIONS */
    $scope.action_view_URL = '';
    $scope.action_controls_URL = '';

    $scope.actions = {
        action_type_views: [
            {name: 'Change sprite', partial: 'views/actions/change_sprite.html'},
            {name: 'Change score', partial: 'views/actions/change_score.html'},
            {name: 'Change component', partial: 'views/actions/change_component.html'},
            {name: 'Change level', partial: 'views/actions/change_level.html'},
            {name: 'Reflect', partial: 'views/actions/reflect.html'},
            {name: 'Input', partial: 'views/actions/input.html'}
        ],
        action_type_options: {
            type_options: [
                'add', 'sub', 'set'
            ],
            component_options: [
                'pos', 'vel', 'acc'
            ]
        }
    };

    $scope.selected_action_type = {
        action_type: null
    };

    $scope.selectedActionChanged = function() {
        console.log('selected_action_type:');
        console.log($scope.selected_action_type.action_type);

        if ($scope.selected_action_type.action_type !== null) {
            $scope.action_controls_URL = 'views/actions/action_controls.html';

            if ($scope.selected_action_type.action_type.name === 'Change sprite') {
                $scope.current_action.action_type = {
                    change_sprite: {
                        object_id: '',
                        sprite_id: ''
                    }
                }
            }
            else if ($scope.selected_action_type.action_type.name === 'Change score') {
                $scope.current_action.action_type = {
                    change_score: {
                        player_id: '',
                        value: '',
                        type: ''
                    }
                }
            }
            else if ($scope.selected_action_type.action_type.name === 'Change component') {
                $scope.current_action.action_type = {
                    change_component: {
                        object_id: '',
                        value: {
                            x: '',
                            y: ''
                        },
                        component: '',
                        type: ''
                    }
                }
            }
            else if ($scope.selected_action_type.action_type.name === 'Change level') {
                $scope.current_action.action_type = {
                    change_level: {
                        level_id: ''
                    }
                }
            }
            else if ($scope.selected_action_type.action_type.name === 'Reflect') {
                $scope.current_action.action_type = {
                    reflect: {
                        object_id: ''
                    }
                }
            }
            else if ($scope.selected_action_type.action_type.name === 'Input') {
                $scope.current_action.action_type = {
                    input: {
                        object_id: '',
                        player_id: '',
                        type: ''
                    }
                }
            }
        }
        else {
            $scope.action_controls_URL = '';
        }
    };

    $scope.current_action = null;

    $scope.newAction = function() {
        action_index++;

        $scope.saveAction(false);

        $scope.action_view_URL = 'views/action_detail.html';

        var new_action = {
            action_id: action_index,
            action_type: {}
        };

        $scope.current_event.actions.action.push(new_action);

        var current_action_index = -1;

        angular.forEach($scope.current_event.actions.action, function(value, index) {
            if (action_index === value.action_id) {
                current_action_index = index;

                console.log('found current action index: ' + current_action_index);
            }
        });

        if (current_action_index === -1) {
            console.log('ERROR: current action index was not found!');
            return;
        }

        $scope.current_action = $scope.current_event.actions.action[current_action_index];
    };

    $scope.saveAction = function(show_notification) {
        show_notification = typeof show_notification !== 'undefined' ? show_notification : true;

        $scope.action_view_URL = '';
        $scope.action_controls_URL = '';

        $scope.selected_action_type = {
            action_type: null
        };

        $scope.selected_reflect_option = {
            reflect_option: null
        };

        $scope.current_action = null;

        if (show_notification) {
            $.pnotify({
                title: 'Action Saved',
                text: 'The current action has been saved.',
                type: 'success'
            });
        }
    };

    $scope.deleteAction = function() {
        var index_to_delete = -1;

        angular.forEach($scope.current_event.actions.action, function(value, index) {
            if ($scope.current_action.action_id === value.action_id) {
                index_to_delete = index;

                console.log('found index to delete: ' + index_to_delete);
            }
        });

        if (index_to_delete === -1) {
            console.log('ERROR: index to delete was not found!');
            return;
        }

        $scope.current_event.actions.action.splice(index_to_delete, 1);

        $.pnotify({
            title: 'Action Deleted',
            text: 'The current action has been deleted.',
            type: 'info'
        });

        $scope.saveAction(false);
    };

    $scope.getActionTypeByName = function(value) {
        for (var i = 0; i < $scope.actions.action_type_views.length; i++) {
            if ($scope.actions.action_type_views[i].name === value) {
                console.log('action type view found:');
                console.log($scope.actions.action_type_views[i]);

                $scope.action_controls_URL = 'views/actions/action_controls.html';

                return $scope.actions.action_type_views[i];
            }
        }

        console.log('action type view not found');
        return '';
    };

    $scope.currentActionChanged = function() {
        $scope.action_view_URL = 'views/action_detail.html';
        var current_action_type;

        for (var key in $scope.current_action.action_type) {
            current_action_type = key.charAt(0).toUpperCase() + key.slice(1);
            current_action_type = current_action_type.replace(/_/g, " ");
            console.log('current_action_type: ' + current_action_type);
        }

        $scope.selected_action_type.action_type = $scope.getActionTypeByName(current_action_type);
    };

    $scope.getLabel = function(type, item_type) {
        var first_key = null;

        angular.forEach(type, function(value, key) {
            if (first_key === null) {
                first_key = key;
            }
        });

        if (!first_key) {
            return 'New ' + item_type;
        }

        first_key = first_key.replace(/_/g, " ");

        return first_key.charAt(0).toUpperCase() + first_key.slice(1);
    };

    /* REFLECT ACTION */
    $scope.reflect_option_views = [
        {name: 'Object', partial: 'views/actions/reflect_object.html'},
        {name: 'Level', partial: ''}
    ]

    $scope.selected_reflect_option = {
        reflect_option: null
    };

    $scope.selectedReflectOptionChanged = function() {
        console.log('selected_reflect_option:');
        console.log($scope.selected_reflect_option.reflect_option);

        if ($scope.selected_reflect_option.reflect_option !== null) {
            if ($scope.selected_reflect_option.reflect_option.name === 'Object') {
                $scope.current_action.action_type.reflect.surface_object_id = '';
                delete $scope.current_action.action_type.reflect.surface_level_id;
            }
            else if ($scope.selected_reflect_option.reflect_option.name === 'Level') {
                $scope.current_action.action_type.reflect.surface_level_id = $scope.current_level.level_id;
                delete $scope.current_action.action_type.reflect.surface_object_id;
            }
        }
    };

    /* SCENE ITEMS */
    $scope.view_URL = '';
    $scope.object_shape_view_URL = '';
    $scope.selected_object = null;

    $scope.clicked = function(clicked_index) {
        console.log('scene item clicked: ' + clicked_index);

        $('.scene .scene-object-container').removeClass('selected');
        $('.scene img[data-index="' + clicked_index + '"]').closest('.scene-object-container').addClass('selected');

        var selected_object_index = -1;

        angular.forEach($scope.current_level.objects.object, function(value, index) {
            if (clicked_index === value.object_id) {
                selected_object_index = index;

                console.log('found selected object index: ' + selected_object_index);
            }
        });

        if (selected_object_index === -1) {
            console.log('ERROR: selected object index was not found!');
            return;
        }

        $scope.selected_object = $scope.current_level.objects.object[selected_object_index];

        $scope.view_URL = 'views/object_detail.html';

        if ($scope.selected_object.object_shape.circle) {
            $scope.object_shape_view_URL = 'views/shapes/object_circle.html';
        }
        else {
            $scope.object_shape_view_URL = 'views/shapes/object_rectangle.html';
        }
    };

    $scope.getObjectShape = function() {
        if ($scope.selected_object.object_shape.circle) {
            return 'Circle';
        }
        else {
            return 'Rectangle';
        }
    };

    $scope.deleteObject = function() {
        var index_to_delete = -1;
        var index_to_remove = $scope.selected_object.object_id;

        angular.forEach($scope.current_level.objects.object, function(value, index) {
            if ($scope.selected_object.object_id === value.object_id) {
                index_to_delete = index;

                console.log('found index to delete: ' + index_to_delete);
            }
        });

        if (index_to_delete === -1) {
            console.log('ERROR: index to delete was not found!');
            return;
        }

        $scope.current_level.objects.object.splice(index_to_delete, 1);

        $.pnotify({
            title: 'Object Deleted',
            text: 'The selected object has been deleted.',
            type: 'info'
        });

        $('.scene').find("img[data-index='" + index_to_remove + "']").closest('.scene-object-container').remove();
        $scope.selected_object = null;
        $scope.view_URL = '';
    };

    $scope.copyObject = function() {
        var $new_scene_object = jQuery.extend(true, {}, $scope.selected_object);
        $new_scene_object.object_id = scene_index;
        $new_scene_object.start_pos.x += 50;
        $new_scene_object.start_pos.y += 50;
        $scope.current_level.objects.object.push($new_scene_object);
        scene_index++;

        var $drop_target = $('.scene');
        var new_object = '<div class="scene-object-container"><img src="' + $scope.objects[$new_scene_object.sprite_id].images.image[0] + '" data-index="' + $new_scene_object.object_id + '"class="scene-object"><div class="outline" ng-click="clicked(' + $new_scene_object.object_id + ')" ></div></div>';

        $drop_target.append($compile(new_object)($scope));

        var $new_object = $('.scene img[data-index="' + $new_scene_object.object_id + '"]');
        var $new_object_parent = $new_object.parent();
        var $new_object_container = $new_object.closest('.scene-object-container');

        if ($scope.objects[$new_scene_object.sprite_id].shape.circle) {
            $new_object.attr('height', ($new_scene_object.object_shape.circle.radius * 2) / $scope.canvas.multiplier);
            $new_object_parent.find('.outline').addClass('circle');
        }
        else {
            $new_object.attr('width', $new_scene_object.object_shape.rectangle.size.width / $scope.canvas.multiplier);
            $new_object.attr('height', $new_scene_object.object_shape.rectangle.size.height / $scope.canvas.multiplier);
        }

        $new_object_container.css({left: ($new_scene_object.start_pos.x + 50) / $scope.canvas.multiplier, top: ($new_scene_object.start_pos.y + 50) / $scope.canvas.multiplier});

        $new_object_container.draggable({
            stop: function () {
                console.log('scene object dragged');
                var offset = $(this).position();
                var offset_left = Math.round(offset.left);
                var offset_top = Math.round(offset.top);
                var draggable_index = $(this).find('img').data('index');

                var dragged_object_index = -1;

                angular.forEach($scope.current_level.objects.object, function(value, index) {
                    if (draggable_index === value.object_id) {
                        dragged_object_index = index;

                        console.log('found dragged object index: ' + dragged_object_index);
                    }
                });

                if(dragged_object_index === -1) {
                    console.log('ERROR: dragged object index was not found!');
                    return;
                }

                $scope.current_level.objects.object[dragged_object_index].start_pos.x = offset_left * $scope.canvas.multiplier;
                $scope.current_level.objects.object[dragged_object_index].start_pos.y = offset_top * $scope.canvas.multiplier;

                $scope.$apply();
            },
            containment: ".scene"
        });

        var preserve_aspect_ratio = false;

        if($new_object_container.find('.outline').hasClass('circle')) {
            preserve_aspect_ratio = true;
        }

        $new_object.resizable({
            aspectRatio: preserve_aspect_ratio,
            stop: function () {
                console.log('scene object resized');

                var width = $(this).width();
                var height = $(this).height();
                var draggable_index = $(this).find('img').data('index');

                var dragged_object_index = -1;

                angular.forEach($scope.current_level.objects.object, function(value, index) {
                    if (draggable_index === value.object_id) {
                        dragged_object_index = index;

                        console.log('found dragged object index: ' + dragged_object_index);
                    }
                });

                if(dragged_object_index === -1) {
                    console.log('ERROR: dragged object index was not found!');
                    return;
                }

                if ($scope.current_level.objects.object[dragged_object_index].object_shape.circle) {
                    $scope.current_level.objects.object[dragged_object_index].object_shape.circle.radius = (width / 2) * $scope.canvas.multiplier;
                }
                else if ($scope.current_level.objects.object[dragged_object_index].object_shape.rectangle) {
                    $scope.current_level.objects.object[dragged_object_index].object_shape.rectangle.size.width = width * $scope.canvas.multiplier;
                    $scope.current_level.objects.object[dragged_object_index].object_shape.rectangle.size.height = height * $scope.canvas.multiplier;
                }

                $scope.$apply();
            },
            containment: '.scene'
        });

        $scope.clicked($new_scene_object.object_id);
    };

    $scope.$watch(
        "selected_object.start_pos.x",
        function(new_value, old_value) {
            if (new_value === null) {
                console.log('new value is null');
                return;
            }

            if (new_value === undefined) {
                console.log('new value is undefined');
                $scope.selected_object.start_pos.x = old_value;
                return;
            }

            if($scope.selected_object.object_shape.circle) {
                var right_value = new_value + ($scope.selected_object.object_shape.circle.radius * 2);
            }
            else {
                var right_value = new_value + ($scope.selected_object.object_shape.rectangle.size.width);
            }

            if(right_value > $scope.game.setup.canvas_size.width) {
                $.pnotify({
                    title: 'Unable to Move Object',
                    text: 'Cannot move object to new position since it would be outside of the canvas',
                    type: 'error'
                });

                $scope.selected_object.start_pos.x = old_value;
                return;
            }

            var $scene_object = $('.scene').find("img[data-index='" + $scope.selected_object.object_id + "']");
            var $scene_object_container = $scene_object.closest('.scene-object-container');

            $scene_object_container.css({left: new_value / $scope.canvas.multiplier});
            console.log('set left offset to new value');
        }
    );

    $scope.$watch(
        "selected_object.start_pos.y",
        function(new_value, old_value) {
            if (new_value === null) {
                console.log('new value is null');
                return;
            }

            if (new_value === undefined) {
                console.log('new value is undefined');
                $scope.selected_object.start_pos.y = old_value;
                return;
            }

            if($scope.selected_object.object_shape.circle) {
                var bottom_value = new_value + ($scope.selected_object.object_shape.circle.radius * 2);
            }
            else {
                var bottom_value = new_value + ($scope.selected_object.object_shape.rectangle.size.height);
            }

            if(bottom_value > $scope.game.setup.canvas_size.height) {
                $.pnotify({
                    title: 'Unable to Move Object',
                    text: 'Cannot move object to new position since it would be outside of the canvas',
                    type: 'error'
                });

                $scope.selected_object.start_pos.y = old_value;
                return;
            }

            var $scene_object = $('.scene').find("img[data-index='" + $scope.selected_object.object_id + "']");
            var $scene_object_container = $scene_object.closest('.scene-object-container');

            $scene_object_container.css({top: new_value / $scope.canvas.multiplier});
            console.log('set top offset to new value');
        }
    );

    /* GAME */
    $scope.saveGame = function() {
        console.log('now saving and sending game to server');

        var game_levels = {
            levels: $scope.game.levels
        };

        var form_data_xml = {
            data: json2xml(game_levels),
            file_name: 'levels.xml'
        };

        SaveXML.write(form_data_xml);

        $.pnotify({
            title: 'Levels Configuration Saved',
            text: 'Your levels configuration has been saved to the following location: server/' + form_data_xml.file_name,
            type: 'success'
        });
    };

    $scope.objects = $scope.game.setup.sprites.sprite;

    $scope.determineOrientation = function(item) {
        if (item.shape.circle) {
            return 'landscape circle';
        }
        else {
            if (item.shape.rectangle.size.width >= item.shape.rectangle.size.height) {
                return 'landscape';
            }
            else {
                return 'portrait';
            }
        }
    };
}

function ConfigController($scope, $location, GameService, ListService, CanvasService, ConfigCompletionService, SaveXML) {
    $scope.game = GameService.game;
    $scope.canvas = CanvasService.canvas;
    $scope.config_completed = ConfigCompletionService.config_completed;

    $scope.saveConfig = function() {
        var sprite_index = 0;
        var background_index = 0;
        level_index = -1;
        $scope.game.setup.sprites.sprite = [];
        $scope.game.setup.backgrounds.background = [];

        $.each($('#sprites .ui-selected'), function() {
            var index = $(this).attr('data-index');

            var new_sprite = {
                sprite_id: sprite_index,
                sprite_name: $scope.sprites[index].sprite_name,
                images: $scope.sprites[index].images,
                speed: $scope.sprites[index].speed,
                offset: {
                    x: $scope.sprites[index].offset.x,
                    y: $scope.sprites[index].offset.y
                },
                shape: {}
            };

            if ($scope.sprites[index].shape.circle) {
                new_sprite.shape = {
                    circle: {
                        radius: $scope.sprites[index].shape.circle.radius
                    }
                };
            }
            else {
                new_sprite.shape = {
                    rectangle: {
                        size: {
                            width: $scope.sprites[index].shape.rectangle.size.width,
                            height: $scope.sprites[index].shape.rectangle.size.height
                        }
                    }
                };
            }

            $scope.game.setup.sprites.sprite.push(new_sprite);

            sprite_index++;
        });

        if ($scope.game.setup.sprites.sprite.length === 0) {
            $.pnotify({
                title: 'Configuration Not Saved',
                text: 'You must select at least one sprite to use in your game.',
                type: 'error'
            });

            return;
        }

        $.each($('#backgrounds .ui-selected'), function() {
            var index = $(this).attr('data-index');

            var new_background = {
                background_id: background_index,
                background_name: $scope.backgrounds[index].background_name,
                image: $scope.backgrounds[index].image,
                speed: $scope.backgrounds[index].speed,
                position_type: $scope.backgrounds[index].position_type
            };

            $scope.game.setup.backgrounds.background.push(new_background);

            background_index++;
        });

        var setup = {
            setup: $scope.game.setup
        };

        var form_data_xml = {
            data: json2xml(setup),
            file_name: 'game.xml'
        };

        SaveXML.write(form_data_xml);

        $scope.config_completed.status = true;

        $.pnotify({
            title: 'Game Configuration Saved',
            text: 'Your game configuration has been saved to the following location: server/' + form_data_xml.file_name,
            type: 'success'
        });
    };

    $scope.configureLevels = function() {
        if ($scope.config_completed.status === false) {
            $.pnotify({
                title: 'Cannot Configure Levels',
                text: 'You must save the game configuration to file before configuring levels.',
                type: 'error'
            });
            return;
        }

        $location.path('levels');
    };

    $scope.determineOrientation = function(item) {
        if (item.shape.circle) {
            return 'landscape circle';
        }
        else {
            if (item.shape.rectangle.size.width >= item.shape.rectangle.size.height) {
                return 'landscape';
            }
            else {
                return 'portrait';
            }
        }
    };

    /* SERVER DATA */
    ListService.getObjects(function(data) {
        $scope.sprites = data.objects.Objects;

        console.log('sprites:');
        console.log($scope.sprites);

        angular.forEach($scope.sprites, function(value, key) {
            if (value.sprite_id > sprite_index) {
                sprite_index = value.sprite_id;
            }
        });

        $scope.backgrounds = data.backgrounds.Backgrounds;

        console.log('backgrounds:');
        console.log($scope.backgrounds);

        angular.forEach($scope.backgrounds, function(value, key) {
            if (value.background_id > background_index) {
                background_index = value.background_id;
            }
        });
    });

    /* SPRITES */
    $scope.sprite_view_URL = '';
    $scope.sprite_shape_view_URL = '';
    $scope.sprite_image_view_URL = '';

    $scope.sprite_image_index = 0;

    $scope.current_sprite = null;

    $scope.selected_sprite_shape = {
        shape: ''
    };

    $scope.sprite_shape_options = [
        'Circle', 'Rectangle'
    ];

    $scope.newSprite = function() {
        sprite_index++;

        $scope.sprite_view_URL = 'views/sprites/sprite_detail.html';

        var new_sprite = {
            sprite_id: sprite_index,
            sprite_name: 'New sprite',
            images: {
                image: []
            },
            speed: 1,
            offset: {
                x: 0,
                y: 0
            },
            shape: {}
        };

        $scope.current_sprite = new_sprite;
    };

    $scope.saveSprite = function() {
        if ($scope.selected_sprite_shape.shape === '') {
            $.pnotify({
                title: 'Sprite Not Saved',
                text: 'Your sprite has not been saved. You must choose a sprite shape.',
                type: 'error'
            });

            return;
        }

        if ($scope.current_sprite.images.image.length === 0) {
            $.pnotify({
                title: 'Sprite Not Saved',
                text: 'Your sprite has not been saved. You must add at least one image.',
                type: 'error'
            });

            return;
        }

        $scope.sprites.push($scope.current_sprite);
        $scope.current_sprite = null;
        $scope.sprite_view_URL = '';

        $scope.sprite_shape_view_URL = '';
        $scope.selected_sprite_shape = {
            shape: ''
        };

        $.pnotify({
            title: 'Sprite Saved',
            text: 'Your sprite has been saved. Select it from the list to use it in your game.',
            type: 'success'
        });
    };

    $scope.deleteSprite = function() {
        $scope.current_sprite = null;
        $scope.sprite_view_URL = '';

        $scope.sprite_shape_view_URL = '';
        $scope.selected_sprite_shape = {
            shape: ''
        };

        background_index--;

        $.pnotify({
            title: 'Sprite Deleted',
            text: 'Your new sprite has been deleted.',
            type: 'info'
        });
    };

    $scope.selectedSpriteShapeChanged = function() {
        if ($scope.selected_sprite_shape.shape === 'Circle') {
            $scope.current_sprite.shape = {
                circle: {
                    radius: ''
                }
            };

            $scope.sprite_shape_view_URL = 'views/shapes/sprite_circle.html';
        }
        else if ($scope.selected_sprite_shape.shape === 'Rectangle') {
            $scope.current_sprite.shape = {
                rectangle: {
                    size: {
                        width: '',
                        height: ''
                    }
                }
            };

            $scope.sprite_shape_view_URL = 'views/shapes/sprite_rectangle.html';
        }
        else {
            $scope.sprite_shape_view_URL = '';
        }
    };

    $scope.addSpriteImage = function() {
        $scope.sprite_image_view_URL = 'views/sprites/new_sprite_image.html';

        $scope.sprite_image_index = $scope.current_sprite.images.image.length;

        var new_sprite_image = '/images/image_name.png';

        $scope.current_sprite.images.image.push(new_sprite_image);
    };

    $scope.deleteSpriteImage = function() {
        $scope.sprite_image_view_URL = '';

        $scope.current_sprite.images.image.splice($scope.sprite_image_index, 1);

        $.pnotify({
            title: 'Sprite Image Deleted',
            text: 'Your new sprite image has been deleted.',
            type: 'info'
        });
    };

    $scope.saveSpriteImage = function() {
        $scope.sprite_image_view_URL = '';

        $.pnotify({
            title: 'Sprite Image Saved',
            text: 'Your new sprite image has been saved.',
            type: 'success'
        });
    };

    /* BACKGROUNDS */
    $scope.background_view_URL = '';

    $scope.position_type_options = [
        'tiled', 'fill', 'centre'
    ];

    $scope.current_background = null;

    $scope.newBackground = function() {
        background_index++;

        $scope.background_view_URL = 'views/background_detail.html';

        var new_background = {
            background_id: background_index,
            background_name: 'New background',
            image: '/images/background_name.png',
            speed: 1,
            position_type: ''
        };

        $scope.current_background = new_background;
    };

    $scope.saveBackground = function() {
        $scope.backgrounds.push($scope.current_background);
        $scope.current_background = null;
        $scope.background_view_URL = '';

        $.pnotify({
            title: 'Background Saved',
            text: 'Your background has been saved. Select it from the list to use it in your game.',
            type: 'success'
        });
    };

    $scope.deleteBackground = function() {
        $scope.current_background = null;
        $scope.background_view_URL = '';

        background_index--;

        $.pnotify({
            title: 'Background Deleted',
            text: 'Your new background has been deleted.',
            type: 'info'
        });
    };
}

'use strict';

/* Controllers */

function LevelsController($scope, $location, CanvasService, GameService, ConfigCompletionService, SaveXML) {
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

    /* LEVELS */
    $scope.current_level = null;

    $scope.createNewLevel = function () {
        level_index++;

        var new_level = {
            level_id: level_index,
            level_name: "Level " + level_index,
            background_id: '',
            objects: {
                object: []
            },
            events: {
                event: []
            }
        };

        $scope.game.levels.level.push(new_level);

        $scope.current_level = $scope.game.levels.level[level_index];
    };

    $scope.newLevel = function () {
        console.log('now creating new level');

        //$scope.scene_objects = [];
        scene_index = 0;
        event_index = 0;

        $('.scene').empty();
        $scope.view_URL = '';

        $scope.createNewLevel();
    };

    $scope.game.levels.level = [];
    $scope.createNewLevel();

    /* EVENTS */
    $scope.event_view_URL = '';
    $scope.new_action_control_URL = '';

    $scope.events = {
        event_type_views: [
            {name: 'Collision', partial: 'views/events/collision.html'},
            {name: 'Boundary', partial: 'views/events/boundary.html'},
            {name: 'Timer', partial: 'views/events/timer.html'},
            {name: 'Input', partial: 'views/events/input.html'}
        ],
        event_types: {
            collision: {
                object_id1: '',
                object_id2: '',
                allow_overlap: 'no'
            },
            boundary: {
                object_id: '',
                level_id: '',
                edges: '',
                allow_overlap: 'no'
            },
            timer: {
                length: '',
                repeat: 'no'
            },
            input: {
                player_id: '',
                value: {
                    x: '',
                    y: ''
                },
                type: ''
            }
        },
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

    $scope.selectedEventChanged = function () {
        console.log('selected_event_type:');
        console.log($scope.selected_event_type.event_type);

        $scope.new_action_control_URL = 'views/actions/new_action_control.html';

        if ($scope.selected_event_type.event_type != null) {
            if ($scope.selected_event_type.event_type.name === 'Collision') {
                $scope.current_event.event_type = {
                    collision: $scope.events.event_types.collision
                }
            }
            else if ($scope.selected_event_type.event_type.name === 'Boundary') {
                $scope.current_event.event_type = {
                    boundary: $scope.events.event_types.boundary
                }

                $scope.current_event.event_type.boundary.level_id = level_index;
            }
            else if ($scope.selected_event_type.event_type.name === 'Timer') {
                $scope.current_event.event_type = {
                    timer: $scope.events.event_types.timer
                }
            }
            else if ($scope.selected_event_type.event_type.name === 'Input') {
                $scope.current_event.event_type = {
                    input: $scope.events.event_types.input
                }
            }
        }
        else {
            $scope.new_action_control_URL = '';
        }
    };

    $scope.current_event = null;

    $scope.setCurrentEvent = function (index) {
        $scope.current_event = $scope.game.levels.level[level_index].events.event[index];
    };

    $scope.newEvent = function () {
        event_index++;

        $scope.event_view_URL = 'views/event_detail.html';

        var new_event = {
            event_id: event_index,
            event_type: {},
            actions: {
                action: []
            }
        };

        $scope.game.levels.level[level_index].events.event.push(new_event);

        $scope.setCurrentEvent(event_index);
    };

    $scope.saveEvent = function () {
        $scope.event_view_URL = '';
        $scope.new_action_control_URL = '';

        $scope.selected_event_type = {
            event_type: null
        };
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
        action_types: {
            change_sprite: {
                object_id: '',
                sprite_id: ''
            },
            change_score: {
                player_id: '',
                value: '',
                type: ''
            },
            change_component: {
                object_id: '',
                value: {
                    x: '',
                    y: ''
                },
                component: '',
                type: ''
            },
            change_level: {
                level_id: ''
            },
            reflect: {
                object_id: '',
                surface_object_id: '',
                surface_level_id: ''
            },
            input: {
                object_id: '',
                player_id: '',
                type: ''
            }
        },
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

    $scope.selectedActionChanged = function () {
        console.log('selected_action_type:');
        console.log($scope.selected_action_type.action_type);

        if ($scope.selected_action_type.action_type != null) {
            if ($scope.selected_action_type.action_type.name === 'Change sprite') {
                $scope.current_action.action_type = {
                    change_sprite: $scope.actions.action_types.change_sprite
                }
            }
            else if ($scope.selected_action_type.action_type.name === 'Change score') {
                $scope.current_action.action_type = {
                    change_score: $scope.actions.action_types.change_score
                }
            }
            else if ($scope.selected_action_type.action_type.name === 'Change component') {
                $scope.current_action.action_type = {
                    change_component: $scope.actions.action_types.change_component
                }
            }
            else if ($scope.selected_action_type.action_type.name === 'Change level') {
                $scope.current_action.action_type = {
                    change_level: $scope.actions.action_types.change_level
                }
            }
            else if ($scope.selected_action_type.action_type.name === 'Reflect') {
                $scope.current_action.action_type = {
                    reflect: $scope.actions.action_types.reflect
                }
            }
            else if ($scope.selected_action_type.action_type.name === 'Input') {
                $scope.current_action.action_type = {
                    input: $scope.actions.action_types.input
                }
            }
        }
        else {
            $scope.action_controls_URL = '';
        }
    };

    $scope.current_action = null;

    $scope.setCurrentAction = function (index) {
        $scope.current_action = $scope.game.levels.level[level_index].events.event[event_index].actions.action[index];
    };

    $scope.newAction = function () {
        action_index++;

        $scope.action_view_URL = 'views/action_detail.html';
        $scope.action_controls_URL = 'views/actions/action_controls.html';

        var new_action = {
            action_id: action_index,
            action_type: {}
        };

        $scope.game.levels.level[level_index].events.event[event_index].actions.action.push(new_action);

        $scope.setCurrentAction(action_index);
    };

    $scope.saveAction = function () {
        $scope.action_view_URL = '';
        $scope.action_controls_URL = '';

        $scope.selected_action_type = {
            action_type: null
        };
    };

    /* SCENE ITEMS */
    //$scope.scene_objects = [];
    $scope.view_URL = '';
    $scope.selected_item = null;

    $scope.setSelectedItem = function (index) {
        $scope.selected_item = $scope.current_level.objects.object[index];
    };

    $scope.clicked = function (index) {
        console.log('scene item clicked');
        console.log(index);

        $('.scene .scene-object-container').removeClass('selected');
        $('.scene img[data-index="' + index + '"]').closest('.scene-object-container').addClass('selected');

        $scope.setSelectedItem(index);
        $scope.view_URL = 'views/ball_detail.html';

        console.log('game:');
        console.log($scope.game);
    };

    /* GAME */
    $scope.saveGame = function () {
        console.log('now saving and POSTing game');

        var game_levels = {
            levels: {}
        };
        game_levels.levels = $scope.game.levels;

        var form_data_xml = {
            data: json2xml(game_levels),
            file_name: 'levels.xml'
        };

        /*$http({
         method: 'POST',
         url: '/LevelsServlet',
         data: form_data_xml.data
         });*/

        SaveXML.write(form_data_xml);
    };

    /*ListService.getObjects(function (objects) {
     $scope.objects = objects;
     });*/

    $scope.objects = $scope.game.setup.sprites.sprite;

    $scope.determineOrientation = function (item) {
        if (item.shape.radius) {
            return 'landscape';
        }
        else {
            if (item.shape.width >= item.shape.height) {
                return 'landscape';
            }
            else {
                return 'portrait';
            }
        }
    };

    console.log('game at end of levels controller:');
    console.log($scope.game);
}

function ConfigController($scope, $location, GameService, ListService, CanvasService, ConfigCompletionService, SaveXML) {
    $scope.game = GameService.game;
    $scope.canvas = CanvasService.canvas;
    $scope.config_completed = ConfigCompletionService.config_completed;

    /*$scope.canvas_size_options = [
     {
     name: '1920x1080',
     width: 1920,
     height: 1080,
     multiplier: 2
     },
     {
     name: '640x480',
     width: 640,
     height: 480,
     multiplier: 1
     }
     ];

     $scope.selected_canvas_size = {
     size: null
     };

     $scope.canvasSizeChanged = function () {
     console.log('canvas:');
     console.log($scope.canvas);

     if ($scope.selected_canvas_size.size != null) {
     if ($scope.selected_canvas_size.size.name === '1920x1080') {
     $scope.canvas.size = $scope.canvas_size_options[0];
     }
     else if ($scope.selected_canvas_size.size.name === '640x480') {
     $scope.canvas.size = $scope.canvas_size_options[1];
     }
     }
     }; */

    ListService.getObjects(function (objects) {
        $scope.sprites = objects;
    });

    console.log('initial game:');
    console.log($scope.game);

    $scope.saveConfig = function () {
        var sprite_index = 0;
        level_index = -1;
        $scope.game.setup.sprites.sprite = [];

        $.each($('.ui-selected'), function () {
            var index = $(this).attr('data-index');

            var new_sprite = {
                sprite_id: sprite_index,
                sprite_name: $scope.sprites[index].sprite_name,
                image: $scope.sprites[index].image,
                speed: $scope.sprites[index].speed,
                offset: {
                    x: $scope.sprites[index].offset.x,
                    y: $scope.sprites[index].offset.y
                },
                shape: {
                    shape_type: $scope.sprites[index].shape.shape_type
                }
            };

            if (new_sprite.shape.shape_type === 'circle') {
                new_sprite.shape.radius = $scope.sprites[index].shape.radius;
            }
            else if (new_sprite.shape.shape_type === 'rectangle') {
                new_sprite.shape.width = $scope.sprites[index].shape.width;
                new_sprite.shape.height = $scope.sprites[index].shape.height;
            }

            $scope.game.setup.sprites.sprite.push(new_sprite);

            sprite_index++;
        });

        var setup = {};
        setup.setup = $scope.game.setup;

        console.log('game.setup:');
        console.log($scope.game.setup);

        var form_data_xml = {
            data: json2xml(setup),
            file_name: 'game.xml'
        };

        /*var form_data_json = {
         setup: $scope.game.setup
         }*/

        /*$http({
         method: 'POST',
         url: '/GameConfigServlet',
         //data: form_data_json
         data: form_data_xml.data
         });*/

        SaveXML.write(form_data_xml);

        console.log('canvas at end of save:');
        console.log(CanvasService.canvas);

        $scope.config_completed.status = true;
        $location.path('levels');
    }

    $scope.determineOrientation = function (item) {
        if (item.shape.radius) {
            return 'landscape';
        }
        else {
            if (item.shape.width >= item.shape.height) {
                return 'landscape';
            }
            else {
                return 'portrait';
            }
        }
    };
}

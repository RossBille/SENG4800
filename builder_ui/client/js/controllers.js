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
    $scope.event_selected_control_URL = '';

    $scope.events = {
        event_type_views: [
            {name: 'Collision', partial: 'views/events/collision.html'},
            {name: 'Boundary', partial: 'views/events/boundary.html'},
            {name: 'Timer', partial: 'views/events/timer.html'},
            {name: 'Input', partial: 'views/events/input.html'}
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

    $scope.selectedEventChanged = function () {
        console.log('selected_event_type:');
        console.log($scope.selected_event_type.event_type);

        if ($scope.selected_event_type.event_type != null) {
            $scope.event_selected_control_URL = 'views/actions/event_selected_control.html';

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
            $scope.event_selected_control_URL = '';
        }
    };

    $scope.current_event = null;

    $scope.newEvent = function () {
        event_index++;
        action_index = -1;

        $scope.saveEvent();
        $scope.saveAction();

        $scope.event_view_URL = 'views/event_detail.html';

        var new_event = {
            event_id: event_index,
            event_type: {},
            actions: {
                action: []
            }
        };

        $scope.game.levels.level[level_index].events.event.push(new_event);

        $scope.current_event = $scope.current_level.events.event[event_index];
    };

    $scope.saveEvent = function () {
        $scope.event_view_URL = '';
        $scope.event_selected_control_URL = '';

        $scope.selected_event_type = {
            event_type: null
        };
    };

    $scope.cancelEvent = function () {
        $scope.saveEvent();

        $scope.current_level.events.event.splice(event_index, 1);

        event_index--;
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

    $scope.selectedActionChanged = function () {
        console.log('selected_action_type:');
        console.log($scope.selected_action_type.action_type);

        if ($scope.selected_action_type.action_type != null) {
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

    $scope.newAction = function () {
        action_index++;
        $scope.action_view_URL = 'views/action_detail.html';

        var new_action = {
            action_id: action_index,
            action_type: {}
        };

        $scope.current_event.actions.action.push(new_action);

        $scope.current_action = $scope.current_event.actions.action[action_index];
    };

    $scope.saveAction = function () {
        $scope.action_view_URL = '';
        $scope.action_controls_URL = '';

        $scope.selected_action_type = {
            action_type: null
        };

        $scope.selected_reflect_option = {
            reflect_option: null
        };
    };

    $scope.cancelAction = function () {
        $scope.saveAction();

        $scope.current_event.actions.action.splice(action_index, 1);

        action_index--;
    };

    /* REFLECT ACTION */
    $scope.reflect_option_views = [
        {name: 'Object', partial: 'views/actions/reflect_object.html'},
        {name: 'Level', partial: ''}
    ]

    $scope.selected_reflect_option = {
        reflect_option: null
    };

    $scope.selectedReflectOptionChanged = function () {
        console.log('selected_reflect_option:');
        console.log($scope.selected_reflect_option.reflect_option);

        if ($scope.selected_reflect_option.reflect_option != null) {
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
    $scope.selected_item = null;

    $scope.clicked = function (index) {
        console.log('scene item clicked');
        console.log(index);

        $('.scene .scene-object-container').removeClass('selected');
        $('.scene img[data-index="' + index + '"]').closest('.scene-object-container').addClass('selected');

        $scope.selected_item = $scope.current_level.objects.object[index];
        $scope.view_URL = 'views/object_detail.html';

        if ($scope.selected_item.object_shape.circle) {
            $scope.object_shape_view_URL = 'views/shapes/object_circle.html';
        }
        else {
            $scope.object_shape_view_URL = 'views/shapes/object_rectangle.html';
        }
    };

    $scope.getObjectShape = function () {
        if ($scope.selected_item.object_shape.circle) {
            return 'Circle';
        }
        else {
            return 'Rectangle';
        }
    };

    /* GAME */
    $scope.saveGame = function () {
        console.log('now saving and sending game to server');

        var game_levels = {
            levels: $scope.game.levels
        };

        var form_data_xml = {
            data: json2xml(game_levels),
            file_name: 'levels.xml'
        };

        SaveXML.write(form_data_xml);
    };

    $scope.objects = $scope.game.setup.sprites.sprite;

    $scope.determineOrientation = function (item) {
        if (item.shape.circle) {
            return 'landscape';
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

    $scope.saveConfig = function () {
        var sprite_index = 0;
        var background_index = 0;
        level_index = -1;
        $scope.game.setup.sprites.sprite = [];

        $.each($('#sprites .ui-selected'), function () {
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

        $.each($('#backgrounds .ui-selected'), function () {
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
        $location.path('levels');
    }

    $scope.determineOrientation = function (item) {
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
    ListService.getObjects(function (data) {
        $scope.sprites = data.objects.Objects;

        console.log('sprites:');
        console.log($scope.sprites);

        angular.forEach($scope.sprites, function (value, key) {
            if(value.sprite_id > sprite_index) {
                sprite_index = value.sprite_id;
            }
        });

        $scope.backgrounds = data.backgrounds.Backgrounds;

        console.log('backgrounds:');
        console.log($scope.backgrounds);

        angular.forEach($scope.backgrounds, function (value, key) {
            if(value.background_id > background_index) {
                background_index = value.background_id;
            }
        });
    });

    /* SPRITES */
    $scope.sprite_view_URL = '';
    $scope.sprite_shape_view_URL = '';
    $scope.current_sprite = null;

    $scope.selected_sprite_shape = {
        shape: ''
    };

    $scope.sprite_shape_options = [
        'Circle', 'Rectangle'
    ];

    $scope.newSprite = function () {
        sprite_index++;

        $scope.sprite_view_URL = 'views/sprite_detail.html';

        var new_sprite = {
            sprite_id: sprite_index,
            sprite_name: 'New sprite',
            image: '/images/file_name.jpg',
            speed: 1,
            offset: {
                x: 0,
                y: 0
            },
            shape: {}
        };

        $scope.current_sprite = new_sprite;
    };

    $scope.saveSprite = function () {
        $scope.sprites.push($scope.current_sprite);
        $scope.current_sprite = null;
        $scope.sprite_view_URL = '';
    };

    $scope.cancelSprite = function () {
        $scope.current_sprite = null;
        $scope.sprite_view_URL = '';

        background_index--;
    };

    $scope.selectedSpriteShapeChanged = function () {
        if ($scope.selected_sprite_shape.shape === 'Circle') {
            $scope.current_sprite.shape = {
                circle: {
                    radius: ''
                }
            };

            $scope.sprite_shape_view_URL = 'views/shapes/sprite_circle.html';
        }
        else if ($scope.selected_sprite_shape.shape === 'Rectangle'){
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

    /* BACKGROUNDS */
    $scope.background_view_URL = '';

    $scope.position_type_options = [
        'tiled', 'stretch', 'fill', 'centre'
    ];

    $scope.current_background = null;

    $scope.newBackground = function () {
        background_index++;

        $scope.background_view_URL = 'views/background_detail.html';

        var new_background = {
            background_id: background_index,
            background_name: 'New background',
            image: '/images/file_name.jpg',
            speed: 1,
            position_type: ''
        };

        $scope.current_background = new_background;
    };

    $scope.saveBackground = function () {
        $scope.backgrounds.push($scope.current_background);
        $scope.current_background = null;
        $scope.background_view_URL = '';
    };

    $scope.cancelBackground = function () {
        $scope.current_background = null;
        $scope.background_view_URL = '';

        background_index--;
    };
}

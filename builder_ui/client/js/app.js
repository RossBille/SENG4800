'use strict';

/* App Module */

// Bootstrap the Application
//var App = angular.module('App', []);

var App = angular.module('App', []).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/config', {templateUrl: 'views/config.html', controller: ConfigController}).
            when('/levels', {templateUrl: 'views/levels.html', controller: LevelsController}).
            otherwise({redirectTo: '/config'});
    }]);

App.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

// Instantiate global variables
var scene_index = 0;
var level_index = -1;
var event_index = 0;
var action_index = -1;
var background_index = -1;
var sprite_index = -1;

// Setup Pines notifications
$.pnotify.defaults.styling = "jqueryui";
$.pnotify.defaults.history = false;
$.pnotify.defaults.delay = 8000;
$.pnotify.defaults.animate_speed = "normal";

'use strict';

/* App Module */

// Bootstrap the Application
//var App = angular.module('App', []);

var App = angular.module('App', []).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when('/config', {templateUrl: 'views/config.html', controller: ConfigController}).
            when('/scenes', {templateUrl: 'views/scenes.html', controller: SceneController}).
            otherwise({redirectTo: '/config'});
    }]);

// Instantiate global variables
var scene_index = 0;
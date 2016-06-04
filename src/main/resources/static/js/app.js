angular.module('app', ['ngRoute', 'ngMaterial', 'md.data.table', 'ngMdIcons', 'ngMessages', 'auth', 'home', 'navigation', 'admin', 'profile', 'ngCookies', 'employees', 'evidence']).config(function ($routeProvider) {

    $routeProvider.when('/', {
        templateUrl: '/partials/home.html',
        controller: 'home',
        controllerAs: 'controller'
    }).when('/login', {
        templateUrl: '/partials/login.html',
        controller: 'navigation',
        controllerAs: 'controller'
    }).when('/home', {
        templateUrl: '/partials/home.html',
        controller: 'home',
        controllerAs: 'controller'
    }).when('/admin', {
        templateUrl: '/partials/admin.html',
        controller: 'admin',
        controllerAs: 'controller',
        resolve: {
            'auth': function (AuthService) {
                return AuthService.authorize('ADMIN');
            }
        }
    }).when('/profile', {
        templateUrl: '/partials/profile.html',
        controller: 'profile',
        controllerAs: 'controller'
    }).when('/employees', {
        templateUrl: '/partials/employees.html',
        controller: 'employees',
        controllerAs: 'controller'
    }).when('/evidence', {
        templateUrl: '/partials/worktimeevidence.html',
        controller: 'evidence',
        controllerAs: 'controller'
    }).otherwise('/');

}).config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';

    $mdThemingProvider.theme('default')
        .primaryPalette('cyan',
        {

        })
        .warnPalette('red', {
            'default': '900'
        });
}]).run(function (auth) {

    auth.init();

}).factory('AuthService',['$location', '$http', '$q', '$rootScope', function ($location, $http, $q, $rootScope) {
    return {
        authorize: function (allowedAuthorization) {

            if($rootScope.authority === allowedAuthorization){
                return true;
            } else {
                return $q.reject('Not Authenticated');
            }

        }
    }
}]);

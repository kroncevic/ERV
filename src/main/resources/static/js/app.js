angular.module('app', ['ngRoute', 'ngMaterial', 'md.data.table', 'ngMdIcons', 'ngMessages', 'auth', 'home', 'navigation', 'admin', 'profile']).config(function ($routeProvider, $httpProvider) {

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
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';

    $mdThemingProvider.theme('default')
        .primaryPalette('teal',
        {})
        .warnPalette('red', {
            'default': '900'
        });
}]).run(function (auth) {

    auth.init('/', '/login');

}).factory('AuthService',['$location', '$http', function ($location, $http) {
    return {
        authorize: function (allowedAuthorization) {

            var promise = $http.get('rest/user/');

            promise.success(function(data) {
                var authorization = data.authorities[0].authority;
                if (authorization === allowedAuthorization) {
                    return true;
                } else {
                    $location('/home');
                    throw 302;
                }
            });
        }
    }
}]);

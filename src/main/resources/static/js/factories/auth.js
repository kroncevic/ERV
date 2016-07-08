angular.module('auth', []).factory(
    'auth',

    function ($rootScope, $http, $location, $window, $cookies) {

        enter = function () {
           if ($location.path() != auth.loginPath) {
                auth.path = $location.path();
                if (!auth.authenticated) {
                    $location.path(auth.loginPath);
                }
            } else if ($location.path() == auth.loginPath && auth.authenticated) {
                $location.path(auth.homePath);
            }


        };

        var auth = {

            authenticated: false,

            loginPath: '/login',
            homePath: '/home',
            path: $location.path(),

            authenticate: function (credentials, callback) {

                $http.post('rest/auth', {
                    username: credentials.username,
                    password: credentials.password
                }).then(function (response) {
                    if (response.data.token) {
                        if ($rootScope.rememberMe) {
                            $window.localStorage["token"] = response.data.token;
                        } else {
                            $cookies.put('token', response.data.token);
                        }

                        $http.defaults.headers.common.Authorization = response.data.token;

                        $http.get('rest/user/').then(function (result) {
                            $rootScope.authority = result.data.authorities[0].authority;
                            $rootScope.username = result.data.principal;
                        });

                        auth.authenticated = true;
                    } else {
                        auth.authenticated = false;
                    }
                    callback && callback(auth.authenticated);
                    $location.path("/home");
                }, function () {
                    auth.authenticated = false;
                    callback && callback(false);
                });

            },

            clear: function () {
                $location.path(auth.loginPath);
                auth.authenticated = false;
                $window.localStorage["token"] = null;
                $cookies.remove('token');
                $rootScope.authority = null;
            },

            init: function () {
                $rootScope.rememberMe = false;
                var token = $window.localStorage["token"];

                if (token == null || token == 'null') {
                    token = $cookies.get('token');
                }

                auth.authenticated = token != null && token != 'null';
                if (auth.authenticated) {
                    $http.defaults.headers.common.Authorization = token;
                    $http.get('rest/user/').then(function (result) {
                        $rootScope.authority = result.data.authorities[0].authority;
                        $rootScope.username = result.data.principal;
                    });
                }

                $rootScope.$on('$routeChangeStart', function () {
                    enter();
                });

                $rootScope.$on('$routeChangeError', function (event, current, previous, rejection) {
                    if (rejection === 'Not Authenticated') {
                        $location.path('/home');
                    }
                });

            }
        };

        return auth;

    });

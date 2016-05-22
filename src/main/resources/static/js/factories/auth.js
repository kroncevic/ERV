angular.module('auth', []).factory(
		'auth',

		function($rootScope, $http, $location) {

			enter = function() {
				if ($location.path() != auth.loginPath) {
					auth.path = $location.path();
					if (!auth.authenticated) {
						$location.path(auth.loginPath);
					}
				} else if($location.path() == auth.loginPath && auth.authenticated){
					$location.path(auth.homePath);
				}
			};

			var auth = {

				authenticated : false,

				loginPath : '/login',
				homePath : '/home',
				path : $location.path(),

				authenticate : function(credentials, callback) {

					var headers = credentials && credentials.username ? {
						authorization : "Basic "
								+ btoa(credentials.username + ":"
										+ credentials.password)
					} : {};

					$http.get('rest/user', {
						headers : headers
					}).then(function(response) {
						if (response.data.name) {
							auth.authenticated = true;
						} else {
							auth.authenticated = false;
						}
						callback && callback(auth.authenticated);
						$location.path("/home");
					}, function() {
						auth.authenticated = false;
						callback && callback(false);
					});

				},

				clear : function() {
					$location.path(auth.loginPath);
					auth.authenticated = false;
					$http.post("rest/logout", {}).then(function() {
						console.log("Logout succeeded");
					}, function() {
						console.log("Logout failed");
					});
				},

				init : function(homePath, loginPath) {

					auth.homePath = homePath;
					auth.loginPath = loginPath;

					auth.authenticate({}, function(authenticated) {
						if (authenticated) {
							$location.path(auth.path);
						}
					});

					$rootScope.$on('$routeChangeStart', function() {
						enter();
					});



				}

			};

			return auth;

		});

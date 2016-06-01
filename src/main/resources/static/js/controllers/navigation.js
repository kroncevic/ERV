angular.module('navigation', ['ngRoute', 'auth'])
	.controller(
		'navigation', ['$scope', '$route', 'auth', '$timeout', '$mdSidenav', '$location',

		function($scope, $route, auth, $timeout, $mdSidenav, $location) {

			var self = this;

			var errorMsg = null;

			$scope.sharedData = {};
			$scope.sharedData.title = null;
			$scope.sharedData.authority = null;

			self.credentials = {};

			self.authenticated = function() {
				return auth.authenticated;
			};

			$scope.toggleLeft = buildDelayedToggler('left');

			self.navHome = function navHome(){
				$location.path('/home');
			};

			self.navAdmin = function navAdmin(){
				$location.path('/admin');
			};

			self.navProfile = function navProfile(){
				$location.path('/profile');
			};

			self.navEmployees = function navEmployees(){
				$location.path('/employees');
			};

			function buildDelayedToggler(navID) {
				return debounce(function () {
					$mdSidenav(navID)
						.toggle();
				}, 200);
			}

			function debounce(func, wait, context) {
				var timer;

				return function debounced() {
					var context = $scope,
						args = Array.prototype.slice.call(arguments);
					$timeout.cancel(timer);
					timer = $timeout(function () {
						timer = undefined;
						func.apply(context, args);
					}, wait || 10);
				};
			}

			$scope.close = function () {
				$mdSidenav('left').close();
			};

			self.login = function() {

				auth.authenticate(self.credentials, function(authenticated) {
					if (authenticated) {
						console.log("Login succeeded");
						self.error = false;
						errorMsg = null;
					} else {
						console.log("Login failed");
						self.error = true;
						self.errorMsg = 'Login failed';
					}
				});

			};

			self.logout = auth.clear;

		}]);

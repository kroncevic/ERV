angular.module('home', []).controller('home', function($http, $scope) {
	var self = this;
	$scope.sharedData.title = 'Naslovnica';
	$http.get('rest/user/').then(function(response) {
		self.user = response.data.name;
		self.authorization = response.data.authorities[0].authority;
	});
});

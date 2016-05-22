angular.module('admin', []).controller('admin', function($http, $scope) {
	var self = this;
	$scope.sharedData.title = 'Admin';
	$http.get('rest/user/').then(function(response) {
		self.user = response.data.name;
		self.authorization = response.data.authorities[0].authority;
	});
});

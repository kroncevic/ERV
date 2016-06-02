angular.module('profile', []).controller('profile', function($http, $scope) {
	var self = this;
	$scope.sharedData.title = 'Profil';
	$http.get('rest/user/').then(function(response) {
		self.user = response.data.name;
		self.authorization = response.data.authorities[0].authority;
	});
});

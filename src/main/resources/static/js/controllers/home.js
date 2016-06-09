angular.module('home', []).controller('home', function($http, $scope) {
	var self = this;
	$scope.sharedData.title = 'Naslovnica';

	$scope.query = {
		order: '-timestamp'
	};

	$scope.logOrder = function (order) {
		console.log('order: ', order);
	};

	$scope.promise = $http.get('/rest/evidence/today').success(function (data) {
		for (i = 0; i < data.length; i++) {
			data[i].timestamp = new Date(data[i].timestamp);
		}
		$scope.evidences = data;
	});

});

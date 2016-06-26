angular.module('home', []).controller('home', function($http, $scope) {
	var self = this;
	$scope.sharedData.title = 'Naslovnica';

	$scope.query = {
		order: '-signInTimestamp'
	};

	$scope.logOrder = function (order) {
		console.log('order: ', order);
	};

	$scope.promise = $http.get('/rest/evidence/today').success(function (data) {
		$scope.evidences = data;
	});

});

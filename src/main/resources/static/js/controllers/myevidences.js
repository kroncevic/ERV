angular.module('myEvidences', []).controller('myEvidences', function($http, $scope, $rootScope) {

	$scope.sharedData.title = 'Moje evidencije';

	$scope.query = {
		order: '-signInTimestamp'
	};

	$scope.logOrder = function (order) {
		console.log('order: ', order);
	};

	$scope.promise = $http.get('/rest/evidence/' + $rootScope.username).success(function (data) {
		for (i = 0; i < data.length; i++) {
			if (data[i].timestamp != '-') {
				data[i].timestamp = new Date(data[i].timestamp);
			}
		}
		$scope.evidences = data;
	});


});

angular.module('myEvidences', []).controller('myEvidences', function($http, $scope, $rootScope) {

	$scope.sharedData.title = 'Moje evidencije';

	$scope.query = {
		order: '-signInTimestamp'
	};

	$scope.logOrder = function (order) {
		console.log('order: ', order);
	};

	$scope.promise = $http.get('/rest/evidence/' + $rootScope.username).success(function (data) {
		$scope.evidences = data;
	});


});

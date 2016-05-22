angular.module('admin', []).controller('admin', function($http, $scope) {

	$scope.sharedData.title = 'Admin';

	$scope.promise = $http.get('/rest/employee/').success(function (data) {
		$scope.employees = data;
	});


});

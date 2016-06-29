angular.module('employees', []).controller('employees', function($http, $scope) {

	$scope.sharedData.title = 'Zaposlenici';

	$scope.showSearch = false;

	$scope.searchName = "";

    $http.get('rest/employee/').then(function (response) {
        $scope.employees = response.data;
    });


});

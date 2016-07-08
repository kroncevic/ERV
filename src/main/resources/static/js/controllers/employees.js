angular.module('employees', []).controller('employees', ['$http', '$scope', '$mdDialog', function($http, $scope, $mdDialog) {

	$scope.sharedData.title = 'Zaposlenici';

	$scope.showSearch = false;

	$scope.searchName = "";

    $http.get('rest/employee/').then(function (response) {
        $scope.employees = response.data;
    });

    $scope.showEmployeeProfile = function (employee) {

        $mdDialog.show({
            controller: EmployeeProfile,
            controllerAs: 'controller',
            templateUrl: 'partials/dialogs/employeeProfile.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals:{
                employee: employee
            }
        })
    };


}]);

function EmployeeProfile($scope, $mdDialog, employee) {

    $scope.employee = employee;

    $scope.hideDialog = function () {
        $mdDialog.hide();
    };


}
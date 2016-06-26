angular.module('admin', []).controller('admin', function ($http, $scope, $q, $rootScope) {

    $scope.sharedData.title = 'Admin';

    $http.get('rest/project/').then(function (response) {
        $scope.projects = response.data;
    });

    $scope.projectResultMessage = undefined;

    $scope.selectedProject = {
        name: undefined
    };

    $scope.showProject = function () {
        return $scope.newProject === true || ($scope.selectedProject.name !== null && $scope.selectedProject.name !== '' && $scope.selectedProject.name !== undefined);
    };

    $scope.clearProject = function () {
        $scope.selectedProject = {
            name: undefined
        };

        $scope.newProject = false;
        $scope.projectResultMessage = undefined;
    };

    $scope.saveProject = function () {

        $http.post('rest/project', $scope.selectedProject)
            .then(
            function (response) {
                $scope.clearProject();
                $http.get('rest/project/').then(function (response) {
                    $scope.projects = response.data;
                });
                $scope.projectForm.name.$touched = false;
                return response.data;
            },
            function (errResponse) {
                return $q.reject(errResponse);
            }
        );

    };

    $scope.deleteProject = function () {

        $http.delete('rest/project/' + $scope.selectedProject.id)
            .then(
            function (response) {
                $scope.clearProject();
                $http.get('rest/project/').then(function (response) {
                    $scope.projects = response.data;
                });
                $scope.projectForm.name.$touched = false;
                return response.data;
            },
            function (errResponse) {
                $scope.projectResultMessage = 'Brisanje nije uspjelo';
                return $q.reject(errResponse);
            }
        );

    };

    $scope.newProject = false;




    $http.get('rest/employee/').then(function (response) {
        $scope.employees = response.data;
    });

    $scope.employeeResultMessage = undefined;

    $scope.selectedEmployee = {
        username: undefined
    };

    $scope.showEmployee = function () {
        return $scope.newEmployee === true || ($scope.selectedEmployee.username !== null && $scope.selectedEmployee.username !== '' && $scope.selectedEmployee.username !== undefined);
    };

    $scope.clearEmployee = function () {
        $scope.selectedEmployee = {
            username: undefined
        };

        $scope.newEmployee = false;
        $scope.employeeResultMessage = undefined;
        $scope.tempBirthDate = new Date();
        $scope.tempEmploymentDate = new Date();
    };


    $scope.tempBirthDate = new Date();
    $scope.tempEmploymentDate = new Date();


    $scope.setEmployeeDates = function (){
      $scope.tempBirthDate = new Date($scope.selectedEmployee.birthDate);
        $scope.tempEmploymentDate = new Date($scope.selectedEmployee.employmentDate);
    };

    $scope.saveEmployee = function () {

        var months = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
        var padding = "00";

        var birthDateDay = $scope.tempBirthDate.getDate();
        var birthDay = padding.substring(0, padding.length - birthDateDay.length) + birthDateDay;
        $scope.selectedEmployee.birthDate =  $scope.tempBirthDate.getFullYear() + months[$scope.tempBirthDate.getMonth()] + birthDay + '000000';

        var employmentDateDay = $scope.tempEmploymentDate.getDate();
        var employmentDay = padding.substring(0, padding.length - employmentDateDay.length) + employmentDateDay;
        $scope.selectedEmployee.employmentDate =  $scope.tempEmploymentDate.getFullYear() + months[$scope.tempEmploymentDate.getMonth()] + employmentDay + '000000';

        $http.post('rest/employee', $scope.selectedEmployee)
            .then(
            function (response) {
                $scope.clearEmployee();
                $http.get('rest/employee/').then(function (response) {
                    $scope.employees = response.data;
                });
                $scope.employeeForm.username.$touched = false;
                return response.data;
            },
            function (errResponse) {
                return $q.reject(errResponse);
            }
        );

    };

    $scope.newEmployee = false;

});

angular.module('admin', []).controller('admin', function ($http, $scope, $q, $rootScope) {

    $scope.sharedData.title = 'Admin';

    $http.get('rest/businessUnit/').then(function (response) {
        $scope.businessUnits = response.data;
    });

    $scope.selectedBusinessUnit = {
        name: undefined
    };

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

    $scope.setEmployeeInfo = function () {
        $scope.tempBirthDate = new Date($scope.selectedEmployee.birthDate);
        $scope.tempEmploymentDate = new Date($scope.selectedEmployee.employmentDate);
        $scope.selectedBusinessUnit = $scope.selectedEmployee.businessUnit;
    };

    var pad = function pad(n, width, z) {
        z = z || '0';
        n = n + '';
        return n.length >= width ? n : new Array(width - n.length + 1).join(z) + n;
    };

    $scope.saveEmployee = function () {

        var months = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];

        var birthDay = pad($scope.tempBirthDate.getDate(), 2);
        $scope.selectedEmployee.birthDate = $scope.tempBirthDate.getFullYear() + months[$scope.tempBirthDate.getMonth()] + birthDay + '000000';

        var employmentDay = pad($scope.tempEmploymentDate.getDate(), 2);
        $scope.selectedEmployee.employmentDate = $scope.tempEmploymentDate.getFullYear() + months[$scope.tempEmploymentDate.getMonth()] + employmentDay + '000000';

        $scope.selectedEmployee.businessUnit = $scope.selectedBusinessUnit;

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

    $scope.showUserData = false;
    $scope.showContractData = false;
    $scope.showeducationData = false;
    $scope.showPersonalData = false;

    $scope.newEmployee = false;


    $http.get('rest/location/').then(function (response) {
        $scope.locations = response.data;
    });

    $scope.locationResultMessage = undefined;

    $scope.selectedLocation = {
        name: undefined
    };

    $scope.showLocation = function () {
        return $scope.newLocation === true || ($scope.selectedLocation.name !== null && $scope.selectedLocation.name !== '' && $scope.selectedLocation.name !== undefined);
    };

    $scope.clearLocation = function () {
        $scope.selectedLocation = {
            name: undefined
        };

        $scope.newLocation = false;
        $scope.locationResultMessage = undefined;
    };

    $scope.saveLocation = function () {

        $http.post('rest/location', $scope.selectedLocation)
            .then(
            function (response) {
                $scope.clearLocation();
                $http.get('rest/location/').then(function (response) {
                    $scope.locations = response.data;
                });
                $scope.locationForm.name.$touched = false;
                return response.data;
            },
            function (errResponse) {
                return $q.reject(errResponse);
            }
        );

    };

    $scope.deleteLocation = function () {

        $http.delete('rest/location/' + $scope.selectedLocation.id)
            .then(
            function (response) {
                $scope.clearLocation();
                $http.get('rest/location/').then(function (response) {
                    $scope.locations = response.data;
                });
                $scope.locationForm.name.$touched = false;
                return response.data;
            },
            function (errResponse) {
                $scope.locationResultMessage = 'Brisanje nije uspjelo';
                return $q.reject(errResponse);
            }
        );

    };

    $scope.newLocation = false;

});

angular.module('newEvidence', []).controller('newEvidence', function($http, $scope, $rootScope, $location, $q) {
	var self = this;
	$scope.sharedData.title = 'Evidencija radnog vremena';

	$scope.evidence = {
		username : undefined,
		type : undefined,
		location : undefined,
		project : undefined,
		uniqueId : undefined
	};

	$scope.getDatetime = new Date();

	$scope.saveEvidence = function() {
		$scope.evidence.location = $scope.selectedLocation.name ;
		$scope.evidence.project = $scope.selectedProject.name;
		$http.post('rest/evidence', $scope.evidence)
			.then(
			function(response){
				$location.path('/myEvidence');
				return response.data;
			},
			function(errResponse){
				console.error('Error while creating user');
				return $q.reject(errResponse);
			}
		);

	};


	$scope.isSignedin = undefined;

	$http.get('rest/employee/'+ $rootScope.username).then(function(response) {
		self.name = response.data.firstName + ' ' + response.data.lastName;
		$scope.evidence.username = response.data.username;
		$http.get('rest/evidence/latest/'+ $rootScope.username).then(function(response) {
			$scope.evidence.type = response.data.type;
			$scope.isSignedin = $scope.evidence.type === 'Prijava';
			if($scope.isSignedin){
				$scope.evidence.type = 'Odjava';
				$scope.evidence.location = response.data.location;
				$scope.selectedLocation.name = response.data.location;
				$scope.evidence.project = response.data.project;
				$scope.selectedProject.name = response.data.project;
				$scope.evidence.uniqueId = response.data.uniqueId;
			} else {
				$scope.evidence.type = 'Prijava';
			}
		});
	});

	$http.get('rest/project/').then(function (response) {
		$scope.projects = response.data;
	});

	$http.get('rest/location/').then(function (response) {
		$scope.locations = response.data;
	});

	$scope.selectedLocation = {
		name: undefined
	};

	$scope.selectedProject = {
		name: undefined
	};

	$scope.disableSaveEvidence = function () {
		return $scope.selectedProject.name === null || $scope.selectedProject.name === '' || $scope.selectedProject.name === undefined || $scope.selectedLocation.name === null || $scope.selectedLocation.name === '' || $scope.selectedLocation.name === undefined;
	};

});

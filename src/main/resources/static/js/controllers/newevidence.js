angular.module('newEvidence', []).controller('newEvidence', function($http, $scope, $rootScope, $location) {
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
				$scope.evidence.project = response.data.project;
				$scope.evidence.uniqueId = response.data.uniqueId;
			} else {
				$scope.evidence.type = 'Prijava';
			}
		});
	});
});

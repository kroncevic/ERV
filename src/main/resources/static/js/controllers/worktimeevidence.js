angular.module('evidence', []).controller('evidence', function($http, $scope, $rootScope, $location) {
	var self = this;
	$scope.sharedData.title = 'Evidencija radnog vremena';

	$scope.evidence = {
		username : undefined,
		type : 'Prijava',
		location : undefined,
		project : undefined
	};

	$scope.getDatetime = new Date();

	$scope.saveEvidence = function() {

		$http.post('rest/evidence', $scope.evidence)
			.then(
			function(response){
				$location.path('/home');
				return response.data;
			},
			function(errResponse){
				console.error('Error while creating user');
				return $q.reject(errResponse);
			}
		);

	};

	$http.get('rest/employee/'+ $rootScope.username).then(function(response) {
		self.name = response.data.firstName + ' ' + response.data.lastName;
		$scope.evidence.username = response.data.username;
	});
});

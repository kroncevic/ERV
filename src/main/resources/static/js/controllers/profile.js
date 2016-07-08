angular.module('profile', []).controller('profile', function($http, $scope, $rootScope) {
	
	var self = this;
	
	$scope.sharedData.title = 'Profil';
	
	$http.get('rest/user/').then(function(response) {
		
		self.user = response.data.username;
		self.authorization = response.data.authorities[0].authority;
		
		$http.get('rest/employee/'+ $rootScope.username).then(function(response) {
		self.name = response.data.firstName + ' ' + response.data.lastName;
		self.address = response.data.address + ', ' + response.data.city + ', ' + response.data.country;
		self.citizenship = response.data.citizenship;
		self.birthDate = response.data.birthDate;
		self.workingPlace = response.data.workingPlace;
		self.employmentDate = response.data.employmentDate;
		self.contract = response.data.contract;
		self.qualifications = response.data.qualifications;
		self.vocation = response.data.vocation;
		self.university = response.data.university;
		self.username = response.data.username;
		self.email = response.data.email;
		self.phoneNumber = response.data.phoneNumber;
		
		self.vacations = response.data.vacations;
		self.businessUnit = response.data.businessUnit;

	});



  });
});

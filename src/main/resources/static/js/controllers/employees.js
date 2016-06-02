angular.module('employees', []).controller('employees', function($http, $scope) {

	$scope.sharedData.title = 'Zaposlenici';

	$scope.employees = [
		{
			name: 'Valentina Kovač',
			workplace: 'prodajna predstavnica',
		},
		{
			name: 'Daniel Lovrić',
			workplace: 'prodajni predstavnik',
		},
		{
			name: 'Matea Rabić',
			workplace: 'programerka',
		},
		{
			name: 'Nikola Lalić',
			workplace: 'programer',
		},
		{
			name: 'Hrvoje Marinović',
			workplace: 'programer',
		},
		{
			name: 'Klara Dalić',
			workplace: 'programerka',
		},
		{
			name: 'Vlatko Horvat',
			workplace: 'programer',
		},
		{
			name: 'Sven Kota',
			workplace: 'programer',
		},
		{
			name: 'Vilim Čavka',
			workplace: 'mrežni administrator',
		},
		{
			name: 'Andrej Jug',
			workplace: 'mrežni administrator',
		},
		{
			name: 'Lidija Marić',
			workplace: 'tajnica',
		},
		{
			name: 'Mila Petroci',
			workplace: 'direktorica',
		},
		{
			name: 'Leo Janković',
			workplace: 'direktor',
		},
		{
			name: 'Igor Tomić',
			workplace: 'direktor',
		},
		{
			name: 'Mišo Ilić',
			workplace: 'vlasnik',
		}

	];



});

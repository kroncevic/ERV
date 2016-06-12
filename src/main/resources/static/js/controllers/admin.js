angular.module('admin', []).controller('admin', function ($http, $scope) {

    $scope.sharedData.title = 'Admin';

    $scope.query = {
        order: '-signInTimestamp'
    };

    $scope.logOrder = function (order) {
        console.log('order: ', order);
    };

    $scope.promise = $http.get('/rest/evidence/').success(function (data) {
        for (i = 0; i < data.length; i++) {
            if (data[i].timestamp != '-') {
                data[i].timestamp = new Date(data[i].timestamp);
            }
        }
        $scope.evidences = data;
    });


});

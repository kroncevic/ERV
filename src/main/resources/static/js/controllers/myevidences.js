angular.module('myEvidences', []).controller('myEvidences', function ($http, $scope, $rootScope) {

    $scope.sharedData.title = 'Moje evidencije';

    $scope.query = {
        order: '-signInTimestamp'
    };

    $scope.logOrder = function (order) {
        console.log('order: ', order);
    };

    $scope.promise = $http.get('/rest/evidence/' + $rootScope.username).success(function (data) {
        $scope.evidences = data;
    });

    $scope.downloadXls = function () {


        var opts = [{sheetid: 'Evidencije', header: true}];
        var res = alasql('SELECT INTO XLSX("Evidencije.xlsx",?) FROM ?', [opts, [convertTableData()]]);
    };

    $scope.downloadPdf = function () {

        var data = convertTableData();
        var rows = [];
        rows.push(['Ime', 'Prijava', 'Odjava', 'Lokacija', 'Projekt']);
        for (i = 0; i < data.length; i++) {
            rows.push([data[i].Ime, data[i].Prijava, data[i].Odjava, data[i].Lokacija, data[i].Projekt]);
        }

        var docDefinition = {
            content: [
                {
                    table: {

                        headerRows: 1,
                        widths: ['auto', 'auto', 'auto', 'auto', 'auto'],

                        body: rows
                    }
                }
            ]
        };

        pdfMake.createPdf(docDefinition).download("Evidencije.pdf");

    };

    $scope.csvData = function(){
        return convertTableData();
    };

    var convertTableData = function () {
        var data = $scope.evidences;
        var returnData = [];
        for (i = 0; i < data.length; i++) {

            var returnEntity = {
                Ime: undefined,
                Prijava: undefined,
                Odjava: undefined,
                Lokacija: undefined,
                Projekt: undefined
            };

            returnEntity.Ime = data[i].name;

            returnEntity.Prijava = data[i].signInTimestamp;

            returnEntity.Odjava = data[i].signOutTimestamp;

            returnEntity.Lokacija = data[i].location;

            returnEntity.Projekt = data[i].project;

            returnData.push(returnEntity);
        }
        return returnData;
    };

});

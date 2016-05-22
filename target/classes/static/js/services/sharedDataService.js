angular.module('sharedDataService', [])
    .service('sharedDataService', function () {
        var title = null;

        return {
            getTitle: function () {
                return title;
            },
            setTitle: function(value) {
                title = value;
            }
        };
    });
(function (ng) {
    var mod = ng.module("marcoModule");
    mod.constant("marcosContext", "api/marcos");
    mod.controller('marcoCtrl', ['$scope', '$http', 'marcosContext',
        function ($scope, $http, marcosContext) {
            $http.get('data/marcos.json').then(function (response) {
                $scope.marcosRecords = response.data;
            });
        }
    ]);
}
)(angular);


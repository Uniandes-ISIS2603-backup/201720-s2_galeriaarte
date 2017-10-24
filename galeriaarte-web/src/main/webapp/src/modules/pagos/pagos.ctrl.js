(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagosContext", "api/pagos");
    mod.controller('pagoCtrl', ['$scope', '$http', 'pagosContext',
        function ($scope, $http, pagoContext) {
            $http.get('data/pagos.json').then(function (response) {
                $scope.pagosRecords = response.data;
            });
        }
    ]);
}
)(angular);

(function (ng) {
    var mod = ng.module("compraModule");
    mod.constant("comprasContext", "api/compras");
    mod.controller('compraCtrl', ['$scope', '$http', 'comprasContext',
        function ($scope, $http, compraContext) {
            $http.get('data/compras.json').then(function (response) {
                $scope.comprasRecords = response.data;
            });
        }
    ]);
}
)(angular);

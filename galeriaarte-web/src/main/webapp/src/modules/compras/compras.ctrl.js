(function (ng) {
    var mod = ng.module("compraModule");
    mod.constant("comprasContext", "compras");
    mod.constant("obrasContext", "api/obras");
    mod.controller('compraCtrl', ['$scope', '$http', 'obrasContext', '$state', 'comprasContext',
        function ($scope, $http, obrasContext, $state , comprasContext) {
            $http.get(obrasContext + '/' + $state.params.obraid + '/' + comprasContext).then(function (response) {
                $scope.comprasRecords = response.data;
            });
        }
    ]);
}
)(angular);

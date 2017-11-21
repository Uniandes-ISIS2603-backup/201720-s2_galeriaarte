(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagosContext", "pagos");
    mod.constant("comprasContext", "api/compras")
    mod.controller('pagoCtrl', ['$scope', '$http','comprasContext','state', 'pagosContext',
        function ($scope, $http, pagosContext) {
            $http.get(pagosContext).then(function (response) {
                $scope.pagosRecords = response.data;
            });
            
            
            if(($state.params.pagoId !== undefined) && ($state.params.pagoId !== null)) {
                $http.get(pagoContext + '/' + $state.params.pagoId).then(function (response) {
                    $scope.currentPago = response.data;
                });
            }
        }
    ]);
}
)(window.angular);



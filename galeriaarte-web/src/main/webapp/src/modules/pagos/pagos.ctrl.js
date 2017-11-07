(function (ng) {
    var mod = ng.module("pagoModule");
<<<<<<< HEAD
    mod.constant("pagosContext", "pagos");
    mod.constant("comprasContext", "api/compras")
    mod.controller('pagoCtrl', ['$scope', '$http','comprasContext','state', 'pagosContext',
        function ($scope, $http, comprasContext, $state, pagosContext) {
            $http.get(comprasContext + '/' + $state.params.compraId + '/' + pagosContext).then(function (response) {
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
=======
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
>>>>>>> origin/master

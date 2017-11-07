(function (ng) {
    var mod = ng.module("compraModule");
<<<<<<< HEAD
    mod.constant("comprasContext", "api/compras");
   // mod.constant("obrasContext", "api/obras");
    mod.controller('compraCtrl', ['$scope', '$http', 'comprasContext', '$state',
        function ($scope, $http, comprasContext, $state) {
            //$http.get('data/compras.json').then(function (response) {
            $http.get(comprasContext).then(function (response) {
                $scope.comprasRecords = response.data;
            });
            
            if ($state.params.compraId !== undefined) {
                $http.get(comprasContext + '/' + $state.params.compraId).then(function (response) {
                    $scope.pagosRecords = response.data.pagos;
                    $scope.currentCompra = response.data;
                });
            }
=======
    mod.constant("comprasContext", "compras");
    mod.constant("obrasContext", "api/obras");
    mod.controller('compraCtrl', ['$scope', '$http', 'obrasContext', '$state', 'comprasContext',
        function ($scope, $http, obrasContext, $state , comprasContext) {
            $http.get(obrasContext + '/' + $state.params.obraid + '/' + comprasContext).then(function (response) {
                $scope.comprasRecords = response.data;
            });
>>>>>>> origin/master
        }
    ]);
}
)(angular);

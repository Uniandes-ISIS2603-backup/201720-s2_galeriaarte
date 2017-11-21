(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagosContext", "api/pagos");
    mod.controller('pagoNewCtrl', ['$scope', '$http', 'pagosContext', '$state', 'comprasContext', '$rootScope',
        function ($scope, $http, pagosContext, $state, comprasContext, $rootScope) {
            $rootScope.edit = false;
            $scope.createPago = function () {
//                $http.post(pagosContext, {
                $http.post('api/compra/' + $scope.pagoCompraId + '/pago', {
                    id: 0,
                    total: $scope.pagoTotal,
                    impuesto: $scope.pagoImpuesto
                }).then(function (response) {
                    
                    $state.go('pagosList', {pagoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
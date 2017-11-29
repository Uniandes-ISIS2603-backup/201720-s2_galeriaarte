(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagosContext", "api/pago");
    mod.controller('pagoNewCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createPago = function () {
                $http.get('api/compra/' + $state.params.compraId).then(function(response){
                    $http.post('api/pago/', {
                    id: 0,
                    total: $scope.total,
                    impuesto: $scope.impuesto,
                    idCompra: response.date
                }).then(function (response) {
                    
                    $state.go('pagosList', {pagoId: response.data.id}, {reload: true});
                });
                })
                
            };
        }
    ]);
}
)(window.angular);
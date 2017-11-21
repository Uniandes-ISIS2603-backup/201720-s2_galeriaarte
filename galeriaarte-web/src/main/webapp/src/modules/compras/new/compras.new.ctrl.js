(function (ng) {
    var mod = ng.module("compraModule");
    mod.constant("comprasContext", "api/compras");
    mod.controller('compraNewCtrl', ['$scope', '$http', 'comprasContext', '$state', '$rootScope',

        function ($scope, $http, comprasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createCompra = function () {
                console.log($scope.valor);
                $http.post(comprasContext, {
                    valor: $scope.valor,
                    fecha: $scope.fecha
                }).then(function (response) {
                    //Blog created successfully
                    $state.go('comprasList', {compraId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);



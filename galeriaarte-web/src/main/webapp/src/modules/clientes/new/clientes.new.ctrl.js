(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clientesContext", "api/cliente");
    mod.controller('clienteNewCtrl', ['$scope', '$http', 'clientesContext', '$state', '$rootScope',
        function ($scope, $http, clientesContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createCliente = function () {
                $http.post(clientesContext, {
                    id: 0,
                    name: $scope.clienteName,
                    tipoTarjeta: $scope.clienteTipoTarjeta,
                    numTarjeta: $scope.clienteNumeroTarjeta
                }).then(function (response) {
                    $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
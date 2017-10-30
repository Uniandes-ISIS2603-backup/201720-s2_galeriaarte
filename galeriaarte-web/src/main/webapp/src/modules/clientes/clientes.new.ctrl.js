(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clientesContext", "api/cliente");
    mod.controller('clienteNewCtrl', ['$scope', '$http', 'clientesContext', '$state', '$rootScope',
        function ($scope, $http, clientesContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createCliente = function () {
                $http.post(clientesContext, {
                    nombre: $scope.clienteNombre,
                    TipoTarjeta: $scope.clienteTipoTarjeta,
                    NumTarjeta: $scope.clienteNumTarjeta
                }).then(function (response) {
                    //Author created successfully
                    $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);